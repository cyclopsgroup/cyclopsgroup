/*
 * Created on 2003-10-24
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado.modules.actions.system.administration;
import org.apache.fulcrum.Service;
import org.apache.fulcrum.TurbineServices;
import org.apache.turbine.ParameterParser;
import org.apache.turbine.RunData;
import org.apache.turbine.TemplateContext;

import com.cyclops.tornado.bo.ConfBroker;
import com.cyclops.tornado.modules.Action;
import com.cyclops.tornado.om.Conf;
import com.cyclops.tornado.services.Restartable;
import com.cyclops.tornado.services.configuration.ConfigurationService;
/**
 * @author jiaqi guo
 * @email g-cyclops@users.sourceforge.net
 */
public class ConfigurationAction extends Action {
    /** Add new configuration item
     * @param data RunData object contains input
     * @param ctx TemplateContext as output
     * @throws Exception Not capture here
     */
    public void doAddnew(RunData data, TemplateContext ctx) throws Exception {
        ParameterParser params = data.getParameters();
        String serviceName = params.getString("service_name");
        String key = params.getString("addnew_key");
        String value = params.getString("addnew_value");
        Conf conf = new Conf();
        conf.setConfKey("services." + serviceName + "." + key);
        conf.setConfValue(value);
        ConfBroker cb = (ConfBroker) getObjectBroker(ConfBroker.class, data);
        cb.save(conf);
        noticeFinished(data);
    }
    /** Delete a set of records
     * @param data RunData object contains input
     * @param ctx TemplateContext object as output
     * @throws Exception TorqueException actually
     */
    public void doDelete(RunData data, TemplateContext ctx) throws Exception {
        int[] ids = data.getParameters().getInts("conf_id");
        ConfBroker cb = (ConfBroker) getObjectBroker(ConfBroker.class, data);
        cb.deleteByPKs(ids);
        noticeFinished(data);
    }
    /** Restart a specified service
     * @param data RunData object as input
     * @param ctx TemplateContext object as output
     * @throws Exception Not captured here
     */
    public void doRestart(RunData data, TemplateContext ctx) throws Exception {
        String serviceName = data.getParameters().getString("service_name");
        Service service = TurbineServices.getInstance().getService(serviceName);
        if (service instanceof Restartable) {
            try {
                ((Restartable) service).restart();
                data.setMessage(serviceName + " is restarted");
            } catch (Exception e) {
                logger.error(
                    "Error occurs when restarting the service " + serviceName,
                    e);
                data.setMessage(
                    "Error occurs when restarting the service " + serviceName);
            }
        } else {
            data.setMessage(
                serviceName
                    + " is not restartable. Please restart the appliation manually.");
        }
    }
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
        noticeFinished(data);
    }
    private void noticeFinished(RunData data) {
        ConfigurationService cs =
            (ConfigurationService) TurbineServices.getInstance().getService(
                ConfigurationService.SERVICE_NAME);
        cs.reload(getBrokerManager(data));
        data.setMessage(
            "New configuration will effect next time the "
                + data.getParameters().getString("service_name")
                + " is restart");
    }
}
