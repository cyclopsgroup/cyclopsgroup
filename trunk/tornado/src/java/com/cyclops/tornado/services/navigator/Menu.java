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
    private static int uniqueId = 0;
    private Vector children = new Vector();
    private String href;
    private int id;
    private String name;
    private Menu parent;
    /** Default constructor for menu object
     */
    public Menu() {
        id = ++uniqueId;
    }
    /** Method addChild() in Class Menu
     * @param item MenuItem child object
     */
    public void addChild(MenuItem item) {
        item.setParent(this);
        children.add(item);
    }
    /** Method getChildren() in Class Menu
     * @return Array of MenuItem children
     */
    public MenuItem[] getChildren() {
        return (MenuItem[]) children.toArray(MenuItem.EMPTY_ARRAY);
    }
    /** Method getHref() in Class MenuItem
        * @return Href of this node
        */
    public String getHref() {
        return href;
    }
    /** Method getName() in Class Menu
     * @return Name of this node
     */
    public String getName() {
        return name;
    }
    /** Method getParent()
     * @return Parent node
     */
    public Menu getParent() {
        return parent;
    }
    /** Method setHref() in Class MenuItem
     * @param string New value of href
     */
    public void setHref(String string) {
        href = string;
    }
    /** Method setName() in Class Menu
     * @param string New value of node
     */
    public void setName(String string) {
        name = string;
    }
    /** Method setParent()
     * @param menu Parent node
     */
    public void setParent(Menu menu) {
        parent = menu;
    }
}
