/*
 * Created on 2003-10-4
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
/** This user implmentation is for turbine instead of for common usage.
 *
 * @author joeblack
 * @since 2003-10-4 19:53:54
 */
public class User extends DefaultUser {
    /** Empty User object array */
    public static final User[] EMPTY_ARRAY = new User[0];
    /** Method getInstance() in Class User
     * @param data RunData as input object
     * @return User instance
     */
    public static User getInstance(RunData data) {
        return (User) getUserService().getActiveUser(data.getSession().getId());
    }
    /** Method getInstances() in Class User
     * @return Array of active user instances
     */
    public static User[] getInstances() {
        ArrayList users = new ArrayList();
        CollectionUtils.addAll(users, getUserService().getActiveUsers());
        return (User[]) users.toArray(EMPTY_ARRAY);
    }
    private static UserService getUserService() {
        return (UserService) TurbineServices.getInstance().getService(
            UserService.SERVICE_NAME);
    }
}
