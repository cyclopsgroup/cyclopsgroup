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
    /** Empty MenuItem node array */
    public static final MenuItem[] EMPTY_ARRAY = new MenuItem[0];
    private boolean collapse;
    private String href;
    /** Method getHref() in Class MenuItem
        * @return Href of this node
        */
    public String getHref() {
        return href;
    }
    /** Method getIsCollapse() in Class MenuItem
     * @return If this node collapse
     */
    public boolean getIsCollapse() {
        return collapse;
    }
    /** Method setCollapse() in Class MenuItem
     * @param b New collapse value
     */
    public void setCollapse(boolean b) {
        collapse = b;
    }
    /** Method setHref() in Class MenuItem
     * @param string New value of href
     */
    public void setHref(String string) {
        href = string;
    }
}