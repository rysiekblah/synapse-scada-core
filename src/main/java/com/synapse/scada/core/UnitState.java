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

import com.synapse.scada.core.jmx.SynapseJMXException;

/**
 * The Enum UnitState.
 *
 * @author Tomek Kozlowski (rysiekblah)
 * @version 1.00 (Nov 3, 2012)
 */
public enum UnitState {

    /** The ON. */
    ON(1),
    
    /** The OFF. */
    OFF(0),

    VALUE(3),
    
    /** The UNKNOWN. */
    UNKNOWN(-1);

    /** The value. */
    private int value;

    /**
     * Instantiates a new unit state.
     *
     * @param value the value
     */
    UnitState(int value) {
        this.value = value;
    }

    /**
     * Gets the value.
     *
     * @return the value
     */
    public int getValue() {
        return value;
    }

    public void changeState(String element, String id) throws SynapseJMXException {
        switch (value) {
            case 1:
                SystemAdapter.getInstance().turnOnUnit(element, id, UnitState.ON);
                break;
            case 0:
                SystemAdapter.getInstance().turnOffUnit(element, id, OFF);
                break;
            default:
                break;
        }
    }
}
