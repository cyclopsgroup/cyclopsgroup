/*
 * Created on 2003-9-29
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado.services.navigator;
import org.apache.fulcrum.Service;
/**
 * @author joeblack
 * @since 2003-9-29 19:17:32
 */
public interface NavigatorService extends Service {
    /** Service name in fulcrum container */
    String SERVICE_NAME = "NavigatorService";
    /** Method getMenuToot() in Class NavigatorService
     * @return Array of root menus
     */
    MenuRoot[] getMenuRoots();
    /** Method getMenu()
     * @param href  Href of the item
     * @return Menu object, null if not found
     */
    Menu getMenu(String href);
}
