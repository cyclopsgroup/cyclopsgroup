/*
 * Created on 2003-10-4
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado.services.user;
import org.apache.commons.lang.StringUtils;

import com.cyclops.tornado.BrokerManager;
import com.cyclops.tornado.bo.UserBroker;
/** Default UserService implementation
 * @author joeblack
 * @since 2003-10-4 20:05:45
 */
public class DefaultUserService extends AbstractUserService {
    /** Implementation of method checkUser() in this class
     * @see com.cyclops.tornado.services.user.UserService#checkUser(java.lang.String, java.lang.String, com.cyclops.tornado.BrokerManager)
     */
    public int checkUser(
        String userName,
        String password,
        BrokerManager brokerManager) {
        if (StringUtils.equals(userName, getAnonymousUser().getName())) {
            return CHECK_RESULT_DISABLED_USER;
        }
        try {
            UserBroker userb =
                (UserBroker) brokerManager.getObjectBroker(UserBroker.class);
            com.cyclops.tornado.om.User user = userb.retrieveByName(userName);
            if (user == null) {
                return CHECK_RESULT_INVALID_USER;
            }
            if (user.getIsDisabled()) {
                return CHECK_RESULT_DISABLED_USER;
            }
            if (!StringUtils.equals(password, user.getPassword())) {
                return CHECK_RESULT_INCORRECT_PASSWORD;
            }
            return CHECK_RESULT_OK;
        } catch (Exception e) {
            logger.error("User identification exception", e);
            return CHECK_RESULT_EXCEPTION;
        }
    }
    /** Implementation of method loadUser() in this class
     * @see com.cyclops.tornado.services.user.AbstractUserService#loadUser(java.lang.String, boolean, com.cyclops.tornado.BrokerManager)
     */
    protected User loadUser(
        String userName,
        boolean isAnonymous,
        BrokerManager brokerManager)
        throws Exception {
        User user = super.loadUser(userName, isAnonymous, brokerManager);
        UserBroker userb =
            (UserBroker) brokerManager.getObjectBroker(UserBroker.class);
        com.cyclops.tornado.om.User dbuser = userb.retrieveByName(userName);
        if (user instanceof com.cyclops.tornado.om.User) {
            dbuser.copyTo((com.cyclops.tornado.om.User) user);
        }
        return user;
    }
}
