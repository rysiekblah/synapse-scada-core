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

package com.synapse.scada.config;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * POJO object for sub-area associated with element. The element object managed
 * set of units provided by sub-area.
 *
 * @author Tomek Kozlowski (rysiekblah)
 * @version 1.00 (Nov 2, 2012)
 */
public class SubArea implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -396435151552082286L;

	/** The id. */
	private Integer id;

	/** The name. */
	private String name;

	/** The protocol. */
	private String protocol;

	/** The host. */
	private String host;

	/** The port. */
	private Integer port;

	/** The unit. */
	private List<Unit> unit = new ArrayList<Unit>();

	/**
	 * Instantiates a new sub area.
	 */
	public SubArea() {

	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	@XmlAttribute
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id
	 *            the new id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	@XmlAttribute
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 * 
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the protocol.
	 * 
	 * @return the protocol
	 */
	@XmlAttribute
	public String getProtocol() {
		return protocol;
	}

	/**
	 * Sets the protocol.
	 * 
	 * @param protocol
	 *            the new protocol
	 */
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	/**
	 * Gets the host.
	 * 
	 * @return the host
	 */
	@XmlAttribute
	public String getHost() {
		return host;
	}

	/**
	 * Sets the host.
	 * 
	 * @param host
	 *            the new host
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * Gets the port.
	 * 
	 * @return the port
	 */
	@XmlAttribute
	public Integer getPort() {
		return port;
	}

	/**
	 * Sets the port.
	 * 
	 * @param port
	 *            the new port
	 */
	public void setPort(Integer port) {
		this.port = port;
	}

	/**
	 * Gets the unit.
	 * 
	 * @return the unit
	 */
	public List<Unit> getUnit() {
		return unit;
	}

	/**
	 * Sets the unit.
	 * 
	 * @param unit
	 *            the new unit
	 */
	public void setUnit(List<Unit> unit) {
		this.unit = unit;
	}

}
