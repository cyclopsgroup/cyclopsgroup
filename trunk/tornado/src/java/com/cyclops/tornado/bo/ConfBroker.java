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
    /** Method deleteByKey()
     * @param key Key of the configuration to be delete
     * @throws Exception TorqueException
     */
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
    /** Method queryByKey()
     * @param key Key of configurations to be queried
     * @return List of result
     * @throws Exception TorqueException
     */
    public List queryByKey(String key) throws Exception {
        Criteria crit = new Criteria();
        crit.and(ConfPeer.CONF_KEY, key);
        return query(crit);
    }
    /** Method retrieveByKey()
     * @param key Key of configuration to be retrieved
     * @return Configuration object, null if not found
     * @throws Exception TorqueException
     */
    public Conf retrieveByKey(String key) throws Exception {
        Criteria crit = new Criteria();
        crit.and(ConfPeer.CONF_KEY, key);
        return (Conf) retrieve(crit);
    }
    /** Save it, making sure just one record for this key
     * @param conf The record to be saved
     * @throws Exception TorqueException actually
     */
    public void saveSingle(Conf conf) throws Exception {
        List existed = queryByKey(conf.getConfKey());
        if (existed.isEmpty()) {
            save(conf);
        } else {
            Conf existedOne = (Conf) existed.get(0);
            existedOne.setConfKey(conf.getConfKey());
            existedOne.setConfValue(conf.getConfValue());
            save(existedOne);
            for (int i = 1; i < existed.size(); i++) {
                Conf tobeDeleted = (Conf) existed.get(i);
                deleteByPK(tobeDeleted.getConfId());
            }
        }
    }
}
