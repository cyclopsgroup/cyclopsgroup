/*
 * Created on 2003-9-30
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado.services.user;
import junit.framework.TestCase;

import org.apache.commons.configuration.BaseConfiguration;

import com.cyclops.tornado.User;
/**
 * @author joeblack
 * @since 2003-9-30 15:49:47
 */
public class DefaultUserServiceTest extends TestCase {
    private DefaultUserService userService;
    /** Method testAnonymous() in Class DefaultUserServiceTest
     */
    public void testAnonymous() {
        assertNotNull(userService.getAnonymousUser());
        assertTrue(userService.getAnonymousUser().isAnonymous());
        assertEquals("guest", userService.getAnonymousUser().getName());
    }
    /** Method setUp()
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        BaseConfiguration bc = new BaseConfiguration();
        bc.setProperty("user.timeout", "500");
        bc.setProperty("user.classname", User.class.getName());
        userService = new DefaultUserService();
        userService.setConfiguration(bc);
        userService.initialize(bc);
    }
    /** Method tearDown()
     * @see junit.framework.TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        userService.shutdown();
        super.tearDown();
    }
}
