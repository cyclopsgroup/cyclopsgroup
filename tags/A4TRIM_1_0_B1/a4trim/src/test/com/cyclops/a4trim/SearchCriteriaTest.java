/*
 * Created on 2003-9-25
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.a4trim;
import junit.framework.TestCase;
/**
 * @author joeblack
 * @since 2003-9-25 16:11:10
 */
public class SearchCriteriaTest extends TestCase {
    /** Method testAddCategoryPath() in Class SearchCriteriaTest */
    public void testAddCategoryPath() {
        SearchCriteria sc = new SearchCriteria();
        sc.addCategoryPath("/a/");
        sc.addCategoryPath("/b");
        sc.addCategoryPath("/a/c/");
        assertEquals(2, sc.getCategoryPaths().size());
        sc.addCategoryPath("/");
        assertEquals(1, sc.getCategoryPaths().size());
        sc.addCategoryPath("/d");
        assertEquals(1, sc.getCategoryPaths().size());
    }
}
