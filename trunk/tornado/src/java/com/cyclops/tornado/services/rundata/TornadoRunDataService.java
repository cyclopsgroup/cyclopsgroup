/*
 * Created on 2003-10-9
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado.services.rundata;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.fulcrum.ServiceException;
import org.apache.fulcrum.security.entity.User;
import org.apache.turbine.RunData;
import org.apache.turbine.services.rundata.TurbineRunDataService;

import com.cyclops.tornado.services.user.UserService;
/**
 * @author joeblack
 * @since 2003-10-9 14:53:50
 */
public class TornadoRunDataService extends TurbineRunDataService {
    /** Method getRunData()
     * @see org.apache.turbine.services.rundata.RunDataService#getRunData(java.lang.String, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.ServletConfig)
     */
    public RunData getRunData(
        String arg0,
        HttpServletRequest arg1,
        HttpServletResponse arg2,
        ServletConfig arg3)
        throws ServiceException {
        RunData rundata = super.getRunData(arg0, arg1, arg2, arg3);
        UserService userService =
            (UserService) getServiceBroker().getService(
                UserService.SERVICE_NAME);
        User user =
            (User) userService.getActiveUser(rundata.getSession().getId());
        rundata.setUser(user);
        return rundata;
    }
}
