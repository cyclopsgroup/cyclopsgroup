/*
 * Created on 2003-9-29
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado.services.user;
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
    /** Method isAnonymous() in Class User
     * @return Whether or not this user is anonymous user
     */
    boolean isAnonymous();
}
