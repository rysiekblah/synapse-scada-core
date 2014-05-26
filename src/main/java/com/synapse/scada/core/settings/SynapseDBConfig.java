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

/**
 * The Class SynapseDBConfig.
 *
 * @author Tomek Kozlowski (rysiekblah)
 * @version 1.00 (Nov 8, 2012)
 */
public class SynapseDBConfig {

    /** The jdbc driver subname. */
    private String jdbcDriverSubname;

    /** The jdbc driver subprotocol. */
    private String jdbcDriverSubprotocol;

    /** The jdbc driver package. */
    private String jdbcDriverPackage;

    /** The db connection address. */
    private String dbConnectionAddress;

    /** The db connection port. */
    private String dbConnectionPort;

    /** The db login user. */
    private String dbLoginUser;

    /** The db login password. */
    private String dbLoginPassword;

    /**
     * Sets the jdbc driver subname.
     * 
     * @param val the new jdbc driver subname
     */
    public void setJdbcDriverSubname(String val) {
        jdbcDriverSubname = val;
    }

    /**
     * Gets the jdbc driver subname.
     * 
     * @return the jdbc driver subname
     */
    public String getJdbcDriverSubname() {
        return jdbcDriverSubname;
    }

    /**
     * Sets the jdbc driver subprotocol.
     * 
     * @param val the new jdbc driver subprotocol
     */
    public void setJdbcDriverSubprotocol(String val) {
        jdbcDriverSubprotocol = val;
    }

    /**
     * Gets the jdbc driver subprotocol.
     * 
     * @return the jdbc driver subprotocol
     */
    public String getJdbcDriverSubprotocol() {
        return jdbcDriverSubprotocol;
    }

    /**
     * Sets the jdbc driver package.
     * 
     * @param val the new jdbc driver package
     */
    public void setJdbcDriverPackage(String val) {
        jdbcDriverPackage = val;
    }

    /**
     * Gets the jdbc driver package.
     * 
     * @return the jdbc driver package
     */
    public String getJdbcDriverPackage() {
        return jdbcDriverPackage;
    }

    /**
     * Sets the db connection address.
     * 
     * @param val the new db connection address
     */
    public void setDbConnectionAddress(String val) {
        dbConnectionAddress = val;
    }

    /**
     * Gets the db connection address.
     * 
     * @return the db connection address
     */
    public String getDbConnectionAddress() {
        return dbConnectionAddress;
    }

    /**
     * Sets the db connection port.
     * 
     * @param val the new db connection port
     */
    public void setDbConnectionPort(String val) {
        dbConnectionPort = val;
    }

    /**
     * Gets the db connection port.
     * 
     * @return the db connection port
     */
    public String getDbConnectionPort() {
        return dbConnectionPort;
    }

    /**
     * Sets the db login user.
     * 
     * @param val the new db login user
     */
    public void setDbLoginUser(String val) {
        dbLoginUser = val;
    }

    /**
     * Gets the db login user.
     * 
     * @return the db login user
     */
    public String getDbLoginUser() {
        return dbLoginUser;
    }

    /**
     * Sets the db login password.
     * 
     * @param val the new db login password
     */
    public void setDbLoginPassword(String val) {
        dbLoginPassword = val;
    }

    /**
     * Gets the db login password.
     * 
     * @return the db login password
     */
    public String getDbLoginPassword() {
        return dbLoginPassword;
    }

}
