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

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.fulcrum.TurbineServices;
import org.apache.turbine.services.pull.ApplicationTool;

import com.cyclops.tornado.services.navigator.Menu;
import com.cyclops.tornado.services.navigator.MenuRoot;
import com.cyclops.tornado.services.navigator.NavigatorService;
/**
 * @author joeblack
 * @since 2003-9-29 19:16:44
 */
public class NavigatorTool implements ApplicationTool {
    private LinkedList currentPath = new LinkedList();
    private MenuRoot defaultRoot;
    /** Method getChildren() in Class NavigatorTool
     * @param menu Menu item
     * @return list of MenuItem objects
     */
    public List getChildren(Menu menu) {
        List ret = new ArrayList();
        CollectionUtils.addAll(ret, menu.getChildren());
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
        CollectionUtils.addAll(ret, getNavigatorService().getMenuRoots());
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
        for (Iterator i = getMenuRoots().iterator(); i.hasNext();) {
            MenuRoot root = (MenuRoot) i.next();
            if (root.isDefault()) {
                defaultRoot = root;
            }
        }
        refresh("Index.vm");
    }
    /** Method refresh()
     * @see org.apache.turbine.services.pull.ApplicationTool#refresh()
     */
    public void refresh() {
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
