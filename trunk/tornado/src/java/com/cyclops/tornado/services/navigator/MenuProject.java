/*
 * Created on 2003-10-18
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado.services.navigator;
import java.util.ArrayList;
/**
 * @author jiaqi guo
 * @email g-cyclops@users.sourceforge.net
 */
public class MenuProject {
    private ArrayList menuRoots = new ArrayList();
    /** Method addMenu()
     * @param root MenuRoot to add
     */
    public void addMenu(MenuRoot root) {
        menuRoots.add(root);
    }
    /** Method getMenuRoots()
     * @return Roots in project
     */
    public MenuRoot[] getMenuRoots() {
        return (MenuRoot[]) menuRoots.toArray(MenuRoot.EMPTY_ARRAY);
    }
}
