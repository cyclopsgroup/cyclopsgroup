/*
 * Created on 2003-11-7
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado.services.navigator;
import org.apache.commons.configuration.Configuration;
/**
 * @author jiaqi guo
 * @email g-cyclops@users.sourceforge.net
 */
public interface NavigatorLoader {
    /** Load MenuProject object from configuration
     * @param conf Configuration object
     * @return MenuProject object
     */
    MenuProject load(Configuration conf);
}
