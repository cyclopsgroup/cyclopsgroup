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
}
