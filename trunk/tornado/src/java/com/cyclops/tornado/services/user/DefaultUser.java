/*
 * Created on 2003-9-29
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado.services.user;
import java.util.Date;
import java.util.Hashtable;

import com.cyclops.tornado.om.DUser;
/**
 * @author jiaqi guo
 * @email g-cyclops@users.sourceforge.net
 */
public class DefaultUser extends DUser implements User {
    private boolean isAnonymous;
    private Hashtable permStorage = new Hashtable();
    private Hashtable tempStorage = new Hashtable();
    /**
     * @see com.cyclops.tornado.services.user.User#getCreatedTimeDate()
     */
    public Date getCreatedTimeDate() {
        return new Date(getCreatedTime());
    }
    /** Implementation of method getId() in this class
     * @see com.cyclops.tornado.services.user.User#getId()
     */
    public int getId() {
        return getUserId();
    }
    /**
     * @see com.cyclops.tornado.services.user.User#getLastSigninDate()
     */
    public Date getLastSigninDate() {
        return new Date(getLastSignin());
    }
    /** Method getName()
     * @see com.cyclops.tornado.services.user.User#getName()
     */
    public String getName() {
        return getUserName();
    }
    /** Method getPermStorage()
     * @see com.cyclops.tornado.services.user.User#getPermStorage()
     */
    public Hashtable getPermStorage() {
        return permStorage;
    }
    /** Method getTempStorage()
     * @see com.cyclops.tornado.services.user.User#getTempStorage()
     */
    public Hashtable getTempStorage() {
        return tempStorage;
    }
    /** Method isAnonymous()
     * @see com.cyclops.tornado.services.user.User#isAnonymous()
     */
    public boolean isAnonymous() {
        return isAnonymous;
    }
    /**
     * @see com.cyclops.tornado.services.user.User#isDisabled()
     */
    public boolean isDisabled() {
        return getIsDisabled();
    }
    /**
     * @see com.cyclops.tornado.services.user.User#isSystem()
     */
    public boolean isSystem() {
        return getIsSystem();
    }
    /** Method setAnonymous() in Class DefaultUser
     * @param b If the user is anonymous
     */
    public void setAnonymous(boolean b) {
        isAnonymous = b;
    }
    /** Method setName() in Class DefaultUser
     * @param string Name of the user
     */
    public void setName(String string) {
        setUserName(string);
    }
}
