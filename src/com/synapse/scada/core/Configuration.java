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
package com.synapse.scada.core;

import com.synapse.scada.config.SystemConfig;

/**
 * The Class Configuration.
 *
 * @author Tomek Kozlowski (rysiekblah)
 * @version 1.00 (Nov 3, 2012)
 */
public class Configuration implements ConfigurationMBean {

	/** The config. */
	private SystemConfig config;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ees.synapse.server.ConfigurationMBean#getConfig()
	 */
	public SystemConfig getConfig() {
		return config;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ees.synapse.server.ConfigurationMBean#setConfig(com.ees.synapse.config
	 * .SystemConfig)
	 */
	public void setConfig(SystemConfig _config) {
		config = _config;
	}

}
