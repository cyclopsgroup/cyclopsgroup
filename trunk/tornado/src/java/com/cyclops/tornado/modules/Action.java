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
import org.apache.turbine.TemplateAction;
import org.apache.turbine.TemplateContext;

import com.cyclops.tornado.BrokerManager;
import com.cyclops.tornado.bo.ObjectBroker;
/**
 * @author jiaqi guo
 * @email g-cyclops@users.sourceforge.net
 */
public class Action extends TemplateAction {
    /** Method getBrokerManager()
     * @param data RunData object
     * @return BrokerManager instance
     */
    public static BrokerManager getBrokerManager(RunData data) {
        return Screen.getBrokerManager(data);
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
        return Screen.getObjectBroker(instanceClass, data);
    }
    /** Logger object */
    protected Log logger = LogFactory.getLog(getClass());
    /** Implementation of method doPerform() in this class
     * @see org.apache.turbine.modules.actions.TemplateAction#doPerform(org.apache.turbine.RunData, org.apache.turbine.TemplateContext)
     */
    public void doPerform(RunData data, TemplateContext ctx) throws Exception {
        //do nothing
    }
}
