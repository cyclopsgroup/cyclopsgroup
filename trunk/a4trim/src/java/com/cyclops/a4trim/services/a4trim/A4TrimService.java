/*
 * Created on 2003-9-26
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.a4trim.services.a4trim;
import org.apache.fulcrum.Service;

import com.cyclops.a4trim.om.Category;
/**
 * @author joeblack
 * @since 2003-9-26 14:17:05
 *
 * Class
 */
public interface A4TrimService extends Service {
    /** Service name in fulcrum */
    String SERVICE_NAME = "A4TrimService";
    /** Empty Category array */
    Category[] EMPTY_CATEGORY_ARRAY = new Category[0];
    /** Method getRootCategories() in Class A4TrimService
     * @return Root categories
     */
    Category[] getRootCategories();
    /** Method getAdminPassword() in Class A4TrimService
     * @return Password for admin
     */
    String getAdminPassword();
    /** Method reload() in Class A4TrimService
     *  Reload category structure
     */
    void reload();
}
