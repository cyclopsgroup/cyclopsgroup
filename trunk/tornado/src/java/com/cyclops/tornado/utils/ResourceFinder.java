/*
 * Created on 2003-9-29
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado.utils;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * @author joeblack
 * @since 2003-9-29 23:22:52
 */
public final class ResourceFinder {
    /** Empty URL object array */
    public static final URL[] EMPTY_URL_ARRAY = new URL[0];
    private static Log logger = LogFactory.getLog(ResourceFinder.class);
    private static final URL findResource(Class clazz, String name) {
        String className =
            StringUtils.replaceOnce(
                clazz.getName(),
                clazz.getPackage().getName() + ".",
                "");
        return clazz.getResource("meta/" + className + "/" + name);
    }
    /** Find resource for a specified class
     * @param clazz Class object
     * @param name Resource name
     * @return URL object, null if not found
     */
    public static final URL getResource(Class clazz, String name) {
        Class c = clazz;
        URL ret = null;
        while (ret == null && c != null) {
            ret = findResource(c, name);
            c = c.getSuperclass();
        }
        return ret;
    }
    /** Find resources for a specified class
     * @param clazz Class object
     * @param names Resource name array
     * @return URL array result
     */
    public static final URL[] getResources(Class clazz, String[] names) {
        List ret = new ArrayList();
        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            URL url = getResource(clazz, name);
            if (url != null) {
                ret.add(url);
            }
        }
        return (URL[]) ret.toArray(EMPTY_URL_ARRAY);
    }
    private ResourceFinder() {
        //do nothing
    }
}
