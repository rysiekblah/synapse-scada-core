/********************************************************************************
 * 
 *  Copyright 2012 Synapse server development team
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
package com.synapse.scada.core.proxy;

import org.apache.log4j.Logger;

import com.synapse.scada.core.SynapseException;

/**
 * A factory for creating ProtocolProxy objects.
 */
public class ProtocolProxyFactory {

	/** The LOG. */
	static Logger LOG = Logger.getLogger(ProtocolProxyFactory.class);

	/** The instance. */
	private static ProtocolProxyFactory instance;

	/**
	 * Instantiates a new protocol proxy factory.
	 */
	private ProtocolProxyFactory() {

	}

	/**
	 * Gets the single instance of ProtocolProxyFactory.
	 * 
	 * @return single instance of ProtocolProxyFactory
	 */
	public static ProtocolProxyFactory getInstance() {
		if (instance == null) {
			instance = new ProtocolProxyFactory();
		}
		return instance;
	}

	/**
	 * Creates a new ProtocolProxy object based on class name. The class name is
	 * provided with server configuration file.
	 * 
	 * @param className
	 *            the class name
	 * @return the i proxy
	 * @throws SynapseException
	 *             the synapse exception
	 */
	public Proxy createProxy(String className) throws SynapseException {

		Proxy proxy = null;

		try {

			proxy = (Proxy) Class.forName(className).newInstance();

		} catch (InstantiationException e) {
			LOG.error("Could not create instance of class: " + className);
			throw new SynapseException(e.getMessage());

		} catch (IllegalAccessException e) {
			LOG.error("Class: " + className
					+ " or its constructor not accessible");
			throw new SynapseException(e.getMessage());

		} catch (ClassNotFoundException e) {
			LOG.error("Class: " + className + " not found");
			throw new SynapseException(e.getMessage());
		}

		return proxy;
	}

}
