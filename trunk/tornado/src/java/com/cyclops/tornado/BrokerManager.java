/*
 * Created on 2003-10-22
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado;
import com.cyclops.tornado.bo.ConnectionManager;
import com.cyclops.tornado.bo.ObjectBroker;
/**
 * @author jiaqi guo
 * @email g-cyclops@users.sourceforge.net
 */
public class BrokerManager extends ConnectionManager {
    /** Key of BrokerManager in context */
    public static final String KEY_IN_CONTEXT = BrokerManager.class.getName();
    /** Method getObjectBroker()
     * @param brokerClass Requested class
     * @return ObjectBroker instance
     * @throws Exception It doesn't handle any exception here
     */
    public ObjectBroker getObjectBroker(Class brokerClass) throws Exception {
        return (ObjectBroker) getConnectableObject(brokerClass);
    }
}
