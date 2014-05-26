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
 * The Class Area.
 *
 * @author Tomek Kozlowski (rysiekblah)
 * @version 1.00 (Nov 2, 2012)
 */
public class Area implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3012456134527588380L;

	/** The id. */
	private String id;

    /** The name. */
    private String name;

    /** The sub area. */
    private List<SubArea> subArea = new ArrayList<SubArea>();

    /**
     * Instantiates a new area.
     */
    Area() {

    }

    /**
     * Sets the id.
     *
     * @param val the new id
     */
    @XmlAttribute
    public void setId(String val) {
        id = val;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

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
     * Sets the sub area.
     *
     * @param val the new sub area
     */
    public void setSubArea(List<SubArea> val) {
        subArea = val;
    }

    /**
     * Gets the sub area.
     *
     * @return the sub area
     */
    public List<SubArea> getSubArea() {
        return subArea;
    }
}
