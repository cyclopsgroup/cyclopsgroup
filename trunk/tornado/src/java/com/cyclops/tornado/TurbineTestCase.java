/*
 * Created on 2003-10-9
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado;
import junit.framework.TestCase;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.fulcrum.TurbineServices;
import org.apache.log4j.PropertyConfigurator;

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
        PropertiesConfiguration pc =
            new PropertiesConfiguration("src/webapp/WEB-INF/conf/tr.properties");
        SlowServiceManager tsm = new SlowServiceManager();
        TurbineServices.setManager(tsm);
        tsm.setConfiguration(pc);
        tsm.init();
    }
}
