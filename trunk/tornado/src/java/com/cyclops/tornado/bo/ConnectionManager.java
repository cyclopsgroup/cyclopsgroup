/*
 * Created on 2003-10-22
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado.bo;
import java.sql.Connection;
import java.util.Hashtable;
import java.util.Iterator;

import org.apache.torque.Torque;
/**
 * @author jiaqi guo
 * @email g-cyclops@users.sourceforge.net
 */
public class ConnectionManager {
    private Hashtable connections = new Hashtable();
    private Hashtable cache = new Hashtable();
    /** Method getConnection()
     * @return Default database connection
     * @throws Exception Thown when the connection is not existed
     */
    public Connection getConnection() throws Exception {
        return getConnection(ObjectBroker.DEFAULT_DATABASE_NAME);
    }
    /** Method getConnection()
     * @param databaseName Name of the database
     * @return Connection to the database
     * @throws Exception When connection doesn't exist
     */
    public Connection getConnection(String databaseName) throws Exception {
        if (connections.containsKey(databaseName)) {
            return (Connection) connections.get(databaseName);
        } else {
            Connection dbcon = Torque.getConnection(databaseName);
            connections.put(databaseName, dbcon);
            return dbcon;
        }
    }
    /** Method getConnectableObject()
     * @param brokerClass Class of requested object
     * @return Instance of requested class
     * @throws Exception It doesn't handle any exception here
     */
    public Object getConnectableObject(Class brokerClass) throws Exception {
        if (cache.contains(brokerClass)) {
            return cache.get(brokerClass);
        } else {
            DBConnectable dbc = (DBConnectable) brokerClass.newInstance();
            dbc.setConnection(getConnection(dbc.getDatabaseName()));
            cache.put(brokerClass, dbc);
            return dbc;
        }
    }
    /** Clear everything, release all connections
     */
    public void release() {
        for (Iterator i = connections.values().iterator(); i.hasNext();) {
            Connection dbcon = (Connection) i.next();
            Torque.closeConnection(dbcon);
        }
        connections.clear();
        cache.clear();
    }
}
