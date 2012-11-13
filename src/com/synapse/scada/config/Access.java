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

/**
 * The Enum Access defines all possible types of access to the unit.
 * 
 * @author Tomek Kozlowski (rysiekblah)
 * @version 1.00 (Nov 2, 2012)
 */
public enum Access {

	/** The read-write mode. */
	RW(0, "ReadWrite"),

	/** Read only mode. */
	R(1, "Read"),

	/** Write only. */
	W(2, "Write");

	/** The code. */
	private final int code;

	/** The label. */
	private final String label;

	/**
	 * Instantiates a new access.
	 * 
	 * @param code
	 *            the code
	 * @param label
	 *            the label
	 */
	Access(int code, String label) {
		this.code = code;
		this.label = label;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Enum#toString()
	 */
	public String toString() {
		return this.label;
	}

	/**
	 * Value of.
	 * 
	 * @return the int
	 */
	public int valueOf() {
		return this.code;
	}
}
