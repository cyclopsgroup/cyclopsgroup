/*
 * Created on 2003-10-24
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado.modules.actions.system.administration;
import org.apache.fulcrum.TurbineServices;
import org.apache.turbine.ParameterParser;
import org.apache.turbine.RunData;
import org.apache.turbine.TemplateContext;

import com.cyclops.tornado.bo.ConfBroker;
import com.cyclops.tornado.modules.Action;
import com.cyclops.tornado.om.Conf;
import com.cyclops.tornado.services.configuration.ConfigurationService;
/**
 * @author jiaqi guo
 * @email g-cyclops@users.sourceforge.net
 */
public class ConfigurationAction extends Action {
    /** Method doSavevalues()
     * @param data RunData object
     * @param ctx TemplateContext object
     * @throws Exception From inside
     */
    public void doSavevalues(RunData data, TemplateContext ctx)
        throws Exception {
        ParameterParser params = data.getParameters();
        String serviceName = params.getString("service_name");
        String[] keys = params.getStrings("conf_key");
        ConfBroker cb = (ConfBroker) getObjectBroker(ConfBroker.class, data);
        for (int i = 0; i < keys.length; i++) {
            String key = keys[i];
            String fullKey = "services." + serviceName + "." + key;
            String value = params.getString("conf_" + key);
            Conf conf = new Conf();
            conf.setConfKey(fullKey);
            conf.setConfValue(value);
            cb.deleteByKey(fullKey);
            cb.save(conf);
        }
        getConfigurationService().reload(getBrokerManager(data));
        data.setMessage(
            "New configuration will effect next time the service is restart");
    }
    private ConfigurationService getConfigurationService() {
        return (ConfigurationService) TurbineServices.getInstance().getService(
            ConfigurationService.SERVICE_NAME);
    }
}
