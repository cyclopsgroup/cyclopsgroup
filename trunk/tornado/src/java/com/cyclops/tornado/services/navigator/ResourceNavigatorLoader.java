/*
 * Created on 2003-11-7
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado.services.navigator;
import java.net.URL;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.digester.Digester;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cyclops.tornado.utils.ResourceFinder;
/**
 * @author jiaqi guo
 * @email g-cyclops@users.sourceforge.net
 */
public class ResourceNavigatorLoader implements NavigatorLoader {
    private Log logger = LogFactory.getLog(getClass());
    /** Implementation of method load() in this class
     * @see com.cyclops.tornado.services.navigator.NavigatorLoader#load(org.apache.commons.configuration.Configuration)
     */
    public MenuProject load(Configuration conf) {
        String[] names = conf.getStringArray("name");
        URL[] urls = ResourceFinder.getResources(getClass(), names);
        Digester digester = new Digester();
        digester.addObjectCreate("project/menu", MenuRoot.class);
        digester.addSetNext("project/menu", "addMenu");
        digester.addSetProperties("project/menu");
        digester.addObjectCreate("*/item", MenuItem.class);
        digester.addSetProperties("*/item");
        digester.addSetNext("*/item", "addChild");
        MenuProject ret = new MenuProject();
        digester.push(ret);
        for (int i = 0; i < urls.length; i++) {
            URL url = urls[i];
            try {
                digester.parse(url.openStream());
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("Parse resource [" + url + "] error", e);
            }
        }
        return ret;
    }
}
