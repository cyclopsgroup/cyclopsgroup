/*
 * Created on 2003-10-24
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado.bo;
import java.util.List;

import org.apache.torque.util.Criteria;

import com.cyclops.tornado.om.Conf;
import com.cyclops.tornado.om.ConfPeer;
/**
 * @author jiaqi guo
 * @email g-cyclops@users.sourceforge.net
 */
public class ConfBroker extends AbstractObjectBroker {
    public void deleteByKey(String key) throws Exception {
        Criteria crit = new Criteria();
        crit.and(ConfPeer.CONF_KEY, key);
        delete(crit);
    }
    /** Implementation of method getObjectClass() in this class
     * @see com.cyclops.tornado.bo.AbstractObjectBroker#getObjectClass()
     */
    protected Class getObjectClass() {
        return Conf.class;
    }
    /** Implementation of method getPrimaryKey() in this class
     * @see com.cyclops.tornado.bo.AbstractObjectBroker#getPrimaryKey()
     */
    protected String getPrimaryKey() {
        return ConfPeer.CONF_ID;
    }
    public List queryByKey(String key) throws Exception {
        Criteria crit = new Criteria();
        crit.and(ConfPeer.CONF_KEY, key);
        return query(crit);
    }
    /** Method retrieveByKey()
     * @param key
     * @return
     * @throws Exception
     */
    public Conf retrieveByKey(String key) throws Exception {
        Criteria crit = new Criteria();
        crit.and(ConfPeer.CONF_KEY, key);
        return (Conf) retrieve(crit);
    }
}
