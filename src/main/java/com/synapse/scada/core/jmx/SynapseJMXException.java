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

/**
 * The Class SynapseJMXException.
 * 
 * @author Tomek Kozlowski (rysiekblah)
 * @version 1.00 (Nov 9, 2012)
 */
public class SynapseJMXException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 152365939926842013L;

	/**
	 * Instantiates a new synapse jmx exception.
	 */
	public SynapseJMXException() {
		super();
	}

	/**
	 * Instantiates a new synapse jmx exception.
	 * 
	 * @param message
	 *            the message
	 */
	public SynapseJMXException(String message) {
		super(message);
	}
}
