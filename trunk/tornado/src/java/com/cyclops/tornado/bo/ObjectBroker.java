/*
 * Created on 2003-10-22
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado.bo;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.List;

import org.apache.commons.beanutils.MethodUtils;
import org.apache.torque.om.Persistent;
import org.apache.torque.util.Criteria;
/**
 * @author jiaqi guo
 * @email g-cyclops@users.sourceforge.net
 */
public class ObjectBroker implements DBConnectable {
    private static final Class[] CRITERIA_AND_CONNECTION =
        new Class[] { Criteria.class, Connection.class };
    /** Default database name */
    public static final String DEFAULT_DATABASE_NAME = "default";
    private transient Connection connection;
    /** Delete records from database
     * @param objectClass Class of object to be delete
     * @param crit Criteria object
     * @throws Exception TorqueException actually
     */
    public void delete(Class objectClass, Criteria crit) throws Exception {
        Method doDelete =
            MethodUtils.getAccessibleMethod(
                getPeerClass(objectClass),
                "doDelete",
                CRITERIA_AND_CONNECTION);
        doDelete.invoke(null, new Object[] { crit, connection });
    }
    /** Implementation of method getConnection() in this class
     * @see com.cyclops.tornado.bo.DBConnectable#getConnection()
     */
    public Connection getConnection() {
        return connection;
    }
    /** Implementation of method getDatabaseName() in this class
     * @see com.cyclops.tornado.bo.DBConnectable#getDatabaseName()
     */
    public String getDatabaseName() {
        return DEFAULT_DATABASE_NAME;
    }
    /** Get peer class for object class
     * @param objectClass Object class
     * @return Peer class
     * @throws Exception TorqueException
     */
    protected Class getPeerClass(Class objectClass) throws Exception {
        return Class.forName(objectClass.getName() + "Peer");
    }
    /** Method query()
     * @param objectClass
     * @param crit Criteria object
     * @return List of query result
     * @throws Exception It doesn't handle any exception here
     */
    public List query(Class objectClass, Criteria crit) throws Exception {
        Method doSelect =
            MethodUtils.getAccessibleMethod(
                getPeerClass(objectClass),
                "doSelect",
                CRITERIA_AND_CONNECTION);
        return (List) doSelect.invoke(null, new Object[] { crit, connection });
    }
    /** Method queryAll()
     * @param objectClass Object class
     * @return All of them
     * @throws Exception from torque
     */
    public List queryAll(Class objectClass) throws Exception {
        return query(objectClass, new Criteria());
    }
    /** Method retrieve()
     * @param objectClass Class of om object
     * @param crit Criteria object
     * @return Single object, null if not found
     * @throws Exception not handle any exception here
     */
    public Object retrieve(Class objectClass, Criteria crit) throws Exception {
        crit.setSingleRecord(true);
        List rs = query(objectClass, crit);
        return rs.isEmpty() ? null : rs.get(0);
    }
    /** Method save()
     * @param obj Persistent object to be saved
     * @throws Exception Not handle any exception here
     */
    public void save(Persistent obj) throws Exception {
        if (obj instanceof LastAccessTrackable) {
            ((LastAccessTrackable) obj).setLastAccess(
                System.currentTimeMillis());
        }
        if (obj instanceof CreatedTimeTrackable && obj.isNew()) {
            ((CreatedTimeTrackable) obj).setCreatedTime(
                System.currentTimeMillis());
        }
        obj.save(connection);
    }
    /** Implementation of method setConnection() in this class
     * @see com.cyclops.tornado.bo.DBConnectable#setConnection(java.sql.Connection)
     */
    public void setConnection(Connection dbcon) {
        connection = dbcon;
    }
}
