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
import java.util.Map;

import org.apache.log4j.Logger;

import com.synapse.scada.config.SubArea;
import com.synapse.scada.config.Unit;
import com.synapse.scada.core.SynapseException;
import com.synapse.scada.core.proxy.Proxy;

/**
 * The Class Element.
 * 
 * @author Tomek Kozlowski (rysiekblah)
 * @version 1.00 (Nov 8, 2012)
 */
public class Element implements ElementMBean {

	/** The LOG. */
	static Logger LOG = Logger.getLogger(Element.class.getName());

	/** The name. */
	private String name;

	/** The proxy. */
	private Proxy proxy;

	/** The sub area. */
	private SubArea subArea;

	/** The units. */
	private Map<Integer, Unit> units = new HashMap<Integer, Unit>();

	/**
	 * Instantiates a new element.
	 */
	public Element() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ees.synapse.server.ElementMBean#setProxy(com.ees.synapse.server.IProxy
	 * )
	 */
	public void setProxy(Proxy _proxy) {
		proxy = _proxy;
		try {
			proxy.initialize(subArea);
		} catch (SynapseException e) {
			LOG.error("Proxy initialization FAILED", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ees.synapse.server.ElementMBean#setName(java.lang.String)
	 */
	public void setName(String name) {
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ees.synapse.server.ElementMBean#getName()
	 */
	public String getName() {
		return name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ees.synapse.server.ElementMBean#setSubArea(com.ees.synapse.config
	 * .SubArea)
	 */
	public void setSubArea(SubArea subArea) {
		this.subArea = subArea;
		createMapOfUnits();
	}

	/**
	 * Create map of units associated with this sub-area.
	 */
	private void createMapOfUnits() {
		StringBuffer sbuf = new StringBuffer("Mapping all Units associated with element(" + name + ") :\n");
		for (Unit unit : subArea.getUnit()) {
			units.put(unit.getId(), unit);
			sbuf.append("id:" + unit.getId() + ", name:" + unit.getName() + "\n");
            proxy.addUnit(unit.getId(), 0);
        }
		LOG.debug(sbuf);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ees.synapse.server.ElementMBean#setState(com.ees.synapse.server.UnitState
	 * )
	 */
	@Override
	public void setState(Integer id, Integer state) {

		if (!checkConnection()) {
			LOG.error("Setting state to unit id(" + id
					+ ") FAILED. No connection to the device.");
			return;
		}

		synchronized (units) {
			try {
                LOG.info("State has changed: " + id + ", " + state);
				// Send request to change the unit's state
				proxy.setState(id, state);

				// Update configuration content
				units.get(id).setState(state);

			} catch (SynapseException e) {
				e.printStackTrace();
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ees.synapse.server.ElementMBean#getState()
	 */
	@Override
	public Integer getUnitState(Integer id) {
		int status = 0;

		if (!checkConnection())
			return null;

		synchronized (units) {
			try {
				status = this.proxy.getState(id);
				LOG.debug("Unit id(" + id + ") status=" + status);
				// update configuration
				units.get(id).setState(status);
			} catch (SynapseException e) {
				LOG.error("Getting unit state FAILED.", e);
			}
		}
		// return status==1 ? UnitState.ON : UnitState.OFF ;
		return status;
	}

	/**
	 * Checking connection status.
	 * 
	 * @return true when connected, otherwise false
	 */
	private boolean checkConnection() {
		if (!proxy.isConnected()) {
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ees.synapse.server.element.ElementMBean#queryAllUnits()
	 */
	@Override
	public void queryAllUnits() {
		if (!checkConnection()) {
			LOG.error("Quering of all units state FAILED. Element ("
					+ this.name + ") not connected.");
			return;
		}

		synchronized (units) {
			for (Map.Entry<Integer, Unit> item : units.entrySet()) {
				try {
					int id = item.getValue().getId();
//					LOG.debug("Quering stat of unit id: " + id);
					int stat = proxy.getState(item.getValue().getId());
					// update the state
					item.getValue().setState(stat);
				} catch (SynapseException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ees.synapse.server.ElementMBean#connect()
	 */
	@Override
	public void connect() {
		LOG.debug("Create connection to the device via proxy module");
		try {
			proxy.connect();
		} catch (SynapseException e) {
			LOG.error("Proxy connection FAILED", e);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ees.synapse.server.ElementMBean#disconnect()
	 */
	@Override
	public void disconnect() {
		LOG.debug("Disconnect the element from the system");
		try {
			proxy.disconnect();
		} catch (SynapseException e) {
			LOG.error("Proxy disconnect FAILED", e);
		}

	}
}
