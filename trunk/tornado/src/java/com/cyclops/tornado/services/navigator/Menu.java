/*
 * Created on 2003-10-6
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado.services.navigator;
import java.util.Vector;
/**
 * @author joeblack
 * @since 2003-10-6 15:39:37
 */
public class Menu {
    private Vector children = new Vector();
    private String name;
    /** Method addChild() in Class Menu
     * @param item MenuItem child object
     */
    public void addChild(MenuItem item) {
        children.add(item);
    }
    /** Method getChildren() in Class Menu
     * @return Array of MenuItem children
     */
    public MenuItem[] getChildren() {
        return (MenuItem[]) children.toArray(MenuItem.EMPTY_ARRAY);
    }
    /** Method getName() in Class Menu
     * @return Name of this node
     */
    public String getName() {
        return name;
    }
    /** Method setName() in Class Menu
     * @param string New value of node
     */
    public void setName(String string) {
        name = string;
    }
}
