/*
 * Created on 2003-9-29
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado.tools;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.fulcrum.TurbineServices;
import org.apache.turbine.RunData;
import org.apache.turbine.services.pull.ApplicationTool;

import com.cyclops.tornado.Passport;
import com.cyclops.tornado.TornadoUser;
import com.cyclops.tornado.modules.ScreenAsset;
import com.cyclops.tornado.services.navigator.Menu;
import com.cyclops.tornado.services.navigator.MenuItem;
import com.cyclops.tornado.services.navigator.MenuRoot;
import com.cyclops.tornado.services.navigator.NavigatorService;
/**
 * @author joeblack
 * @since 2003-9-29 19:16:44
 */
public class NavigatorTool implements ApplicationTool {
    private LinkedList currentPath = new LinkedList();
    private MenuRoot defaultRoot;
    private Passport passport = null;
    private boolean checkHref(String href) {
        if (passport == null) {
            return true;
        } else {
            ScreenAsset sa = new ScreenAsset(href);
            return passport.accept(sa);
        }
    }
    /** Method getChildren() in Class NavigatorTool
     * @param menu Menu item
     * @return list of MenuItem objects
     */
    public List getChildren(Menu menu) {
        if (menu == null) {
            return Collections.EMPTY_LIST;
        }
        ArrayList ret = new ArrayList();
        MenuItem[] items = menu.getChildren();
        for (int i = 0; i < items.length; i++) {
            MenuItem item = items[i];
            if (checkHref(item.getHref())) {
                ret.add(item);
            }
        }
        return ret;
    }
    /** Method getCurrentMenu()
     * @return Current menu object
     */
    public Menu getCurrentMenu() {
        return (Menu) currentPath.get(currentPath.size() - 1);
    }
    /** Method getCurrentMenuRoot()
     * @return Current menu root object
     */
    public MenuRoot getCurrentMenuRoot() {
        return (MenuRoot) currentPath.get(0);
    }
    /** Method getCurrentPath()
     * @return Current postion
     */
    public List getCurrentPath() {
        return Collections.unmodifiableList(currentPath);
    }
    /** Get roots
     * @return RootMenu object list
     */
    public List getMenuRoots() {
        List ret = new ArrayList();
        MenuRoot[] roots = getNavigatorService().getMenuRoots();
        for (int i = 0; i < roots.length; i++) {
            MenuRoot root = roots[i];
            if (checkHref(root.getHref())) {
                ret.add(root);
            }
        }
        return ret;
    }
    /** Method getNavigatorService() in Class NavigatorTool
     * @return NavigatorService instance
     */
    public NavigatorService getNavigatorService() {
        return (NavigatorService) TurbineServices.getInstance().getService(
            NavigatorService.SERVICE_NAME);
    }
    /** Method init()
     * @see org.apache.turbine.services.pull.ApplicationTool#init(java.lang.Object)
     */
    public void init(Object initObject) {
        List roots = getMenuRoots();
        for (Iterator i = roots.iterator(); i.hasNext();) {
            MenuRoot root = (MenuRoot) i.next();
            if (root.isDefault()) {
                defaultRoot = root;
            }
        }
        if (defaultRoot == null && !roots.isEmpty()) {
            defaultRoot = (MenuRoot) roots.get(0);
        }
        refresh("Index.vm");
    }
    /** Method refresh()
     * @see org.apache.turbine.services.pull.ApplicationTool#refresh()
     */
    public void refresh() {
    }
    /** Called in page
     * @param data RunData object
     */
    public void refresh(RunData data) {
        refresh(data.getTarget());
        TornadoUser user = TornadoUser.getInstance(data);
        passport = user.getPassport();
    }
    /** Referesh tool with current position
     * @param target Current template
     */
    public void refresh(String target) {
        String href = StringUtils.join(StringUtils.split(target, "/"), ",");
        Menu item = getNavigatorService().getMenu(href);
        if (item == null) {
            return;
        }
        currentPath.clear();
        Menu parent = item;
        while (parent != null) {
            currentPath.add(0, parent);
            parent = parent.getParent();
        }
        if (currentPath.isEmpty()) {
            currentPath.add(defaultRoot);
        }
    }
}
