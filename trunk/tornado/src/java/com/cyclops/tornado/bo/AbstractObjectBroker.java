/*
 * Created on 2003-10-22
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado.bo;
import java.util.List;

import org.apache.torque.util.Criteria;
/**
 * @author jiaqi guo
 * @email g-cyclops@users.sourceforge.net
 */
public abstract class AbstractObjectBroker extends ObjectBroker {
    /** Method getObjectClass()
     * @return What class does this broker focus on
     */
    protected abstract Class getObjectClass();
    /** Method getPrimaryKey()
     * @return Primary key field
     */
    protected abstract String getPrimaryKey();
    /** Implementation of method query() in this class
     * @see com.cyclops.tornado.bo.ObjectBroker#query(java.lang.Class, org.apache.torque.util.Criteria)
     */
    public List query(Criteria crit) throws Exception {
        return super.query(getObjectClass(), crit);
    }
    /** Implementation of method retrieve() in this class
     * @see com.cyclops.tornado.bo.ObjectBroker#retrieve(java.lang.Class, org.apache.torque.util.Criteria)
     */
    public Object retrieve(Criteria crit) throws Exception {
        return retrieve(getObjectClass(), crit);
    }
    /** Method retrieveByPK()
     * @param id Int id value
     * @return Single object, null if not found
     * @throws Exception from torque
     */
    public Object retrieveByPK(int id) throws Exception {
        Criteria crit = new Criteria();
        crit.and(getPrimaryKey(), id);
        return retrieve(crit);
    }
}
