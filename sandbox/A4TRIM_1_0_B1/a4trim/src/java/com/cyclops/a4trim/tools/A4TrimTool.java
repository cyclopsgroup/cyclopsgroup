/*
 * Created on 2003-9-26
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.a4trim.tools;
import org.apache.commons.lang.StringUtils;
import org.apache.fulcrum.TurbineServices;
import org.apache.turbine.services.pull.ApplicationTool;

import com.cyclops.a4trim.om.Category;
import com.cyclops.a4trim.services.a4trim.A4TrimService;
/**
 * @author joeblack
 * @since 2003-9-26 14:22:49
 *
 * Class A4TrimTool
 */
public class A4TrimTool implements ApplicationTool {
    private boolean isAdmin = false;
    private A4TrimService getService() {
        return (A4TrimService) TurbineServices.getInstance().getService(
            A4TrimService.SERVICE_NAME);
    }
    /** Method init()
     * @see org.apache.turbine.services.pull.ApplicationTool#init(java.lang.Object)
     */
    public void init(Object arg0) {
        //do nothing when init
    }
    /** Method refresh()
     * @see org.apache.turbine.services.pull.ApplicationTool#refresh()
     */
    public void refresh() {
        //do nothing
    }
    /** Method getRootCategories() in Class A4TrimTool
     * @return Array of root categories
     */
    public Category[] getRootCategories() {
        return getService().getRootCategories();
    }
    /** Method reloadRootCategories() in Class A4TrimTool */
    public void reloadRootCategories() {
        getService().reload();
    }
    /** Method adminSignIn() in Class A4TrimTool
     * @param password Password of admin
     * @return If successful
     */
    public boolean adminSignIn(String password) {
        if (StringUtils.equals(password, getService().getAdminPassword())) {
            isAdmin = true;
        }
        return isAdmin;
    }
    /** Method adminSignOut() in Class A4TrimTool */
    public void adminSignOut() {
        isAdmin = false;
    }
    /** Method getIsAdmin() in Class A4TrimTool
     * @return If it's admin now
     */
    public boolean getIsAdmin() {
        return isAdmin;
    }
}
