/*
 * Created on 2003-10-22
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado.bo;
import org.apache.torque.util.Criteria;

import com.cyclops.tornado.om.User;
import com.cyclops.tornado.om.UserPeer;
/**
 * @author jiaqi guo
 * @email g-cyclops@users.sourceforge.net
 */
public class UserBroker extends AbstractObjectBroker {
    /** Implementation of method getObjectClass() in this class
     * @see com.cyclops.tornado.bo.AbstractObjectBroker#getObjectClass()
     */
    protected Class getObjectClass() {
        return User.class;
    }
    /** Implementation of method getPrimaryKey() in this class
     * @see com.cyclops.tornado.bo.AbstractObjectBroker#getPrimaryKey()
     */
    protected String getPrimaryKey() {
        return UserPeer.ID;
    }
    /** Method retrieveByName()
     * @param userName Account name of user
     * @return User object, null if not found
     * @throws Exception torque exception
     */
    public User retrieveByName(String userName) throws Exception {
        Criteria crit = new Criteria();
        crit.and(UserPeer.USER_NAME, userName);
        return (User) retrieve(crit);
    }
}
