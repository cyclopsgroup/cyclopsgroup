/*
 * Created on 2003-10-24
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado.modules.screens.system.administration.service;
import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.fulcrum.TurbineServices;
import org.apache.turbine.RunData;
import org.apache.turbine.TemplateContext;

import com.cyclops.tornado.modules.Screen;
import com.cyclops.tornado.services.configuration.ConfigurationService;
/**
 * @author jiaqi guo
 * @email g-cyclops@users.sourceforge.net
 */
public abstract class BaseServiceScreen extends Screen {
    /** Method getServiceName()
     * @return Service name to be managed
     */
    protected abstract String getServiceName();
    /** Implementation of method doBuildTemplate() in this class
     * @see org.apache.turbine.modules.Module#doBuildTemplate(org.apache.turbine.RunData, org.apache.turbine.TemplateContext)
     */
    protected void doBuildTemplate(RunData data, TemplateContext ctx)
        throws Exception {
        ConfigurationService cs =
            (ConfigurationService) TurbineServices.getInstance().getService(
                ConfigurationService.SERVICE_NAME);
        Configuration conf =
            cs.getCustomizableConfiguration().subset(
                "services." + getServiceName());
        if (conf == null) {
            conf = new BaseConfiguration();
        }
        ctx.put("configuration", conf);
    }
}
