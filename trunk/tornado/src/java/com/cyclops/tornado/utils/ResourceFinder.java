/*
 * Created on 2003-9-29
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado.utils;
import java.io.File;
import java.io.FileFilter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.regexp.RE;
import org.apache.regexp.REUtil;
/**
 * @author joeblack
 * @since 2003-9-29 23:22:52
 */
public class ResourceFinder {
    private static Log logger = LogFactory.getLog(ResourceFinder.class);
    /** Empty URL object array */
    public static final URL[] EMPTY_URL_ARRAY = new URL[0];
    private PathTransformable pathTransformer = new PathTransformable() {
        public String getRealPath(String relPath) {
            return new File(relPath).getAbsolutePath();
        }
    };
    /** Constructor with a specified PathTransformer
     * @param pt PathTransformer object
     */
    public ResourceFinder(PathTransformable pt) {
        pathTransformer = pt;
    }
    /** Method getResources() in Class ResourceFinder
     * @param conf Configuration object contains the information of resources to be find
     * @return Array of resources in form of URL object
     */
    public URL[] getResources(Configuration conf) {
        ArrayList urls = new ArrayList();
        String pattern = conf.getString("pattern", "*");
        for (Iterator i = conf.getKeys(); i.hasNext();) {
            String key = (String) i.next();
            String[] paths = conf.getStringArray(key);
            for (int j = 0; j < paths.length; j++) {
                String path = paths[j];
                try {
                    if (StringUtils.equals(key, "resource")) {
                        urls.add(getClass().getClassLoader().getResource(path));
                    } else if (StringUtils.equals(key, "file")) {
                        URL url = getFileURL(pathTransformer.getRealPath(path));
                        if (url != null) {
                            urls.add(url);
                        }
                    } else if (StringUtils.equals(key, "localfile")) {
                        URL url = getFileURL(path);
                        if (url != null) {
                            urls.add(url);
                        }
                    } else if (StringUtils.equals(key, "path")) {
                        CollectionUtils.addAll(
                            urls,
                            getPathURLs(
                                pathTransformer.getRealPath(path),
                                pattern));
                    } else if (StringUtils.equals(key, "localpath")) {
                        CollectionUtils.addAll(
                            urls,
                            getPathURLs(path, pattern));
                    }
                } catch (Exception e) {
                    logger.error("Can't load resource " + path, e);
                }
            }
        }
        return (URL[]) urls.toArray(EMPTY_URL_ARRAY);
    }
    private URL getFileURL(String path) throws MalformedURLException {
        File file = new File(path);
        if (file.isFile()) {
            return file.toURL();
        } else {
            return null;
        }
    }
    private URL[] getPathURLs(String path, final String pattern)
        throws MalformedURLException {
        List ret = new ArrayList();
        File dir = new File(path);
        if (dir.isDirectory()) {
            File[] files = dir.listFiles(new FileFilter() {
                public boolean accept(File file) {
                    try {
                        RE re = REUtil.createRE("<" + pattern + ">");
                        return re.match(file.getName());
                    } catch (Exception e) {
                        return false;
                    }
                }
            });
            for (int i = 0; i < files.length; i++) {
                File file = files[i];
                ret.add(file.toURL());
            }
        }
        return (URL[]) ret.toArray(EMPTY_URL_ARRAY);
    }
}
