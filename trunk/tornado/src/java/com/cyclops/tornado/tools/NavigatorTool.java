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
import com.cyclops.tornado.services.navigator.MenuRoot;
import com.cyclops.tornado.services.navigator.NavigatorService;
/**
 * @author joeblack
 * @since 2003-9-29 19:16:44
 */
public class NavigatorTool implements ApplicationTool {
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
    /** Get roots
     * @return RootMenu object array
     */
    public MenuRoot[] getRootMenus() {
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
    /** Method refresh()
     * @see org.apache.turbine.services.pull.ApplicationTool#refresh()
     */
    public void refresh() {
        // TODO Auto-generated method stub
    }
}
