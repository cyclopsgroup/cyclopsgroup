/*
 * Created on 2003-10-9
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado.services.rundata;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.fulcrum.ServiceException;
import org.apache.turbine.RunData;
import org.apache.turbine.services.rundata.TurbineRunDataService;

import com.cyclops.tornado.BrokerManager;
/**
 * @author joeblack
 * @since 2003-10-9 14:53:50
 */
public class TornadoRunDataService extends TurbineRunDataService {
    /** to replace default configuration */
    public static final String TORNADO_CONFIG = "tornado";
    /**
     * @see org.apache.turbine.services.rundata.RunDataService#getRunData(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.ServletConfig)
     */
    public RunData getRunData(
        HttpServletRequest request,
        HttpServletResponse response,
        ServletConfig config)
        throws ServiceException {
        return getRunData(TORNADO_CONFIG, request, response, config);
    }
    /** Implementation of method getRunData() in this class
     * @see org.apache.turbine.services.rundata.RunDataService#getRunData(java.lang.String, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.ServletConfig)
     */
    public RunData getRunData(
        String key,
        HttpServletRequest request,
        HttpServletResponse response,
        ServletConfig config)
        throws ServiceException, IllegalArgumentException {
        RunData data = super.getRunData(key, request, response, config);
        data.setTemp(BrokerManager.KEY_IN_CONTEXT, new BrokerManager());
        return data;
    }
    /** Implementation of method putRunData() in this class
     * @see org.apache.turbine.services.rundata.RunDataService#putRunData(org.apache.turbine.RunData)
     */
    public boolean putRunData(RunData data) {
        BrokerManager brokerManager =
            (BrokerManager) data.getTemp(BrokerManager.KEY_IN_CONTEXT);
        brokerManager.release();
        data.setTemp(BrokerManager.KEY_IN_CONTEXT, null);
        return super.putRunData(data);
    }
}
