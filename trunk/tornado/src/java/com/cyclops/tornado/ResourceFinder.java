/*
 * Created on 2003-9-29
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado;
import java.io.File;
import java.net.URL;
/**
 * @author joeblack
 * @since 2003-9-29 23:22:52
 */
public class ResourceFinder {
    public static final URL[] EMPTY_URL_ARRAY = new URL[0];
    private PathTransformable pathTransformer = new PathTransformable() {
        public String getRealPath(String relPath) {
            return new File(relPath).getAbsolutePath();
        }
    };
    public ResourceFinder() {
    }
    public ResourceFinder(PathTransformable pt) {
        pathTransformer = pt;
    }
}
