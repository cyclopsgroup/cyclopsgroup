/*
 * Created on 2003-11-7
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado.utils;
import junit.framework.TestCase;
/**
 * @author jiaqi guo
 * @email g-cyclops@users.sourceforge.net
 */
public class ResourceFinderTest extends TestCase {
    private class T extends ResourceFinderTest {
        //nothing
    }
    /** Method testFindResource()
     */
    public void testFindResource() {
        assertNull(ResourceFinder.getResource(getClass(), "abc.xml"));
        assertNotNull(ResourceFinder.getResource(getClass(), "ttt.xml"));
        assertNotNull(ResourceFinder.getResource(T.class, "ttt.xml"));
    }
}
