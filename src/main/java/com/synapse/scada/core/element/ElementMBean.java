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

import com.synapse.scada.config.SubArea;
import com.synapse.scada.core.proxy.Proxy;


// TODO: Auto-generated Javadoc
/**
 * The Interface ElementMBean.
 *
 * @author Tomek Kozlowski (rysiekblah)
 * @version 1.00 (Nov 8, 2012)
 */
public interface ElementMBean {

	/**
	 * Get unit state with given id.
	 *
	 * @param id the id
	 * @return unit state as Integer value
	 */
	Integer getUnitState(Integer id);

	/**
	 * Query the system of all units state. The method update configuration
	 * content.
	 */
	void queryAllUnits();

	/**
	 * Sets the state.
	 * @param id TODO
	 * @param state
	 *            the new state
	 */
	void setState(Integer id, Integer state);

	/**
	 * Sets the name.
	 * 
	 * @param _name
	 *            the new name
	 */
	void setName(String _name);

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	String getName();

	/**
	 * Sets the proxy.
	 * 
	 * @param _proxy
	 *            the new proxy
	 */
	void setProxy(Proxy _proxy);

	/**
	 * Sets the sub area.
	 * 
	 * @param subArea
	 *            the sub area managed by this element
	 */
	void setSubArea(SubArea subArea);

	/**
	 * Connect.
	 */
	void connect();

	/**
	 * Disconnect.
	 */
	void disconnect();

}
