/*
 * Created on 2003-10-24
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado.services.configuration;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.fulcrum.BaseService;

import com.cyclops.tornado.BrokerManager;
import com.cyclops.tornado.bo.ConfBroker;
import com.cyclops.tornado.om.Conf;
/**
 * @author jiaqi guo
 * @email g-cyclops@users.sourceforge.net
 */
public class DefaultConfigurationService
    extends BaseService
    implements ConfigurationService {
    private Configuration customizableConfiguration;
    /** Implementation of method getCustomizableConfiguration() in this class
     * @see com.cyclops.tornado.services.configuration.ConfigurationService#getCustomizableConfiguration()
     */
    public Configuration getCustomizableConfiguration() {
        return customizableConfiguration;
    }
    /** Implementation of method init() in this class
     * @see org.apache.fulcrum.Service#init()
     */
    public void init() {
        BrokerManager brokerManager = new BrokerManager();
        try {
            reload(brokerManager);
        } catch (Exception e) {
            getCategory().error("Initialzing Configuration Service error", e);
        } finally {
            brokerManager.release();
            setInit(true);
        }
    }
    /** Implementation of method reload() in this class
     * @see com.cyclops.tornado.services.configuration.ConfigurationService#reload(com.cyclops.tornado.BrokerManager)
     */
    public synchronized void reload(BrokerManager brokerManager) {
        try {
            BaseConfiguration conf = new BaseConfiguration();
            ConfBroker confb =
                (ConfBroker) brokerManager.getObjectBroker(ConfBroker.class);
            List rs = confb.queryAll();
            for (Iterator i = rs.iterator(); i.hasNext();) {
                Conf confom = (Conf) i.next();
                conf.addProperty(confom.getConfKey(), confom.getConfValue());
            }
            customizableConfiguration = conf;
        } catch (Exception e) {
            getCategory().error("Load configuration error", e);
        }
    }
}
