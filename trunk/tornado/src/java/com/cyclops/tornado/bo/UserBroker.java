/*
 * Created on 2003-10-22
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado.bo;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.torque.util.Criteria;

import com.cyclops.tornado.om.DUser;
import com.cyclops.tornado.om.DUserPeer;
/**
 * @author jiaqi guo
 * @email g-cyclops@users.sourceforge.net
 */
public class UserBroker extends AbstractObjectBroker {
    /** Implementation of method getObjectClass() in this class
     * @see com.cyclops.tornado.bo.AbstractObjectBroker#getObjectClass()
     */
    protected Class getObjectClass() {
        return DUser.class;
    }
    /** Implementation of method getPrimaryKey() in this class
     * @see com.cyclops.tornado.bo.AbstractObjectBroker#getPrimaryKey()
     */
    protected String getPrimaryKey() {
        return DUserPeer.USER_ID;
    }
    /** Method retrieveByName()
     * @param userName Account name of user
     * @return DUser object, null if not found
     * @throws Exception torque exception
     */
    public DUser retrieveByName(String userName) throws Exception {
        Criteria crit = new Criteria();
        crit.and(DUserPeer.USER_NAME, userName);
        return (DUser) retrieve(crit);
    }
    /** Search DUser by DUser name of key word
     * @param name Account name, first name, middle name or last name, or part of them
     * @param keyword Email or description or part of them
     * @param includingDeleted Whether or not including deleted records
     * @return List of DUser objects
     * @throws Exception TorqueException actually
     */
    public List search(String name, String keyword, boolean includingDeleted)
        throws Exception {
        Criteria crit = new Criteria();
        if (!StringUtils.isEmpty(name)) {
            String part = "%" + name + "%";
            Criteria.Criterion accountName =
                crit.getNewCriterion(DUserPeer.USER_NAME, part, Criteria.LIKE);
            Criteria.Criterion firstName =
                crit.getNewCriterion(DUserPeer.FIRST_NAME, part, Criteria.LIKE);
            Criteria.Criterion middleName =
                crit.getNewCriterion(
                    DUserPeer.MIDDLE_NAME,
                    part,
                    Criteria.LIKE);
            Criteria.Criterion lastName =
                crit.getNewCriterion(DUserPeer.LAST_NAME, part, Criteria.LIKE);
            crit.and(accountName.or(firstName).or(middleName).or(lastName));
        }
        if (!StringUtils.isEmpty(keyword)) {
            String part = "%" + keyword + "%";
            Criteria.Criterion email =
                crit.getNewCriterion(DUserPeer.EMAIL, part, Criteria.LIKE);
            Criteria.Criterion description =
                crit.getNewCriterion(
                    DUserPeer.DESCRIPTION,
                    part,
                    Criteria.LIKE);
            crit.and(email.or(description));
        }
        if (!includingDeleted) {
            crit.and(DUserPeer.IS_DISABLED, false);
        }
        return query(crit);
    }
}
