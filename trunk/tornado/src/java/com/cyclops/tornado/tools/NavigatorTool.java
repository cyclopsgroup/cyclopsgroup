/*
 * Created on 2003-9-29
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado.tools;
import org.apache.fulcrum.TurbineServices;
import org.apache.turbine.services.pull.ApplicationTool;

import com.cyclops.tornado.services.navigator.NavigatorService;
/**
 * @author joeblack
 * @since 2003-9-29 19:16:44
 */
public class NavigatorTool implements ApplicationTool {
    private NavigatorService getService() {
        return (NavigatorService) TurbineServices.getInstance().getService(
            NavigatorService.SERVICE_NAME);
    }
    /** Method init()
     * @see org.apache.turbine.services.pull.ApplicationTool#init(java.lang.Object)
     */
    public void init(Object arg0) {
        // TODO Auto-generated method stub
    }
    /** Method refresh()
     * @see org.apache.turbine.services.pull.ApplicationTool#refresh()
     */
    public void refresh() {
        // TODO Auto-generated method stub
    }
}
