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

import com.cyclops.tornado.ResourceFinder;
import com.cyclops.tornado.services.BaseService;
/**
 * @author joeblack
 * @since 2003-9-29 23:20:58
 */
public class DefaultNavigatorService extends BaseService {
    private Hashtable menus = new Hashtable();
    /** Method initialize()
     * @see com.cyclops.tornado.services.BaseService#initialize(org.apache.commons.configuration.Configuration)
     */
    protected void initialize(Configuration conf) throws Exception {
        ResourceFinder rf = new ResourceFinder(this);
        URL[] resources = rf.getResources(conf);
        MenuItem root = new MenuItem();
        Digester digester = new Digester();
        digester.addObjectCreate("menu", Menu.class);
        digester.addObjectCreate("item", MenuItem.class);
        digester.addSetProperties("item");
        digester.addSetNext("item", "addChild");
        for (int i = 0; i < resources.length; i++) {
            URL resource = resources[i];
            try {
                digester.clear();
                Menu menu = (Menu) digester.parse(resource.openStream());
                menus.put(menu.getName(), menu);
            } catch (Exception e) {
                logger.debug("Resource " + resource + " loading failed!", e);
            }
        }
    }
}
