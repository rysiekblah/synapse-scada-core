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

import javax.xml.bind.annotation.XmlAttribute;

/**
 * The Class Unit holds current state of the device.
 * 
 * @author Tomek Kozlowski (rysiekblah)
 * @version 1.00 (Nov 2, 2012)
 */
public class Unit implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8483428174566043793L;

	/** The id. */
	private Integer id;

	/** The name. */
	private String name;

	/** The type. */
	private String type;

	/** The state. */
	private Integer state;

	/** The access. */
	private Access access;

	/** The max val. */
	private Integer maxVal;

	/**
	 * Instantiates a new unit.
	 */
	public Unit() {

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
	 * Gets the type.
	 * 
	 * @return the type
	 */
	@XmlAttribute
	public String getType() {
		return type;
	}

	/**
	 * Sets the type.
	 * 
	 * @param type
	 *            the new type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Gets the state.
	 * 
	 * @return the state
	 */
	@XmlAttribute
	public Integer getState() {
		return state;
	}

	/**
	 * Sets the state.
	 * 
	 * @param state
	 *            the new state
	 */
	public void setState(Integer state) {
		this.state = state;
	}

	/**
	 * Gets the access.
	 * 
	 * @return the access
	 */
	@XmlAttribute
	public Access getAccess() {
		return access;
	}

	/**
	 * Sets the access.
	 * 
	 * @param access
	 *            the new access
	 */
	public void setAccess(Access access) {
		this.access = access;
	}

	/**
	 * Gets the max val.
	 * 
	 * @return the max val
	 */
	@XmlAttribute
	public Integer getMaxVal() {
		return maxVal;
	}

	/**
	 * Sets the max val.
	 * 
	 * @param maxVal
	 *            the new max val
	 */
	public void setMaxVal(Integer maxVal) {
		this.maxVal = maxVal;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "UNIT(" + this.name + "), TYPE(" + this.type + "), ID("
				+ this.id + "), STATE(" + this.state + ")";
	}
}
