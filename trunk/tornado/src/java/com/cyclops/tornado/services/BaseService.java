/*
 * Created on 2003-9-29
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado.services;
import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cyclops.tornado.BrokerManager;
import com.cyclops.tornado.services.configuration.ConfigurationService;
import com.cyclops.tornado.utils.PathTransformable;
/**
 * @author joeblack
 * @since 2003-9-29 17:03:56
 *
 * Class BaseService in tornado project
 */
public abstract class BaseService
    extends org.apache.fulcrum.BaseService
    implements PathTransformable {
    /** Configuration object for this service */
    protected Configuration configuration;
    /** Log object used in the derived classes */
    protected Log logger = LogFactory.getLog(getClass());
    /** Method init()
     * @see org.apache.fulcrum.Service#init()
     */
    public synchronized void init() {
        BrokerManager brokerManager = new BrokerManager();
        try {
            ConfigurationService cs =
                (ConfigurationService) getServiceBroker().getService(
                    ConfigurationService.SERVICE_NAME);
            Configuration conf =
                cs.getCustomizableConfiguration().subset(
                    "services." + getName());
            if (conf == null) {
                conf = new BaseConfiguration();
            }
            setConfiguration(conf);
            logger.debug(
                "Start initializing service implementation "
                    + getClass().getName());
            initialize(configuration, brokerManager);
            logger.debug(
                "Initialization finished for service " + getClass().getName());
        } catch (Exception e) {
            logger.error("Service " + getName() + "failed to initialize", e);
        } finally {
            brokerManager.release();
            setInit(true);
        }
    }
    /** Derived classes will override this method to initialize service
     * @param conf Configuration object
     * @param brokerManager To access biz objects
     * @throws Exception Any exception could be thrown
     */
    protected abstract void initialize(
        Configuration conf,
        BrokerManager brokerManager)
        throws Exception;
    /** All derived class can implements Restartable interface without add anycode
     * But make sure no problem will occur when shutdown(), init() methods are called consecutively before the service implements the interface
     * @see com.cyclops.tornado.services.Restartable#restart()
     */
    public synchronized void restart() throws Exception {
        shutdown();
        setInit(false);
        init();
    }
    /** Method setConfiguration() in Class BaseService
     * @param conf Configuration object
     */
    public void setConfiguration(Configuration conf) {
        configuration = conf;
    }
}
