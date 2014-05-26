/********************************************************************************
 * 
 * Effective Engineering Solutions, Copyright 2012
 * All rights reserved.
 * 
 * Synapse - monitor for automation systems
 *  
 *******************************************************************************/

package com.synapse.scada.core.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

// TODO: Auto-generated Javadoc
/**
 * The Class DBProxy.
 */
public class DBProxy {

    /** The conn. */
    private Connection conn;

    /** The stmt. */
    private Statement stmt;

    /** The jdbc subprotocol. */
    private String jdbcSubprotocol;

    /** The jdbc subname. */
    private String jdbcSubname;

    /** The jdbc packet name. */
    private String jdbcPacketName;

    /**
     * Instantiates a new dB proxy.
     * 
     * @param subprotocol the subprotocol
     * @param subname the subname
     * @param packet the packet
     */
    public DBProxy(String subprotocol, String subname, String packet) {
        jdbcSubprotocol = subprotocol;
        jdbcSubname = subname;
        jdbcPacketName = packet;
    }

    /**
     * Connect.
     * 
     * @param host the host
     * @param port the port
     * @param dbName the db name
     * @param user the user
     * @param password the password
     */
    public void connect(String host, String port, String dbName, String user, String password) {

        String connUrl = "jdbc:" + jdbcSubprotocol + ":" + jdbcSubname + "://" + host + ":" + port + "/" + dbName;

        try {
            Class.forName(jdbcPacketName);
            conn = DriverManager.getConnection(connUrl, user, password);
            stmt = conn.createStatement();
            System.out.println("Connected to: " + connUrl);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Connection failed to " + connUrl);
        }
    }

    /**
     * Disconnect.
     */
    public void disconnect() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Disconnection FAILED");
        }
    }

    /**
     * Gets the connection.
     * 
     * @return the connection
     */
    public Connection getConnection() {
        return conn;
    }

    /**
     * Gets the statement.
     * 
     * @return the statement
     */
    public Statement getStatement() {
        return stmt;
    }

}
