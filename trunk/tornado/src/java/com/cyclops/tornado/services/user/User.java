/*
 * Created on 2003-9-29
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado.services.user;
import java.util.Date;
import java.util.Hashtable;
/**
 * @author joeblack
 * @since 2003-9-29 17:23:48
 *
 */
public interface User {
    /** Empty user object array */
    User[] EMPTY_ARRAY = new User[0];
    /** Implementation of method getCreatedTimeDate() in this class
     * @return Created time of this user
     */
    Date getCreatedTimeDate();
    /** Implementation of method getDescription() in this class
     * @return Description of this user
     */
    String getDescription();
    /** Implementation of method getEmail() in this class
     * @return Email of this user
     */
    String getEmail();
    /** Over riding method getFullName() of super class
     * @return Full name of this user
     */
    String getFullName();
    /** Implementation of method getLastSigninDate() in this class
     * @return Last sign in date of this user
     */
    Date getLastSigninDate();
    /** Name of this user
     * @return User name
     */
    String getName();
    /** Method getPermStorage() in Class User
     * @return Permanent objects storage
     */
    Hashtable getPermStorage();
    /** Implementation of method getSigninCounter() in this class
     * @return How many time this user signin in this system
     */
    int getSigninCounter();
    /** Method getTempStorage() in Class User
     * @return Temporary objects storage
     */
    Hashtable getTempStorage();
    /** Method isAnonymous() in Class User
     * @return Whether or not this user is anonymous user
     */
    boolean isAnonymous();
    /** Implementation of method isDisabled() in this class
     * @return Is this user disabled from the system
     */
    boolean isDisabled();
    /** Implementation of method isSystem() in this class
     * @return Is this user a system user
     */
    boolean isSystem();
    /** Method setAnonymous() in Class User
     * @param anonymous If it is anonymous
     */
    void setAnonymous(boolean anonymous);
    /** Method setName() in Class User
     * @param name Name value
     */
    void setName(String name);
}
