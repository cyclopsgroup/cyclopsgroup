/*
 * Created on 2003-9-29
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado.services.navigator;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.lang.StringUtils;

import com.cyclops.tornado.BrokerManager;
import com.cyclops.tornado.services.BaseService;
import com.cyclops.tornado.services.Restartable;
/**
 * @author joeblack
 * @since 2003-9-29 23:20:58
 */
public class DefaultNavigatorService
    extends BaseService
    implements NavigatorService, Restartable {
    private List menus = new ArrayList();
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
        return (MenuRoot[]) menus.toArray(MenuRoot.EMPTY_ARRAY);
    }
    /** Implementation of method initialize() in this class
     * @see com.cyclops.tornado.services.BaseService#initialize(org.apache.commons.configuration.Configuration, com.cyclops.tornado.BrokerManager)
     */
    protected void initialize(Configuration conf, BrokerManager brokerManager)
        throws Exception {
        String[] loaderNames = conf.getStringArray("loader");
        for (int i = 0; i < loaderNames.length; i++) {
            String loaderName = loaderNames[i];
            Configuration loaderConf = conf.subset("loader." + loaderName);
            try {
                NavigatorLoader loader =
                    (NavigatorLoader) Class
                        .forName(loaderConf.getString("classname"))
                        .newInstance();
                MenuProject project = loader.load(loaderConf);
                MenuRoot[] roots = project.getMenuRoots();
                for (int j = 0; j < roots.length; j++) {
                    MenuRoot menu = roots[j];
                    menus.add(menu);
                }
            } catch (Exception e) {
                logger.error("Loader error name=" + loaderName);
            }
        }
    }
    /** Implementation of method shutdown() in this class
     * @see org.apache.fulcrum.Service#shutdown()
     */
    public void shutdown() {
        menus.clear();
        super.shutdown();
    }
}
