/*
 * Created on 2003-9-29
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado.tools;
import org.apache.fulcrum.TurbineServices;
import org.apache.turbine.services.pull.ApplicationTool;

import com.cyclops.tornado.services.navigator.Menu;
import com.cyclops.tornado.services.navigator.MenuItem;
import com.cyclops.tornado.services.navigator.NavigatorService;
import com.cyclops.tornado.services.user.User;
import com.cyclops.tornado.services.user.UserListener;
/**
 * @author joeblack
 * @since 2003-9-29 19:16:44
 */
public class NavigatorTool implements ApplicationTool, UserListener {
    /** Method getChildren() in Class NavigatorTool
     * @param menu Menu item
     * @return Array of MenuItem objects
     */
    public MenuItem[] getChildren(Menu menu) {
        return menu.getChildren();
    }
    /** Method getNavigatorService() in Class NavigatorTool
     * @return NavigatorService instance
     */
    public NavigatorService getNavigatorService() {
        return (NavigatorService) TurbineServices.getInstance().getService(
            NavigatorService.SERVICE_NAME);
    }
    public Menu[] getRootMenus() {
        return getNavigatorService().getRootMenus();
    }
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
    /** Method onSingIn()
     * @see com.cyclops.tornado.services.user.UserListener#onSingIn(com.cyclops.tornado.services.user.User)
     */
    public void onSingIn(User user) {
        // TODO Auto-generated method stub
    }
    /** Method onSingOut()
     * @see com.cyclops.tornado.services.user.UserListener#onSingOut(com.cyclops.tornado.services.user.User)
     */
    public void onSingOut(User user) {
        // TODO Auto-generated method stub
    }

    /** Method refresh()
     * @see org.apache.turbine.services.pull.ApplicationTool#refresh()
     */
    public void refresh() {
        // TODO Auto-generated method stub
    }

}
