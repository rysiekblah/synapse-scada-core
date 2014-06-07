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
package com.synapse.scada.core.element;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.management.Attribute;

import org.apache.log4j.Logger;

import com.synapse.scada.config.SubArea;
import com.synapse.scada.core.SynapseException;
import com.synapse.scada.core.jmx.JmxHelper;
import com.synapse.scada.core.jmx.SynapseJMXException;
import com.synapse.scada.core.proxy.ProtocolProxyFactory;
import com.synapse.scada.core.settings.SynapseProtocolProxy;

/**
 * The Class ElementCreate.
 * 
 * @author Tomek Kozlowski (rysiekblah)
 * @version 1.00 (Nov 8, 2012)
 */
public class ElementCreate implements Command {

	/** The LOG. */
	static Logger LOG = Logger.getLogger(ElementCreate.class);

	/** The proxy map. */
	private Map<String, String> proxyMap;

	/** The element class name. */
	private Class<Element> elementClass;

	/**
	 * Instantiates a new element create.
	 * 
	 * @param clazz
	 *            the clazz
	 */
	public ElementCreate(Class<Element> clazz) {
		proxyMap = new HashMap<String, String>();
		elementClass = clazz;
	}

	/**
	 * Creates the proxy map.
	 * 
	 * @param proxys
	 *            the proxys
	 */
	public void createProxyMap(List<SynapseProtocolProxy> proxys) {

        for (SynapseProtocolProxy proxy : proxys) {
            proxyMap.put(proxy.getName(), proxy.getPackageName());
            LOG.debug("Protocol[" + proxy.getName() + "], Proxy[" + proxy.getPackageName() + "]");
        }
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ees.synapse.server.ICommand#execute(com.ees.synapse.config.SubArea)
	 */
	@Override
	public void execute(SubArea subArea) throws SynapseException {

		// Get Area managed by this element
		String name = subArea.getName();

		LOG.debug("Create element name(" + name + ")");

		UnitsManager unitsMgr = new UnitsManager();

		// Config the listener
		try {

			unitsMgr.setElementObjName(JmxHelper.Instance().createObjectName("Element", name));

			JmxHelper.Instance().addNotificationListener("Services:type=Timer",	unitsMgr);

			LOG.info("SubArea name: " + name + ", create MBean for it");
			JmxHelper.Instance().createMBean(elementClass, name);

			// Set element name
			Attribute nameAttr = new Attribute("Name", name);
			JmxHelper.Instance().setMBeanAttribute("Element", name, nameAttr);

            // Set proxy
            String proxyName = proxyMap.get(subArea.getProtocol());
            LOG.debug("Register proxy: " + proxyName);

			Attribute proxyAttr;
			proxyAttr = new Attribute("Proxy", ProtocolProxyFactory.getInstance().createProxy(proxyName));
			JmxHelper.Instance().setMBeanAttribute("Element", name, proxyAttr);

            // Set sub area
            Attribute subAreaAttr = new Attribute("SubArea", subArea);
            JmxHelper.Instance().setMBeanAttribute("Element", name, subAreaAttr);

			JmxHelper.Instance().printMBeans();

		} catch (SynapseJMXException e) {
			LOG.error("Create Element name(" + name + ") FAILED");
			throw new SynapseException(e.getMessage());
		}

	}

}
