/*
 * Created on 2003-10-9
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado.services;
import java.util.HashSet;

import org.apache.fulcrum.InitializationException;
import org.apache.fulcrum.InstantiationException;
import org.apache.fulcrum.Service;
import org.apache.fulcrum.TurbineServices;
/**
 * @author joeblack
 * @since 2003-10-9 15:15:14
 */
public class SlowServiceManager extends TurbineServices {
    private HashSet initializedServices = new HashSet();
    private boolean isInit = false;
    /** Initialize this service if it's not initialized
     * @see org.apache.fulcrum.ServiceBroker#getService(java.lang.String)
     */
    public Service getService(String serviceName)
        throws InstantiationException {
        if (!initializedServices.contains(serviceName)) {
            try {
                initService(serviceName);
            } catch (Exception e) {
                e.printStackTrace();
                getCategory().error(e);
            }
            initializedServices.add(serviceName);
        }
        return super.getService(serviceName);
    }
    /** None of services is initialized here even its earlyInit is true
     * @see org.apache.fulcrum.BaseServiceBroker#init()
     */
    public void init() throws InitializationException {
        if (isInit) {
            return;
        }
        loggingConfigured = false;
        initMapping();
        isInit = true;
    }
    /** Implementation of method getRealPath() in this class
     * @see org.apache.fulcrum.ServiceBroker#getRealPath(java.lang.String)
     */
    public String getRealPath(String path) {
        return "src/webapp/" + path;
    }
}
