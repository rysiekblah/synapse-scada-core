/********************************************************************************
 * 
 *  Copyright 2012 Synapse SCADA team
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  
 *******************************************************************************/
package com.synapse.scada.core;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.management.Attribute;
import javax.management.timer.Timer;
import org.apache.log4j.Logger;
import com.synapse.scada.config.Area;
import com.synapse.scada.config.SubArea;
import com.synapse.scada.config.SystemConfig;
import com.synapse.scada.core.element.Command;
import com.synapse.scada.core.element.Element;
import com.synapse.scada.core.element.ElementConnect;
import com.synapse.scada.core.element.ElementCreate;
import com.synapse.scada.core.element.ElementDisconnect;
import com.synapse.scada.core.jmx.JmxHelper;
import com.synapse.scada.core.jmx.SynapseJMXException;
import com.synapse.scada.core.settings.ServerConfig;
import com.synapse.scada.core.settings.SynapseJmxService;
import com.synapse.scada.core.settings.SynapseProtocolProxy;

/**
 * The Class SynapseServer.
 *
 * @author Tomek Kozlowski (rysiekblah)
 * @version 1.00 (Nov 3, 2012)
 */
public class SynapseServer extends Server {

	/** The LOG. */
	static Logger LOG = Logger.getLogger(SynapseServer.class);

	/** Domain name. */
	private static final String domainName = "EssProject";

	/** The cfg file name. */
	private static final String systemCfgFileName = "config_r2.xml";

	/** The server cfg file name. */
	private static final String serverCfgFileName = "synapse_server_cfg.xml";

	/** The timer. */
	private Timer requestTriggerTimer;

	/** The jmx settings. */
	private SynapseJmxService jmxSettings;

	/**
	 * Instantiates a new synapse server.
	 */
	public SynapseServer() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ees.synapse.server.IServerBody#create()
	 */
	public void create() throws SynapseException {

		requestTriggerTimer = new Timer();

		LOG.debug("Start configuration of the Synapse server");

		// Read system configuration
		SystemConfig systemCfg = (SystemConfig) configure(SystemConfig.class,
				systemCfgFileName);
		
		// Read server configuration
		ServerConfig serverCfg = (ServerConfig) configure(ServerConfig.class,
				serverCfgFileName);

		jmxSettings = serverCfg.getSynapseJmxService();

		List<SynapseProtocolProxy> proxys = serverCfg.getSynapseProtocolProxy();

		ElementCreate createCmd = new ElementCreate(Element.class);
		createCmd.createProxyMap(proxys);

		JmxHelper.Instance().createMBServer(domainName);

		LOG.debug("Start creation of system model based on configuration");
		createSystemModel(systemCfg, createCmd);

		LOG.debug("DB access open");
		createDBConnection(serverCfg);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ees.synapse.server.IServerBody#activate()
	 */
	public void start() throws SynapseException {

		LOG.debug("Open system connection for every element proxy");
		executeCommand(new ElementConnect());

		LOG.debug("Start JMX connection to JmxConnector");
		establishRemoteConnection();

		requestTriggerTimer.addNotification("ReadTrigger",
				"Get status of all units", new String(),
				new Date((new Date()).getTime() + 5000), 3000);

		LOG.debug("Start timer");
		requestTriggerTimer.start();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ees.synapse.server.IServerBody#deactivate()
	 */
	public void stop() throws SynapseException {

		requestTriggerTimer.stop(); // Stop trigger timer
		LOG.debug("Close system connection for every element proxy");
		executeCommand(new ElementDisconnect());
		closeDBConnection(); // Close db access
		JmxHelper.Instance().releaseMBServer(); // MBeanserver releasing
		closeRemoteConnection();
	}

	/**
	 * Creates the system model.
	 * 
	 * @param config
	 *            the config
	 * @param createCmd
	 *            the create cmd
	 * @throws SynapseException
	 *             the synapse exception
	 */
	private void createSystemModel(SystemConfig config, Command createCmd)
			throws SynapseException {

		try {

			LOG.debug("Create Configuration MBean and register to MBeanServer");
			JmxHelper.Instance().createMBean(Configuration.class, "1");

			// Set config attribute to Configuration mbean
			Attribute cfgAttr = new Attribute("Config", config);
			JmxHelper.Instance().setMBeanAttribute("Configuration", "1",
					cfgAttr);

			LOG.debug("Register Timer to MBean server");
			JmxHelper.Instance().registerMBean(requestTriggerTimer,
					"Services:type=Timer");

			executeCommand(createCmd);

		} catch (SynapseJMXException e) {
			LOG.error("System model creation FAILED", e);
			throw new SynapseException(e.getMessage());
		}

	}

	/**
	 * Establish remote connection.
	 * 
	 * @throws SynapseException
	 *             the synapse exception
	 */
	private void establishRemoteConnection() throws SynapseException {
		try {
			if (jmxSettings != null) {
				String connString = "service:" + jmxSettings.getService()
						+ "://" + jmxSettings.getHost() + ":"
						+ jmxSettings.getPort() + "/" + jmxSettings.getName();
				LOG.debug("Connection string: " + connString);
				JmxHelper.Instance().attachJmxConnector(connString);
				LOG.debug("Remote connection established succesfully");
			} else {
				LOG.debug("Server not connected to JmxConnector, synapseJmxService("
						+ jmxSettings + ")");
			}

		} catch (SynapseJMXException e) {
			LOG.error("Connect to Jmx connector FAILED - name("
					+ jmxSettings.getName() + "), service("
					+ jmxSettings.getService() + "), host("
					+ jmxSettings.getHost() + "), port("
					+ jmxSettings.getPort() + ")");
			throw new SynapseException(e.getMessage());
		}
	}

	/**
	 * Close remote connection.
	 * 
	 * @throws SynapseException
	 *             the synapse exception
	 */
	private void closeRemoteConnection() throws SynapseException {
		if (jmxSettings != null) {
			try {
				JmxHelper.detatchJmxConnector();
			} catch (SynapseJMXException e) {
				LOG.error("Closing remote jmx connection FAILED");
				throw new SynapseException(e.getMessage());
			}
		}
	}

	/**
	 * Creates the db connection.
	 * 
	 * @param config
	 *            the config
	 * @throws SynapseException
	 *             the synapse exception
	 */
	private void createDBConnection(ServerConfig config)
			throws SynapseException {
		LOG.debug("Create DataBase connection: dbName("
				+ config.getSynapseDBConfig().getJdbcDriverSubname() + ")");
	}

	/**
	 * Close db connection.
	 * 
	 * @throws SynapseException
	 *             the synapse exception
	 */
	private void closeDBConnection() throws SynapseException {
		LOG.debug("Closing DataBase connection");
	}

	/**
	 * Execute command.
	 * 
	 * @param command
	 *            the command
	 * @throws SynapseException
	 *             the synapse exception
	 */
	private void executeCommand(Command command) throws SynapseException {

		try {

			SystemConfig sysCfg = (SystemConfig) JmxHelper.Instance()
					.getMBeanAttribute("Configuration", "1", "Config");

			List<Area> areaList = sysCfg.getArea();
			for (Iterator<Area> ita = areaList.iterator(); ita.hasNext();) {
				Area area = ita.next();
				List<SubArea> subAreaList = area.getSubArea();
				for (Iterator<SubArea> itsa = subAreaList.iterator(); itsa
						.hasNext();) {
					SubArea subArea = itsa.next();
					command.execute(subArea);
				}

			}

		} catch (SynapseJMXException e) {
			LOG.error("Execute command(" + command.getClass() + ") FALED");
			throw new SynapseException(e.getMessage());
		}
	}

}
