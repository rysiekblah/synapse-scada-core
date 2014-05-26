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

import javax.xml.bind.annotation.XmlAttribute;

/**
 * The Class SynapseJmxService.
 *
 * @author Tomek Kozlowski (rysiekblah)
 * @version 1.00 (Nov 8, 2012)
 */
public class SynapseJmxService {

    /** The name. */
    private String name;

    /** The service. */
    private String service;

    /** The host. */
    private String host;

    /** The port. */
    private String port;

    /**
     * Sets the name.
     * 
     * @param val the new name
     */
    @XmlAttribute
    public void setName(String val) {
        name = val;
    }

    /**
     * Gets the name.
     * 
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the service.
     * 
     * @param val the new service
     */
    @XmlAttribute
    public void setService(String val) {
        service = val;
    }

    /**
     * Gets the service.
     * 
     * @return the service
     */
    public String getService() {
        return service;
    }

    /**
     * Sets the host.
     * 
     * @param val the new host
     */
    @XmlAttribute
    public void setHost(String val) {
        host = val;
    }

    /**
     * Gets the host.
     * 
     * @return the host
     */
    public String getHost() {
        return host;
    }

    /**
     * Sets the port.
     * 
     * @param val the new port
     */
    @XmlAttribute
    public void setPort(String val) {
        port = val;
    }

    /**
     * Gets the port.
     * 
     * @return the port
     */
    public String getPort() {
        return port;
    }
}
