/*
 * Created on 2003-10-8
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado.services.rundata;
import java.util.Hashtable;

import org.apache.commons.httpclient.Base64;
import org.apache.fulcrum.security.session.SessionBindingEvent;

import com.cyclops.tornado.om.User;
/**
 * @author joeblack
 * @since 2003-10-8 16:10:57
 */
public class TornadoUser
    extends User
    implements
        org.apache.fulcrum.security.entity.User,
        com.cyclops.tornado.services.user.User {
    private Hashtable tempStorage = new Hashtable();
    private Hashtable permStorage = new Hashtable();
    private boolean isAnonymous = true;
    /** Method getAccessCounterForSession()
     * @see org.apache.fulcrum.security.entity.User#getAccessCounterForSession()
     */
    public int getAccessCounterForSession() {
        return getAccessCounter();
    }
    /** Method getPassword()
     * @see org.apache.fulcrum.security.entity.User#getPassword()
     */
    public String getPassword() {
        return new String(Base64.decode(getEncryptedPassword().getBytes()));
    }
    /** Method getPerm()
     * @see org.apache.fulcrum.security.entity.User#getPerm(java.lang.String, java.lang.Object)
     */
    public Object getPerm(String key, Object defaultValue) {
        if (permStorage.containsKey(key)) {
            return permStorage.get(key);
        } else {
            return defaultValue;
        }
    }
    /** Method getPerm()
     * @see org.apache.fulcrum.security.entity.User#getPerm(java.lang.String)
     */
    public Object getPerm(String key) {
        return getPerm(key, null);
    }
    /** Method getPermStorage()
     * @see org.apache.fulcrum.security.entity.User#getPermStorage()
     */
    public Hashtable getPermStorage() {
        return permStorage;
    }
    /** Method getTemp()
     * @see org.apache.fulcrum.security.entity.User#getTemp(java.lang.String, java.lang.Object)
     */
    public Object getTemp(String key, Object defaultValue) {
        if (tempStorage.containsKey(key)) {
            return permStorage.get(key);
        } else {
            return defaultValue;
        }
    }
    /** Method getTemp()
     * @see org.apache.fulcrum.security.entity.User#getTemp(java.lang.String)
     */
    public Object getTemp(String key) {
        return getTemp(key, null);
    }
    /** Method getTempStorage()
     * @see org.apache.fulcrum.security.entity.User#getTempStorage()
     */
    public Hashtable getTempStorage() {
        return tempStorage;
    }
    /** Method hasLoggedIn()
     * @see org.apache.fulcrum.security.entity.User#hasLoggedIn()
     */
    public boolean hasLoggedIn() {
        return !isAnonymous;
    }
    /** Method incrementAccessCounter()
     * @see org.apache.fulcrum.security.entity.User#incrementAccessCounter()
     */
    public void incrementAccessCounter() {
        setAccessCounter(getAccessCounter() + 1);
    }
    /** Method incrementAccessCounterForSession()
     * @see org.apache.fulcrum.security.entity.User#incrementAccessCounterForSession()
     */
    public void incrementAccessCounterForSession() {
        incrementAccessCounter();
    }
    /** Method isConfirmed()
     * @see org.apache.fulcrum.security.entity.User#isConfirmed()
     */
    public boolean isConfirmed() {
        return true;
    }
    /** Method removeTemp()
     * @see org.apache.fulcrum.security.entity.User#removeTemp(java.lang.String)
     */
    public Object removeTemp(String key) {
        return tempStorage.remove(key);
    }
    /** Method setAccessCounterForSession()
     * @see org.apache.fulcrum.security.entity.User#setAccessCounterForSession(int)
     */
    public void setAccessCounterForSession(int counter) {
        setAccessCounter(counter);
    }
    /** Method setHasLoggedIn()
     * @see org.apache.fulcrum.security.entity.User#setHasLoggedIn(java.lang.Boolean)
     */
    public void setHasLoggedIn(Boolean hasLogin) {
        setAnonymous(!hasLogin.booleanValue());
    }
    /** Method setPassword()
     * @see org.apache.fulcrum.security.entity.User#setPassword(java.lang.String)
     */
    public void setPassword(String pass) {
        setEncryptedPassword(new String(Base64.encode(pass.getBytes())));
    }
    /** Method setPerm()
     * @see org.apache.fulcrum.security.entity.User#setPerm(java.lang.String, java.lang.Object)
     */
    public void setPerm(String key, Object value) {
        permStorage.put(key, value);
    }
    /** Method setPermStorage()
     * @see org.apache.fulcrum.security.entity.User#setPermStorage(java.util.Hashtable)
     */
    public void setPermStorage(Hashtable arg0) {
        //do nothing
    }
    /** Method setTemp()
     * @see org.apache.fulcrum.security.entity.User#setTemp(java.lang.String, java.lang.Object)
     */
    public void setTemp(String key, Object value) {
        tempStorage.put(key, value);
    }
    /** Method setTempStorage()
     * @see org.apache.fulcrum.security.entity.User#setTempStorage(java.util.Hashtable)
     */
    public void setTempStorage(Hashtable arg0) {
        //do nothing
    }
    /** Method updateLastLogin()
     * @see org.apache.fulcrum.security.entity.User#updateLastLogin()
     */
    public void updateLastLogin() throws Exception {
        setLastLoginTime(System.currentTimeMillis());
    }
    /** Method valueBound()
     * @see org.apache.fulcrum.security.session.SessionBindingListener#valueBound(org.apache.fulcrum.security.session.SessionBindingEvent)
     */
    public void valueBound(SessionBindingEvent arg0) {
        //do nothing
    }
    /** Method valueUnbound()
     * @see org.apache.fulcrum.security.session.SessionBindingListener#valueUnbound(org.apache.fulcrum.security.session.SessionBindingEvent)
     */
    public void valueUnbound(SessionBindingEvent arg0) {
        // do nothing
    }
    /** Method getName()
     * @see org.apache.fulcrum.security.entity.SecurityEntity#getName()
     */
    public String getName() {
        return getUserName();
    }
    /** Method setName()
     * @see org.apache.fulcrum.security.entity.SecurityEntity#setName(java.lang.String)
     */
    public void setName(String name) {
        setUserName(name);
    }
    /** Method isAnonymous()
     * @see com.cyclops.tornado.services.user.User#isAnonymous()
     */
    public boolean isAnonymous() {
        return isAnonymous;
    }
    /** Method setAnonymous()
     * @see com.cyclops.tornado.services.user.User#setAnonymous(boolean)
     */
    public void setAnonymous(boolean anonymous) {
        isAnonymous = anonymous;
    }
}
