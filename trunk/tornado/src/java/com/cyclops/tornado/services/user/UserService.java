/*
 * Created on 2003-9-29
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado.services.user;
import org.apache.fulcrum.Service;
/**
 * @author joeblack
 * @since 2003-9-29 17:00:26
 *
 * Class
 */
public interface UserService extends Service {
    /** Service name in fulcrum */
    String SERVICE_NAME = "UserService";
    /** Method getActiveUsers() in Class UserService
     * @return Array of active users
     */
    User[] getActiveUsers();
    /** Method getAnonymousUser() in Class UserService
     * @return Anonymous user object
     */
    User getAnonymousUser();
    /** Get active user object by user name
     * @param key Key referenced to this user
     * @return User object, null if this user is not active to this key
     */
    User getActiveUser(String key);
    /** Method singinUser() in Class UserService
     * @param key Key of this user session
     * @param userName Name of the user
     */
    void singin(String key, String userName);
    /** Method singout() in Class UserService
     * @param key Key of this user session
     */
    void singout(String key);
}
