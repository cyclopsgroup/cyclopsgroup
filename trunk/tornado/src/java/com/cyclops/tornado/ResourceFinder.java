/*
 * Created on 2003-9-29
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.lang.StringUtils;
/**
 * @author joeblack
 * @since 2003-9-29 23:22:52
 */
public class ResourceFinder {
    /** Empty URL object array */
    public static final URL[] EMPTY_URL_ARRAY = new URL[0];
    private PathTransformable pathTransformer = new PathTransformable() {
        public String getRealPath(String relPath) {
            return new File(relPath).getAbsolutePath();
        }
    };
    /** Default constructor */
    public ResourceFinder() {
    }
    /** Constructor with a specified PathTransformer
     * @param pt PathTransformer object
     */
    public ResourceFinder(PathTransformable pt) {
        pathTransformer = pt;
    }
    public URL[] getResources(Configuration conf) {
        ArrayList urls = new ArrayList();
        for (Iterator i = conf.getKeys(); i.hasNext();) {
            String key = (String) i.next();
            String[] paths = conf.getStringArray(key);
            for (int j = 0; j < paths.length; j++) {
                String path = paths[j];
                try {
                    if (StringUtils.equals(key, "resource")) {
                        urls.add(getClass().getClassLoader().getResource(path));
                    } else if (StringUtils.equals(key, "file")) {
                        File file = new File(pathTransformer.getRealPath(path));
                        if (file.isFile()) {
                            urls.add(file.toURL());
                        }
                    } else if (StringUtils.equals(key, "localfile")) {
                        File file = new File(path);
                        if (file.isFile()) {
                            urls.add(file.toURL());
                        }
                    }
                } catch (Exception e) {
                }
            }
        }
        return (URL[]) urls.toArray(EMPTY_URL_ARRAY);
    }
}
