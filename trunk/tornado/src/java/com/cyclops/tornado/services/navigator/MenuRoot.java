/*
 * Created on 2003-10-18
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado.services.navigator;
/**
 * @author jiaqi guo
 * @email g-cyclops@users.sourceforge.net
 */
public class MenuRoot extends Menu {
    /** Empty MenuRoot array */
    public static final MenuRoot[] EMPTY_ARRAY = new MenuRoot[0];
    private boolean isDefault;
    /** Same to getDefault()
     * @return If this root is default
     */
    public boolean getDefault() {
        return isDefault;
    }
    /** Method isDefault()
     * @return If this root is default
     */
    public boolean isDefault() {
        return isDefault;
    }
    /** Method setDefault()
     * @param b Is default value
     */
    public void setDefault(boolean b) {
        isDefault = b;
    }

}
