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

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.List;

import org.xml.sax.InputSource;

import com.synapse.scada.core.XmlHelper;

// TODO: Auto-generated Javadoc
/**
 * The Class Main.
 */
public class Main {
    
    /**
     * The main method.
     *
     * @param argc the arguments
     */
    public static void main(String[] argc) {
        try {
            InputSource inputSource = new InputSource(new BufferedInputStream(new FileInputStream("config_r2.xml")));
            SystemConfig s = (SystemConfig) XmlHelper.unmarshal(inputSource, com.synapse.scada.config.SystemConfig.class);
            System.out.println("OK");

            List<Area> area = s.getArea();
            for (Iterator<Area> it = area.iterator(); it.hasNext();) {
                Area a = it.next();
                System.out.println("Area " + a.getName());
                List<SubArea> subarea = a.getSubArea();
                System.out.println("  SubArea size = " + subarea.size());

                for (Iterator<SubArea> it2 = subarea.iterator(); it2.hasNext();) {
                    SubArea sub = it2.next();
                    System.out.println("  SubArea " + sub.getId());
                    List<Unit> unit = sub.getUnit();

                    for (Iterator<Unit> it3 = unit.iterator(); it3.hasNext();) {
                        Unit u = it3.next();
                        Access t = u.getAccess();
                        String label = "NULL";
                        if (t != null) {
                            label = t.toString();
                        }
                        System.out.println("    Unit " + u.getName() + " access: " + label);

                    }
                }
            }

        } catch (Exception e) {
            System.out.println("FAILED");
            e.printStackTrace();
        }
    }
}
