/*
 * Created on 2003-10-24
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado.services.configuration;
import org.apache.commons.configuration.Configuration;
import org.apache.fulcrum.Service;

import com.cyclops.tornado.BrokerManager;
/**
 * @author jiaqi guo
 * @email g-cyclops@users.sourceforge.net
 */
public interface ConfigurationService extends Service {
    /** Service name in fulcrum */
    String SERVICE_NAME = "ConfigurationService";
    /** Get  root configuration
     * @return Root configuration object
     */
    Configuration getCustomizableConfiguration();
    /** Reload configuration content from database
     * @param brokerManager BrokerManager instance
     */
    void reload(BrokerManager brokerManager);
}
