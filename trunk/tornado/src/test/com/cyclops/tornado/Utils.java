/*
 * Created on 2003-10-6
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.fulcrum.TurbineServices;
import org.apache.torque.Torque;
/**
 * @author joeblack
 * @since 2003-10-6 13:56:39
 */
public class Utils {
    public static void main(String[] args) throws Exception {
        PropertiesConfiguration pc =
            new PropertiesConfiguration("src/webapp/WEB-INF/conf/tr.properties");
        TurbineServices.getInstance().setConfiguration(pc);
        TurbineServices.getInstance().init();
        System.out.println(Torque.getConnection("default"));
    }
}
