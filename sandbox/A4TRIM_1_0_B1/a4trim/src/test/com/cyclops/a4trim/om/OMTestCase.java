/*
 * Created on 2003-9-23
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.a4trim.om;

import java.io.FileInputStream;

import junit.framework.TestCase;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.torque.Torque;

/**
 * @author joeblack
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class OMTestCase extends TestCase {

    /** Method setUp()
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        if (!Torque.isInit()) {
            PropertiesConfiguration pc = new PropertiesConfiguration();
            pc.load(
                new FileInputStream("src/webapp/WEB-INF/conf/services.properties"));
            Torque.init(pc.subset("services.DatabaseService"));
        }
    }

    /** Method tearDown()
     * @see junit.framework.TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        Torque.shutdown();
        super.tearDown();
    }

}
