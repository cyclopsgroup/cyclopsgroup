/*
 * Created on 2003-9-29
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado.services.user;
import java.util.Hashtable;
/**
 * @author joeblack
 * @since 2003-9-29 17:23:48
 *
 */
public interface User {
    /** Empty user object array */
    User[] EMPTY_ARRAY = new User[0];
    /** Name of this user
     * @return User name
     */
    String getName();
    /** Method getPermStorage() in Class User
     * @return Permanent objects storage
     */
    Hashtable getPermStorage();
    /** Method getTempStorage() in Class User
     * @return Temporary objects storage
     */
    Hashtable getTempStorage();
    /** Method isAnonymous() in Class User
     * @return Whether or not this user is anonymous user
     */
    boolean isAnonymous();
    /** Method setAnonymous() in Class User
     * @param anonymous If it is anonymous
     */
    void setAnonymous(boolean anonymous);
    /** Method setName() in Class User
     * @param name Name value
     */
    void setName(String name);
}
