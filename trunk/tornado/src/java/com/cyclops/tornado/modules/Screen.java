/*
 * Created on 2003-10-22
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado.modules;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.turbine.RunData;
import org.apache.turbine.TemplateContext;
import org.apache.turbine.TemplateScreen;

import com.cyclops.tornado.BrokerManager;
import com.cyclops.tornado.bo.ObjectBroker;
/**
 * @author jiaqi guo
 * @email g-cyclops@users.sourceforge.net
 */
public class Screen extends TemplateScreen {
    /** Method getBrokerManager()
     * @param data RunData object
     * @return BrokerManager instance
     */
    public static BrokerManager getBrokerManager(RunData data) {
        BrokerManager brokerManager =
            (BrokerManager) data.getTemp(BrokerManager.KEY_IN_CONTEXT);
        return brokerManager;
    }
    /** Method newObjectBroker()
     * @param instanceClass class of object broker
     * @param data RunData object
     * @throws Exception from torque
     * @return Instance of ObjectBroker
     */
    public static ObjectBroker getObjectBroker(
        Class instanceClass,
        RunData data)
        throws Exception {
        return getBrokerManager(data).getObjectBroker(instanceClass);
    }
    /** Logger object */
    protected Log logger = LogFactory.getLog(getClass());
    /** Implementation of method doBuildTemplate() in this class
     * @see org.apache.turbine.modules.Module#doBuildTemplate(org.apache.turbine.RunData, org.apache.turbine.TemplateContext)
     */
    protected void doBuildTemplate(RunData arg0, TemplateContext arg1)
        throws Exception {
        //do nothing
    }
}
