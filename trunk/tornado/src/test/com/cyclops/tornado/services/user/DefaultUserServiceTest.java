/*
 * Created on 2003-9-30
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado.services.user;
import org.apache.fulcrum.TurbineServices;

import com.cyclops.tornado.TurbineTestCase;
/**
 * @author joeblack
 * @since 2003-9-30 15:49:47
 */
public class DefaultUserServiceTest extends TurbineTestCase {
    /** Method testAnonymous() in Class DefaultUserServiceTest
     */
    public void testAnonymous() {
        UserService userService =
            (UserService) TurbineServices.getInstance().getService(
                UserService.SERVICE_NAME);
        assertNotNull(userService.getAnonymousUser());
        assertTrue(userService.getAnonymousUser().isAnonymous());
        assertEquals("guest", userService.getAnonymousUser().getName());
    }
}
