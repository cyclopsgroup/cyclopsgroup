/*
 * Created on 2003-10-20
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado.tools;
import java.util.List;

import com.cyclops.tornado.TurbineTestCase;
/**
 * @author jiaqi guo
 * @email g-cyclops@users.sourceforge.net
 */
public class NavigatorToolTest extends TurbineTestCase {
    /** Method testGetCurrentPath()
     */
    public void testGetCurrentPath() {
        NavigatorTool nt = new NavigatorTool();
        nt.init(null);
        List path = nt.getCurrentPath();
        assertEquals(1, path.size());
        nt.refresh("system/administration/user/Search.vm");
        path = nt.getCurrentPath();
        assertEquals(3, path.size());
    }
}
