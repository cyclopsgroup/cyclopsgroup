/*
 * Created on 2003-9-29
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado.services.user;
import org.apache.fulcrum.Service;

import com.cyclops.tornado.BrokerManager;
/**
 * @author joeblack
 * @since 2003-9-29 17:00:26
 *
 * Class
 */
public interface UserService extends Service {
    /** Check result code, check success */
    int CHECK_RESULT_OK = 0;
    /** Check result code, password error*/
    int CHECK_RESULT_INCORRECT_PASSWORD = 1;
    /** Check result code, invalid user name */
    int CHECK_RESULT_INVALID_USER = 2;
    /** Check result code, user is disabled */
    int CHECK_RESULT_DISABLED_USER = 3;
    /** Check result code, exception occurred */
    int CHECK_RESULT_EXCEPTION = 4;
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
     * @param brokerManager BrokerManager object
     */
    void singin(String key, String userName, BrokerManager brokerManager);
    /** Method singout() in Class UserService
     * @param key Key of this user session
     * @param brokerManager BrokerManager object
     */
    void singout(String key, BrokerManager brokerManager);
    /** Method checkUser() in Class UserService
     * @param userName Name of user
     * @param password Password for signing
     * @param brokerManager BrokerManager object
     * @return Sigin result, refer to the constants about it pls.
     */
    int checkUser(
        String userName,
        String password,
        BrokerManager brokerManager);
}
