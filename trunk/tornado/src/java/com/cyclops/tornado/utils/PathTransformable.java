/*
 * Created on 2003-9-29
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado.utils;
/**
 * @author joeblack
 * @since 2003-9-29 23:24:30
 */
public interface PathTransformable {
    /** Method getRealPath() in Class PathTransformer
     * @param relPath Relative path
     * @return Real path
     */
    String getRealPath(String relPath);
}