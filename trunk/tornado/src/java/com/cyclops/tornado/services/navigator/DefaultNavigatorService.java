/*
 * Created on 2003-9-29
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado.services.navigator;
import java.net.URL;
import java.util.Hashtable;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.digester.Digester;
import org.apache.commons.lang.StringUtils;

import com.cyclops.tornado.BrokerManager;
import com.cyclops.tornado.services.BaseService;
import com.cyclops.tornado.utils.ResourceFinder;
/**
 * @author joeblack
 * @since 2003-9-29 23:20:58
 */
public class DefaultNavigatorService
    extends BaseService
    implements NavigatorService {
    private Hashtable menus = new Hashtable();
    private Menu getMenu(Menu menu, String href) {
        if (StringUtils.equals(menu.getHref(), href)) {
            return menu;
        } else {
            MenuItem[] children = menu.getChildren();
            for (int i = 0; i < children.length; i++) {
                MenuItem item = children[i];
                Menu ret = getMenu(item, href);
                if (ret != null) {
                    return ret;
                }
            }
            return null;
        }
    }
    /** Implementation of method getMenu() in this class
     * @see com.cyclops.tornado.services.navigator.NavigatorService#getMenu(java.lang.String)
     */
    public Menu getMenu(String href) {
        MenuRoot[] roots = getMenuRoots();
        for (int i = 0; i < roots.length; i++) {
            MenuRoot root = roots[i];
            Menu ret = getMenu(root, href);
            if (ret != null) {
                return ret;
            }
        }
        return null;
    }
    /** Method getRootMenus()
     * @see com.cyclops.tornado.services.navigator.NavigatorService#getRootMenus()
     */
    public MenuRoot[] getMenuRoots() {
        return (MenuRoot[]) menus.values().toArray(MenuRoot.EMPTY_ARRAY);
    }
    /** Implementation of method initialize() in this class
     * @see com.cyclops.tornado.services.BaseService#initialize(org.apache.commons.configuration.Configuration, com.cyclops.tornado.BrokerManager)
     */
    protected void initialize(Configuration conf, BrokerManager brokerManager)
        throws Exception {
        ResourceFinder rf = new ResourceFinder(this);
        URL[] resources = rf.getResources(conf);
        MenuItem root = new MenuItem();
        Digester digester = new Digester();
        digester.addObjectCreate("project", MenuProject.class);
        digester.addObjectCreate("project/menu", MenuRoot.class);
        digester.addSetNext("project/menu", "addMenu");
        digester.addSetProperties("project/menu");
        digester.addObjectCreate("*/item", MenuItem.class);
        digester.addSetProperties("*/item");
        digester.addSetNext("*/item", "addChild");
        digester.setNamespaceAware(false);
        for (int i = 0; i < resources.length; i++) {
            URL resource = resources[i];
            try {
                digester.clear();
                MenuProject project =
                    (MenuProject) digester.parse(resource.openStream());
                MenuRoot[] roots = project.getMenuRoots();
                for (int j = 0; j < roots.length; j++) {
                    MenuRoot menu = roots[j];
                    menus.put(menu.getName(), menu);
                }
            } catch (Exception e) {
                logger.debug("Resource " + resource + " loading failed!", e);
            }
        }
    }
}
