/*
 * Created on 2003-9-29
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado.services.navigator;
/**
 * @author joeblack
 * @since 2003-9-29 21:19:39
 */
public class MenuItem extends Menu {
    public static final MenuItem[] EMPTY_ARRAY = new MenuItem[0];
    private boolean collapse;
    private String href;
    /** Method getHref() in Class MenuItem
        * @return
        */
    public String getHref() {
        return href;
    }
    /** Method getIsCollapse() in Class MenuItem
     * @return
     */
    public boolean getIsCollapse() {
        return collapse;
    }
    /** Method setCollapse() in Class MenuItem
     * @param b
     */
    public void setCollapse(boolean b) {
        collapse = b;
    }
    /** Method setHref() in Class MenuItem
     * @param string
     */
    public void setHref(String string) {
        href = string;
    }
}