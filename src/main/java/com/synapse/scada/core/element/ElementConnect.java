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

import org.apache.log4j.Logger;

import com.synapse.scada.config.SubArea;
import com.synapse.scada.core.SynapseException;
import com.synapse.scada.core.jmx.JmxHelper;
import com.synapse.scada.core.jmx.SynapseJMXException;

/**
 * The Class ElementConnect. Class invokes connect method of specified Element
 * MBean object
 * 
 * @author Tomek Kozlowski (rysiekblah)
 * @version 1.00 (Nov 8, 2012)
 */
public class ElementConnect implements Command {

	/** The LOG. */
	static Logger LOG = Logger.getLogger(ElementConnect.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ees.synapse.server.ICommand#execute(com.ees.synapse.config.SubArea)
	 */
	public void execute(SubArea subArea) throws SynapseException {
		String name = subArea.getName();
		LOG.debug("Connect Element name(" + name + ")");
		try {
			JmxHelper.Instance().invokeMethod(
					JmxHelper.Instance().createObjectName("Element", name),
					"connect", null, null);
		} catch (SynapseJMXException e) {
			LOG.error("Connection Element name(" + name
					+ ") to protocol FAILED");
			throw new SynapseException(e.getMessage());
		}

	}

}
