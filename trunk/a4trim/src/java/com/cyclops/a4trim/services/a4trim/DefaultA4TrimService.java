/*
 * Created on 2003-9-26
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.a4trim.services.a4trim;
import java.util.Collections;
import java.util.List;

import org.apache.fulcrum.BaseService;
import org.apache.torque.util.Criteria;

import com.cyclops.a4trim.om.Category;
import com.cyclops.a4trim.om.CategoryPeer;
/**
 * @author joeblack
 * @since 2003-9-26 14:19:17
 *
 * Class
 */
public class DefaultA4TrimService
    extends BaseService
    implements A4TrimService {
    private List rootCategories = Collections.EMPTY_LIST;
    private String adminPassword = "a4trim";
    /** Method init()
     * @see org.apache.fulcrum.Service#init()
     */
    public void init() {
        adminPassword =
            getConfiguration().getString("admin.password", adminPassword);
        reload();
        setInit(true);
    }
    /** Method getRootCategories()
     * @see com.cyclops.a4trim.services.a4trim.A4TrimService#getRootCategories()
     */
    public Category[] getRootCategories() {
        return (Category[]) rootCategories.toArray(EMPTY_CATEGORY_ARRAY);
    }
    /** Method reload()
     * @see com.cyclops.a4trim.services.a4trim.A4TrimService#reload()
     */
    public void reload() {
        Criteria crit = new Criteria();
        crit.and(CategoryPeer.PARENT_ID, -1);
        crit.and(CategoryPeer.DEPTH, 0);
        try {
            rootCategories = CategoryPeer.doSelect(crit);
        } catch (Exception e) {
            getCategory().error("Can't reload root categories", e);
        }
    }
    /** Method getAdminPassword()
     * @see com.cyclops.a4trim.services.a4trim.A4TrimService#getAdminPassword()
     */
    public String getAdminPassword() {
        return adminPassword;
    }
}
