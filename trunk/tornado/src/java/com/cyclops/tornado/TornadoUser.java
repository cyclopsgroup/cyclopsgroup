/*
 * Created on 2003-10-16
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado;
import java.util.ArrayList;

import org.apache.commons.collections.CollectionUtils;
import org.apache.fulcrum.TurbineServices;
import org.apache.turbine.RunData;

import com.cyclops.tornado.services.user.DefaultUser;
import com.cyclops.tornado.services.user.UserService;
/**
 * @author joeblack
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class TornadoUser extends DefaultUser {
    /** Empty array of this instance */
    public static final TornadoUser[] EMPTY_ARRAY = new TornadoUser[0];
    /** Get current user instance
     * @param data RunData object
     * @return User instance
     */
    public static final TornadoUser getInstance(RunData data) {
        return getInstance(data.getSession().getId());
    }
    /** Get current user instance
     * @param key Key of user instance
     * @return Instance of the user
     */
    public static final TornadoUser getInstance(String key) {
        UserService userService =
            (UserService) TurbineServices.getInstance().getService(
                UserService.SERVICE_NAME);
        return (TornadoUser) userService.getActiveUser(key);
    }
    /** Get all active users
     * @return Array of active users
     */
    public static final TornadoUser[] getInstances() {
        UserService userService =
            (UserService) TurbineServices.getInstance().getService(
                UserService.SERVICE_NAME);
        ArrayList temp = new ArrayList();
        CollectionUtils.addAll(temp, userService.getActiveUsers());
        return (TornadoUser[]) temp.toArray(EMPTY_ARRAY);
    }
    /** Method getPassport()
     * @return Passport object
     */
    public Passport getPassport() {
        return (Passport) getTempStorage().get(Passport.KEY_IN_USER);
    }
    /** Method setPassport()
     * @param passport Passport object
     */
    public void setPassport(Passport passport) {
        getTempStorage().put(Passport.KEY_IN_USER, passport);
    }
}
