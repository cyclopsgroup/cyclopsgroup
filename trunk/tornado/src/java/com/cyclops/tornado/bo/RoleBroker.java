/*
 * Created on 2003-11-3
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado.bo;
import com.cyclops.tornado.om.Role;
import com.cyclops.tornado.om.RolePeer;
/**
 * @author jiaqi guo
 * @email g-cyclops@users.sourceforge.net
 */
public class RoleBroker extends AbstractObjectBroker {
    /** Implementation of method getObjectClass() in this class
     * @see com.cyclops.tornado.bo.AbstractObjectBroker#getObjectClass()
     */
    protected Class getObjectClass() {
        return Role.class;
    }
    /** Implementation of method getPrimaryKey() in this class
     * @see com.cyclops.tornado.bo.AbstractObjectBroker#getPrimaryKey()
     */
    protected String getPrimaryKey() {
        return RolePeer.ROLE_ID;
    }
}
