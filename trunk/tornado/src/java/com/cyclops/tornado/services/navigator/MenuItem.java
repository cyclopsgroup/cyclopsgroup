/*
 * Created on 2003-9-29
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado.services.navigator;
import java.util.Vector;
/**
 * @author joeblack
 * @since 2003-9-29 21:19:39
 */
public class MenuItem {
    /** Empty MenuItem object array */
    public static final MenuItem[] EMPTY_ARRAY = new MenuItem[0];
    private Vector children = new Vector();
    private String href;
    private MenuItem parent;
    private String text;
    /** Method addChild() in Class MenuItem
     * @param item Node item to be add
     */
    public void addChild(MenuItem item) {
        children.add(item);
        item.setParent(this);
    }
    /** Method getChildren() in Class MenuItem
     * @return Array of children objects
     */
    public MenuItem[] getChildren() {
        return (MenuItem[]) children.toArray(EMPTY_ARRAY);
    }
    /** Method getHref() in Class MenuItem
     * @return Href of this menu item
     */
    public String getHref() {
        return href;
    }
    /** Method getParent() in Class MenuItem
     * @return Parent node, could be null
     */
    public MenuItem getParent() {
        return parent;
    }
    /** Method getText() in Class MenuItem
     * @return Text message of this node
     */
    public String getText() {
        return text;
    }
    /** Method removeChild() in Class MenuItem
     * @param item Node item to be removed
     */
    public void removeChild(MenuItem item) {
        children.remove(item);
        item.setParent(null);
    }
    /** Method setHref() in Class MenuItem
     * @param string New href value
     */
    public void setHref(String string) {
        href = string;
    }
    /** Method setParent() in Class MenuItem
     * @param item New parent object
     */
    public void setParent(MenuItem item) {
        parent = item;
    }
    /** Method setText() in Class MenuItem
     * @param string New text value
     */
    public void setText(String string) {
        text = string;
    }
}
