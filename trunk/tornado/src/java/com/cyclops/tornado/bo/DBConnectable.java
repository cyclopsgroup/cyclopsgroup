/*
 * Created on 2003-10-22
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado.bo;
import java.sql.Connection;
/**
 * @author jiaqi guo
 * @email g-cyclops@users.sourceforge.net
 */
public interface DBConnectable {
    /** Method getDatabaseName()
     * @return Which database to connect
     */
    String getDatabaseName();
    /** Method setConnection()
     * @param dbcon Connection object
     */
    void setConnection(Connection dbcon);
    /** Method getConnection()
     * @return Connection object
     */
    Connection getConnection();
}
