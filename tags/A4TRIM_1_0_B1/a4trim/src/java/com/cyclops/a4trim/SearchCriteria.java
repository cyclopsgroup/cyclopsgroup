/*
 * Created on 2003-9-25
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.a4trim;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
/**
 * @author joeblack
 * @since 2003-9-25 16:01:13
 *
 * Class SearchCriteria
 */
public class SearchCriteria {
    private static final String[] EMPTY_STRING_ARRAY = new String[0];
    /** Key of this object in session */
    public static final String KEY =
        SearchCriteria.class.getName() + "@session";
    /** Default value of page size */
    public static final int DEFAULT_PAGE_SIZE = 20;
    private String keyword = "";
    private String productCode = "";
    private int pageSize = DEFAULT_PAGE_SIZE;
    private HashSet categoryPaths = new HashSet();
    /** Method getKeyword() in Class SearchCriteria
     * @return Current keyword value
     */
    public String getKeyword() {
        return keyword;
    }
    /** Method getProductCode() in Class SearchCriteria
     * @return Current product code value
     */
    public String getProductCode() {
        return productCode;
    }
    /** Method setKeyword() in Class SearchCriteria
     * @param string New keyword value
     */
    public void setKeyword(String string) {
        keyword = string;
    }
    /** Method setProductCode() in Class SearchCriteria
     * @param string New product code value
     */
    public void setProductCode(String string) {
        productCode = string;
    }
    /** Method addCategoryPath() in Class SearchCriteria
     * @param path Category path
     */
    public void addCategoryPath(String path) {
        boolean addIt = true;
        List tobeRemoved = new ArrayList();
        for (Iterator i = categoryPaths.iterator(); i.hasNext();) {
            String p = (String) i.next();
            if (path.startsWith(p)) {
                addIt = false;
            }
            if (p.startsWith(path) && !StringUtils.equals(p, path)) {
                tobeRemoved.add(p);
            }
        }
        categoryPaths.removeAll(tobeRemoved);
        if (addIt) {
            categoryPaths.add(path);
        }
    }
    /** Method getCategoryPaths() in Class SearchCriteria
     * @return Set of category paths
     */
    public Set getCategoryPaths() {
        return Collections.unmodifiableSet(categoryPaths);
    }
    /** Method getPageSize() in Class SearchCriteria
     * @return Page size value
     */
    public int getPageSize() {
        return pageSize;
    }
    /** Method setPageSize() in Class SearchCriteria
     * @param i New page size value
     */
    public void setPageSize(int i) {
        pageSize = i;
    }
    /** Method containsRootCategory() in Class SearchCriteria
     * @return Whether or not this search criteria contains root category
     */
    public boolean containsRootCategory() {
        boolean ret = false;
        for (Iterator i = categoryPaths.iterator(); i.hasNext();) {
            String path = (String) i.next();
            if (StringUtils.equals("/", path)) {
                ret = true;
                break;
            }
        }
        return ret;
    }
}
