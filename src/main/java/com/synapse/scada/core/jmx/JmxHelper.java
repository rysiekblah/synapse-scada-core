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
package com.synapse.scada.core.jmx;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.management.Attribute;
import javax.management.JMException;
import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.NotificationListener;
import javax.management.ObjectName;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;

import org.apache.log4j.Logger;

/**
 * The Class JmxHelper.
 * 
 * @author Tomek Kozlowski (rysiekblah)
 * @version 1.00 (Nov 9, 2012)
 */
public class JmxHelper {

	/** The LOG. */
	static Logger LOG = Logger.getLogger(JmxHelper.class);

	/** The mbs. */
	private MBeanServer mbs;

	/** The cs. */
	private static JMXConnectorServer cs;

	/** The instance. */
	private static JmxHelper instance;

	/**
	 * Instantiates a new jmx helper.
	 */
	private JmxHelper() {

	}

	/**
	 * Gets the mB server.
	 * 
	 * @return the mB server
	 */
	public MBeanServer getMBServer() {
		return mbs;
	}

	/**
	 * Instance.
	 * 
	 * @return the jmx helper
	 */
	public static JmxHelper Instance() {
		if (instance == null) {
			instance = new JmxHelper();
		}
		return instance;
	}

	/**
	 * Prints the m beans.
	 */
	public void printMBeans() {
		LOG.debug("MBeans count: " + mbs.getMBeanCount());
	}

	/**
	 * Domain.
	 * 
	 * @return the string
	 */
	public String domain() {
		return mbs.getDefaultDomain();
	}

	/**
	 * Creates the mb server.
	 * 
	 * @param domain
	 *            the domain
	 */
	public void createMBServer(String domain) {
		LOG.debug("Create MBServer with domain(" + domain + ")");
		mbs = MBeanServerFactory.createMBeanServer(domain);
	}

	/**
	 * Release mb server.
	 */
	public void releaseMBServer() {
		LOG.debug("Release MBServer with domain(" + mbs.getDefaultDomain()
				+ ")");
		MBeanServerFactory.releaseMBeanServer(mbs);
	}

	/**
	 * Invoke method.
	 * 
	 * @param name
	 *            the name
	 * @param operationName
	 *            the operation name
	 * @param params
	 *            the params
	 * @param signature
	 *            the signature
	 * @throws SynapseJMXException
	 *             the synapse jmx exception
	 */
	public void invokeMethod(ObjectName name, String operationName,
			Object[] params, String[] signature) throws SynapseJMXException {
		try {
			mbs.invoke(name, operationName, params, signature);
		} catch (JMException e) {
			LOG.error("Invoking method (" + operationName + ") of object("
					+ name + ") FAILED");
			throw new SynapseJMXException(e.getMessage());
		}
	}

	/**
	 * Creates the object name.
	 * 
	 * @param key
	 *            the key
	 * @param value
	 *            the value
	 * @return the object name
	 * @throws SynapseJMXException
	 *             the synapse jmx exception
	 */
	public ObjectName createObjectName(String key, String value)
			throws SynapseJMXException {
		ObjectName objName = null;

		try {

			objName = ObjectName
					.getInstance(mbs.getDefaultDomain(), key, value);
			return objName;

		} catch (JMException e) {
			LOG.error("Creation ObjectName: key(" + key + "), value(" + value
					+ ") FAILED");
			throw new SynapseJMXException(e.getMessage());
		}
	}

	/**
	 * Sets the m bean attribute.
	 * 
	 * @param key
	 *            the key
	 * @param value
	 *            the value
	 * @param attribute
	 *            the attribute
	 * @throws SynapseJMXException
	 *             the synapse jmx exception
	 */
	public void setMBeanAttribute(String key, String value, Attribute attribute)
			throws SynapseJMXException {

		try {

			mbs.setAttribute(
					ObjectName.getInstance(mbs.getDefaultDomain(), key, value),
					attribute);

		} catch (JMException e) {
			LOG.error("Setting attribute(" + attribute.getName()
					+ ") of object: key(" + key + "(, value(" + value
					+ ") FAILED");
			throw new SynapseJMXException(e.getMessage());

		}
	}

	/**
	 * Gets the m bean attribute.
	 * 
	 * @param key
	 *            the key
	 * @param value
	 *            the value
	 * @param attribute
	 *            the attribute
	 * @return the MBean attribute
	 * @throws SynapseJMXException
	 *             the synapse jmx exception
	 */
	public Object getMBeanAttribute(String key, String value, String attribute)
			throws SynapseJMXException {

		Object object = null;

		try {
			object = mbs.getAttribute(
					ObjectName.getInstance(mbs.getDefaultDomain(), key, value),
					attribute);
		} catch (JMException e) {
			LOG.error("Get attribute(" + attribute + ") of ObjectName key("
					+ key + ")" + ", value(" + value + ") FAILED");
			throw new SynapseJMXException(e.getMessage());
		}
		return object;
	}

	/**
	 * Creates the m bean.
	 * 
	 * @param className
	 *            the class name
	 * @param objectName
	 *            the object name
	 * @throws SynapseJMXException
	 *             the synapse jmx exception
	 */
	public void createMBean(String className, String objectName)
			throws SynapseJMXException {

		try {
			LOG.debug("Create MBean: className( " + className
					+ "), objectName(" + objectName + ")");
			// Create MBean
			mbs.createMBean(className, ObjectName.getInstance(objectName));

		} catch (JMException e) {
			LOG.error("MBean creation FAILED");
			throw new SynapseJMXException(e.getMessage());
		}
	}

	/**
	 * Creates the m bean.
	 * 
	 * @param clazz
	 *            the class name
	 * @param value
	 *            the value
	 * @throws SynapseJMXException
	 *             the synapse jmx exception
	 */
	public void createMBean(Class<?> clazz, String value)
			throws SynapseJMXException {
		try {

			LOG.debug("Create MBean: domain(" + mbs.getDefaultDomain()
					+ "), key(" + clazz.getSimpleName() + "), value(" + value
					+ ")");
			// Create mbean
			mbs.createMBean(
					clazz.getName(),
					ObjectName.getInstance(mbs.getDefaultDomain(),
							clazz.getSimpleName(), value));

		} catch (JMException e) {
			LOG.error("MBean creation FAILED");
			throw new SynapseJMXException(e.getMessage());

		}
	}

	/**
	 * Register m bean.
	 * 
	 * @param object
	 *            the object
	 * @param name
	 *            the name
	 * @throws SynapseJMXException
	 *             the synapse jmx exception
	 */
	public void registerMBean(Object object, String name)
			throws SynapseJMXException {
		try {
			LOG.debug("Register MBean: object(" + object.getClass()
					+ "), name(" + name + ")");
			mbs.registerMBean(object, ObjectName.getInstance(name));

		} catch (JMException e) {
			LOG.error("Register MBean FAILED");
			throw new SynapseJMXException(e.getMessage());

		}
	}

	/**
	 * Register m bean.
	 * 
	 * @param object
	 *            the object
	 * @param key
	 *            the key
	 * @param value
	 *            the value
	 * @throws SynapseJMXException
	 *             the synapse jmx exception
	 */
	public void registerMBean(Object object, String key, String value)
			throws SynapseJMXException {

		LOG.debug("Register MBean: object(" + object.getClass() + ", key("
				+ key + "), value(" + value + ")");

		try {

			mbs.registerMBean(object,
					ObjectName.getInstance(mbs.getDefaultDomain(), key, value));

		} catch (JMException e) {
			LOG.error("MBean registration FAILED");
			throw new SynapseJMXException(e.getMessage());
		}
	}

	/**
	 * Adds the notification listener.
	 * 
	 * @param name
	 *            the name
	 * @param listener
	 *            the listener
	 * @throws SynapseJMXException
	 *             the synapse jmx exception
	 */
	public void addNotificationListener(String name,
			NotificationListener listener) throws SynapseJMXException {

		LOG.debug("Add notification listener(" + listener.getClass()
				+ "), to object(" + name + ")");

		try {

			mbs.addNotificationListener(new ObjectName(name), listener, null,
					null);

		} catch (JMException e) {
			LOG.error("Notification listener adding FAILED");
			throw new SynapseJMXException(e.getMessage());

		}
	}

	/**
	 * Attach jmx connector.
	 * 
	 * @param serviceUrl
	 *            the service url
	 * @throws SynapseJMXException
	 *             the synapse exception
	 */
	public void attachJmxConnector(String serviceUrl)
			throws SynapseJMXException {
		LOG.info("Attaching to jmx connector server");
		JMXServiceURL url;
		try {
			url = new JMXServiceURL(serviceUrl);
			cs = JMXConnectorServerFactory
					.newJMXConnectorServer(url, null, mbs);
			cs.start();
		} catch (MalformedURLException e) {
			LOG.error("JMX connection FAILED: URL ERROR");
			throw new SynapseJMXException(e.getMessage());
		} catch (IOException e) {
			LOG.error("JMX connection FAILED: IO ERROR");
			if (e.getCause().getClass().getName() == "javax.naming.NameAlreadyBoundException") {
				try {
					LOG.info("Already attached, closing connector server");
					cs.stop();
					throw new SynapseJMXException(e.getMessage());
				} catch (IOException e1) {
					LOG.error("Already attached, closing connection server FAILED");
					throw new SynapseJMXException(e1.getMessage());
				}
			} else {
				throw new SynapseJMXException(e.getMessage());
			}
		}

	}

	/**
	 * Attach jmx connector.
	 * 
	 * @param protocol
	 *            the protocol
	 * @param host
	 *            the host
	 * @param port
	 *            the port
	 * @throws SynapseJMXException
	 *             the synapse jmx exception
	 */
	public void attachJmxConnector(String protocol, String host, int port)
			throws SynapseJMXException {

		LOG.debug("Attaching to JMX connector: protocol(" + protocol
				+ "), host(" + host + ", port(" + port + ")");

		try {

			JMXServiceURL url = new JMXServiceURL(protocol, host, port);
			cs = JMXConnectorServerFactory
					.newJMXConnectorServer(url, null, mbs);
			cs.start();

		} catch (IOException e) {
			LOG.error("Attache to JMX connector FAILED");
			throw new SynapseJMXException(e.getMessage());
		}

	}

	/**
	 * Attach jmx connector.
	 * 
	 * @param protocol
	 *            the protocol
	 * @param host
	 *            the host
	 * @param port
	 *            the port
	 * @param urlPath
	 *            the url path
	 * @throws SynapseJMXException
	 *             the synapse jmx exception
	 */
	public void attacheJmxConnector(String protocol, String host, int port,
			String urlPath) throws SynapseJMXException {

		LOG.debug("Attache to JMX connector: protocol(" + protocol + "), host("
				+ host + ", port(" + port + "), urlPath(" + urlPath + ")");

		try {

			JMXServiceURL url = new JMXServiceURL(protocol, host, port, urlPath);
			cs = JMXConnectorServerFactory
					.newJMXConnectorServer(url, null, mbs);
			cs.start();

		} catch (IOException e) {
			LOG.error("Attache to JMX connector FAILED");
			throw new SynapseJMXException(e.getMessage());
		}
	}

	/**
	 * Detatch jmx connector.
	 * 
	 * @throws SynapseJMXException
	 *             the synapse jmx exception
	 */
	public static void detatchJmxConnector() throws SynapseJMXException {

		LOG.debug("Detache JMX connector: " + cs.toString());

		try {

			cs.stop();

		} catch (IOException e) {
			LOG.error("JMX connector detatching FAILED");
			throw new SynapseJMXException(e.getMessage());
		}
	}

}
