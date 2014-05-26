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
 * The Class SynapseProtocolProxy.
 *
 * @author Tomek Kozlowski (rysiekblah)
 * @version 1.00 (Nov 8, 2012)
 */
public class SynapseProtocolProxy {
    
    /** The protocol name. */
    private String name;
    
    /** The Proxy package name. */
    private String packageName;
    
    /**
     * Gets the protocol name.
     *
     * @return the name
     */
    @XmlAttribute
    public String getName() {
        return name;
    }
    
    /**
     * Sets the protocol name.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Gets the Proxy package name.
     *
     * @return the package name
     */
    @XmlAttribute
    public String getPackageName() {
        return packageName;
    }
    
    /**
     * Sets the Proxy package name.
     *
     * @param packageName the new package name
     */
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
    
}
