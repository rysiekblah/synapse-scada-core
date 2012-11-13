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

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;
import org.xml.sax.InputSource;

/**
 * The Class Server.
 *
 * @author Tomek Kozlowski (rysiekblah)
 * @version 1.00 (Nov 3, 2012)
 */
public abstract class Server {
    
    /** The LOG. */
    static Logger LOG = Logger.getLogger(Server.class.getName());

    /**
     * Creates the.
     *
     * @throws SynapseException the synapse exception
     */
    public void create() throws SynapseException {
        
    }
    
    /**
     * Start the server.
     *
     * @throws SynapseException the synapse exception
     */
    public void start() throws SynapseException {
        
    }
    
    /**
     * Stop the server.
     *
     * @throws SynapseException the synapse exception
     */
    public void stop() throws SynapseException {
        
    }
    
    
    /**
     * Configure.
     *
     * @param clazz the clazz
     * @param filename the filename
     * @return the object
     * @throws SynapseException the synapse exception
     */
    protected Object configure(Class<?> clazz, String filename) throws SynapseException {

        Object s = null;

        try {

            InputSource inputSource = new InputSource(new BufferedInputStream(new FileInputStream(filename)));
            s = XmlHelper.unmarshal(inputSource, clazz);

        } catch (JAXBException e) {
            LOG.error("File: " + filename + " parsing FAILED");
            throw new SynapseException(e.getMessage());

        } catch (FileNotFoundException e) {
            LOG.error("File: " + filename + " not found");
            throw new SynapseException(e.getMessage());
        }

        LOG.info("Server has been configured properly: " + clazz.getName());

        return s;

    }
}
