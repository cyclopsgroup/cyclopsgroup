/*
 * Created on 2003-10-20
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado.services.navigator;
import org.apache.fulcrum.TurbineServices;

import com.cyclops.tornado.TurbineTestCase;
/** Navigator Service can't work in test case
 * Still not figure out what's happening
 * @author jiaqi guo
 * @email g-cyclops@users.sourceforge.net
 */
public class NavigatorServiceTest extends TurbineTestCase {
    /** Method testGetMenuRoots()
    */
    public void testGetMenuRoots() {
        NavigatorService ns =
            (NavigatorService) TurbineServices.getInstance().getService(
                NavigatorService.SERVICE_NAME);
        MenuRoot[] roots = ns.getMenuRoots();
        assertEquals(2, roots.length);
    }
}
