/*
 * Created on 2003-10-24
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado.modules.screens.system.administration.service;
import org.apache.turbine.RunData;
import org.apache.turbine.TemplateContext;

import com.cyclops.tornado.bo.ConfBroker;
import com.cyclops.tornado.services.user.UserService;
/**
 * @author jiaqi guo
 * @email g-cyclops@users.sourceforge.net
 */
public class User extends BaseServiceScreen {
    /** Implementation of method doBuildTemplate() in this class
     * @see org.apache.turbine.modules.Module#doBuildTemplate(org.apache.turbine.RunData, org.apache.turbine.TemplateContext)
     */
    protected void doBuildTemplate(RunData data, TemplateContext ctx)
        throws Exception {
        super.doBuildTemplate(data, ctx);
        ConfBroker cb = (ConfBroker) getObjectBroker(ConfBroker.class, data);
        ctx.put("listeners", cb.queryByKey("services.UserService.listener"));
    }
    /** Implementation of method getServiceName() in this class
     * @see com.cyclops.tornado.modules.screens.system.administration.service.BaseServiceScreen#getServiceName()
     */
    protected String getServiceName() {
        return UserService.SERVICE_NAME;
    }
}
