/*
 * Created on 2003-10-9
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado;
import java.io.FileInputStream;

import junit.framework.TestCase;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.fulcrum.TurbineServices;

import com.cyclops.tornado.services.SlowServiceManager;
/**
 * @author joeblack
 * @since 2003-10-9 15:10:33
 */
public class TurbineTestCase extends TestCase {
    /** Method setUp()
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        SlowServiceManager tsm = new SlowServiceManager();
        PropertiesConfiguration pc = new PropertiesConfiguration();
        pc.load(new FileInputStream("src/webapp/WEB-INF/conf/tr.properties"));
        tsm.setConfiguration(pc);
        tsm.init();
        TurbineServices.setManager(tsm);
    }
}
