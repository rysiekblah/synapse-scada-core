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

import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class SystemConfig holds system configuration.
 *
 * @author Tomek Kozlowski (rysiekblah)
 * @version 1.00 (Nov 2, 2012)
 */
@XmlRootElement
public class SystemConfig implements Serializable {

    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2114651706787204329L;
	
	/** The area. */
    private List<Area> area = new ArrayList<Area>();

    /**
     * Instantiates a new system config.
     */
    public SystemConfig() {

    }

    /**
     * Sets the area.
     *
     * @param val the new area
     */
    public void setArea(List<Area> val) {
        area = val;
    }

    /**
     * Gets the area.
     *
     * @return the area
     */
    public List<Area> getArea() {
        return area;
    }
}
