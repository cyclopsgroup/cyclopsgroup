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
    /** Method getRootMenus()
     * @see com.cyclops.tornado.services.navigator.NavigatorService#getRootMenus()
     */
    public MenuRoot[] getRootMenus() {
        return (MenuRoot[]) menus.values().toArray(MenuRoot.EMPTY_ARRAY);
    }
    /** Method initialize()
     * @see com.cyclops.tornado.services.BaseService#initialize(org.apache.commons.configuration.Configuration)
     */
    protected void initialize(Configuration conf) throws Exception {
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
