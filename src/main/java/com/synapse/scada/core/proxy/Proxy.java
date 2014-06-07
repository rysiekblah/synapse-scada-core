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

import com.synapse.scada.config.SubArea;
import com.synapse.scada.core.SynapseException;

// TODO: Auto-generated Javadoc
/**
 * The Class IProxy. This is interface between system and server sides. Proxy
 * modules operates on dedicated protocol depends on system implementation. It
 * could be modbus, upd packets, rs232 and others.
 * 
 * @author Tomek Kozlowski
 */
public interface Proxy {

	/**
	 * Initialize. Method initializes protocol object.
	 * 
	 * @param subArea
	 *            the sub area
	 * @throws SynapseException
	 *             the synapse exception
	 */
	void initialize(SubArea subArea) throws SynapseException;

	/**
	 * Connect. Method makes a connection to controlled device.
	 * 
	 * @throws SynapseException
	 *             the synapse exception
	 */
	void connect() throws SynapseException;

	/**
	 * Disconnect.
	 * 
	 * @throws SynapseException
	 *             the synapse exception
	 */
	void disconnect() throws SynapseException;
	
	/**
	 * Check if the proxy is connected to the system.
	 * 
	 * @return
	 */
	boolean isConnected();

	/**
	 * Method sets the state of the unit. It could be used for units of
	 * 'boolean' type. Based on SubArea contents the method could also
	 * determines type of unit (boolean, value, percent) and make proper
	 * request.
	 * 
	 * @param id
	 *            the id
	 * @param state
	 *            the state
	 * @throws SynapseException
	 *             the synapse exception
	 */
	void setState(int id, int state) throws SynapseException;

	/**
	 * Method gets the state of the unit. It could be used for units of
	 * 'boolean' type. Based on SubArea contents the method could also
	 * determines type of unit (boolean, value, percent) and make proper
	 * request.
	 * 
	 * @param id
	 *            the id
	 * @return the state
	 * @throws SynapseException
	 *             the synapse exception
	 */
	int getState(int id) throws SynapseException;

	/**
	 * Method sets the value of the unit. It could be used for units of 'value'
	 * or 'percents' type.
	 * 
	 * @param id
	 *            the id
	 * @param value
	 *            the value
	 * @throws SynapseException
	 *             the synapse exception
	 */
	void setValue(int id, int value) throws SynapseException;

	/**
	 * Method sets the value of the unit. It could be used for units of 'value'
	 * or 'percents' type.
	 * 
	 * @param id
	 *            the id
	 * @return the value
	 * @throws SynapseException
	 *             the synapse exception
	 */
	int getValue(int id) throws SynapseException;

    void addUnit(Integer id, int i);
}
