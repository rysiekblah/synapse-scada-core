/********************************************************************************
 * 
 * Effective Engineering Solutions, Copyright 2012
 * All rights reserved.
 * 
 * Synapse - monitor for automation systems
 *  
 *******************************************************************************/
package com.synapse.scada.core.db;

import java.sql.ResultSet;
import java.sql.SQLException;

// TODO: Auto-generated Javadoc
/**
 * The Class DBAdapter.
 */
public class DBAdapter {

    /** The m_db proxy. */
    private DBProxy m_dbProxy = null;

    /**
     * Instantiates a new dB adapter.
     * 
     * @param proxy the proxy
     */
    public DBAdapter(DBProxy proxy) {
        m_dbProxy = proxy;
    }

    /**
     * Creates the table.
     * 
     * @param sql the sql
     */
    public void createTable(String sql) {

    }

    /**
     * Clear table.
     * 
     * @param tableName the table name
     * @throws SQLException the sQL exception
     */
    public void clearTable(String tableName) throws SQLException {
        String sql = "DELETE FROM " + tableName;
        m_dbProxy.getStatement().execute(sql);
    }

    /**
     * Populate.
     */
    public void populate() {

    }

    /**
     * Read table.
     * 
     * @param tableName the table name
     * @return the result set
     * @throws SQLException the sQL exception
     */
    public ResultSet readTable(String tableName) throws SQLException {
        ResultSet rs = null;
        String sql = "SELECT state FROM " + tableName;
        rs = m_dbProxy.getStatement().executeQuery(sql);
        return rs;
    }

    /**
     * Insert row.
     * 
     * @param sql the sql
     * @throws SQLException the sQL exception
     */
    public void insertRow(String sql) throws SQLException {
        m_dbProxy.getStatement().execute(sql);
    }

    /**
     * Sets the state.
     * 
     * @param id the id
     * @param val the val
     * @param tableName the table name
     * @throws SQLException the sQL exception
     */
    public void setState(int id, int val, String tableName) throws SQLException {
        String sql = "UPDATE " + tableName + " SET state=" + val + " WHERE id=" + id;
        if (m_dbProxy != null) {
            m_dbProxy.getStatement().executeUpdate(sql);
        }
    }

    /**
     * Gets the state.
     * 
     * @param id the id
     * @param tableName the table name
     * @return the state
     * @throws SQLException the sQL exception
     */
    public int getState(int id, String tableName) throws SQLException {
        String sql = "SELECT state FROM " + tableName + " WHERE id=" + id;
        System.out.println(sql);
        ResultSet rs = null;
        int state = 0;
        if (m_dbProxy != null) {
            rs = m_dbProxy.getStatement().executeQuery(sql);
            rs.next();
            state = rs.getInt("state");
        }

        return state;
    }
}
