/*
 * Created on 2003-10-16
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado;

/**
 * @author joeblack
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class TornadoUserTest extends TurbineTestCase {
    /**
     *  Test method
     */
    public void testAnonymous() {
        TornadoUser[] users = TornadoUser.getInstances();
        assertEquals(1, users.length);
        TornadoUser anonymous = users[0];
        assertEquals("guest", anonymous.getName());
        assertTrue(anonymous.isAnonymous());
    }
}
