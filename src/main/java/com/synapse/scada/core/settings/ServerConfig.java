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
package com.synapse.scada.core.settings;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class ServerConfig.
 *
 * @author Tomek Kozlowski (rysiekblah)
 * @version 1.00 (Nov 8, 2012)
 */
@XmlRootElement
public class ServerConfig {

    /** The synapse jmx service. */
    private SynapseJmxService synapseJmxService;

    /** The synapse db config. */
    private SynapseDBConfig synapseDBConfig;
    
    /** The synapse protocol proxy. */
    List<SynapseProtocolProxy> synapseProtocolProxy = new ArrayList<SynapseProtocolProxy>();

    /**
     * Instantiates a new server config.
     */
    ServerConfig() {

    }

    /**
     * Instantiates a new server config.
     * 
     * @param jmx the jmx
     * @param db the db
     */
    ServerConfig(SynapseJmxService jmx, SynapseDBConfig db) {
        synapseJmxService = jmx;
        synapseDBConfig = db;
    }

    /**
     * Sets the synapse jmx service.
     * 
     * @param val the new synapse jmx service
     */
    public void setSynapseJmxService(SynapseJmxService val) {
        synapseJmxService = val;
    }

    /**
     * Sets the synapse db config.
     * 
     * @param db the new synapse db config
     */
    public void setSynapseDBConfig(SynapseDBConfig db) {
        synapseDBConfig = db;
    }

    /**
     * Gets the synapse jmx service.
     * 
     * @return the synapse jmx service
     */
    public SynapseJmxService getSynapseJmxService() {
        return synapseJmxService;
    }

    /**
     * Gets the synapse db config.
     * 
     * @return the synapse db config
     */
    public SynapseDBConfig getSynapseDBConfig() {
        return synapseDBConfig;
    }
    
    /**
     * Gets the synapse protocol proxy.
     *
     * @return the synapse protocol proxy
     */
    public List<SynapseProtocolProxy> getSynapseProtocolProxy() {
        return synapseProtocolProxy;
    }

    /**
     * Sets the synapse protocol proxy.
     *
     * @param synapseProtocolProxy the new synapse protocol proxy
     */
    public void setSynapseProtocolProxy(List<SynapseProtocolProxy> synapseProtocolProxy) {
        this.synapseProtocolProxy = synapseProtocolProxy;
    }
}
