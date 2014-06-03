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
package com.synapse.scada.client;

import java.io.*;
import java.util.jar.JarEntry;

import javax.management.Attribute;
import javax.management.JMX;
import javax.management.MBeanException;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import javax.servlet.ServletContext;

import com.synapse.scada.config.SystemConfig;
import com.synapse.scada.core.ConfigurationMBean;

/**
 * The Class Client.
 * 
 * @author Tomek Kozlowski (rysiekblah)
 * @version 1.00 (Nov 4, 2012)
 */
public class Client implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2421556000829637787L;

	/** The Constant DOMAIN. */
	private static final String DOMAIN = "EssProject";

	private String connString;

	/** The jmxc. */
	private JMXConnector jmxc;

	/** The mbsc. */
	private MBeanServerConnection mbsc;

	/** The cfg object. */
	private ObjectName cfgObject;

	/** The config. */
	private ConfigurationMBean config;

    private static Client instance;

	/**
	 * Instantiates a new client.
	 * 
	 * @param ihost
	 *            the ihost
	 * @param iport
	 *            the iport
	 * @throws SynapseClientException
	 *             the synapse client exception
	 */
	public Client() {
        JMXSettingsReader reader = new JMXSettingsReader();
        try (InputStream in = new FileInputStream("/home/tomek/IdeaProjects/synapse-scada-core/server_config.xml");) {

            reader.parse(in);
            connString = "service:" + reader.getService() + "://"
                    + reader.getHost() + ":" + reader.getPort() + "/"
                    + reader.getName();
            System.out.println("conn: " + connString);
            connect();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SynapseClientException e) {
            e.printStackTrace();
        }


    }

    public static Client getInstance() {
        if (instance == null) {
            instance = new Client();
        }
        return instance;
    }

	public void init(ServletContext context) throws SynapseClientException {
		JMXSettingsReader reader = new JMXSettingsReader();
		reader.parse(context.getResourceAsStream("/server_config.xml"));
		connString = "service:" + reader.getService() + "://"
				+ reader.getHost() + ":" + reader.getPort() + "/"
				+ reader.getName();
	}



	/**
	 * Gets the conn id.
	 * 
	 * @return the conn id
	 * @throws SynapseClientException
	 */
	public String getConnectionId() {
		try {
			return jmxc.getConnectionId();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Connect.
	 * 
	 * @throws SynapseClientException
	 *             the synapse client exception
	 */
	public void connect() throws SynapseClientException {
		try {
			jmxc = JMXConnectorFactory.connect(new JMXServiceURL(connString),
					null);
			mbsc = jmxc.getMBeanServerConnection();
			cfgObject = ObjectName.getInstance(DOMAIN, "Configuration", "1");
			config = JMX.newMBeanProxy(mbsc, cfgObject,
					ConfigurationMBean.class);
		} catch (Exception e) {
			throw new SynapseClientException("Connection FAILED."
					+ e.getMessage());
		}

	}

	/**
	 * Gets the domains.
	 * 
	 * @return the domains
	 */
	public String[] getDomains() {
		try {
			return mbsc.getDomains();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Gets the default domain.
	 * 
	 * @return the default domain
	 */
	public String getDefaultDomain() {
		try {
			return mbsc.getDefaultDomain();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Close.
	 */
	public void close() {
		try {
			jmxc.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets the config x.
	 * 
	 * @return the config x
	 */
	public SystemConfig getConfig() {

		SystemConfig syscfg=null;
		
		try {
			syscfg = config.getConfig();
		} catch (Exception e) {

		}

		return syscfg;
	}

	/**
	 * Gets the state.
	 * 
	 * @param name
	 *            the name
	 * @return the state
	 */
	public String getState(ObjectName name) {
		try {
			return (String) mbsc.getAttribute(name, "State");
		} catch (MBeanException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * Sets the state.
	 * 
	 * @param name
	 *            the name
	 * @param string
	 *            the string
	 */
	public void setState(ObjectName name, String string) {
		try {
			mbsc.setAttribute(name, new Attribute("State", string));
		} catch (MBeanException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
