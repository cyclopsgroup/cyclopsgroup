/*
 * Created on 2003-10-16
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado.services.rundata;

import org.apache.fulcrum.TurbineServices;
import org.apache.turbine.services.rundata.DefaultTurbineRunData;

import com.cyclops.tornado.services.user.User;
import com.cyclops.tornado.services.user.UserService;

/**
 * @author joeblack
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class TornadoRunData extends DefaultTurbineRunData {
    /**
     * @return Tornado user object
     */
    public User getTornadoUser() {
        UserService userService =
            (UserService) TurbineServices.getInstance().getService(
                UserService.SERVICE_NAME);
        return userService.getActiveUser(getSession().getId());
    }
}
