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

import com.cyclops.tornado.om.User;
import com.cyclops.tornado.om.UserPeer;
/**
 * @author jiaqi guo
 * @email g-cyclops@users.sourceforge.net
 */
public class UserBroker extends AbstractObjectBroker {
    /** Implementation of method getObjectClass() in this class
     * @see com.cyclops.tornado.bo.AbstractObjectBroker#getObjectClass()
     */
    protected Class getObjectClass() {
        return User.class;
    }
    /** Implementation of method getPrimaryKey() in this class
     * @see com.cyclops.tornado.bo.AbstractObjectBroker#getPrimaryKey()
     */
    protected String getPrimaryKey() {
        return UserPeer.USER_ID;
    }
    /** Method retrieveByName()
     * @param userName Account name of user
     * @return User object, null if not found
     * @throws Exception torque exception
     */
    public User retrieveByName(String userName) throws Exception {
        Criteria crit = new Criteria();
        crit.and(UserPeer.USER_NAME, userName);
        return (User) retrieve(crit);
    }
    /** Search user by user name of key word
     * @param name Account name, first name, middle name or last name, or part of them
     * @param keyword Email or description or part of them
     * @return List of User objects
     * @throws Exception TorqueException actually
     */
    public List search(String name, String keyword) throws Exception {
        Criteria crit = new Criteria();
        if (!StringUtils.isEmpty(name)) {
            String part = "%" + name + "%";
            Criteria.Criterion accountName =
                crit.getNewCriterion(UserPeer.USER_NAME, part, Criteria.LIKE);
            Criteria.Criterion firstName =
                crit.getNewCriterion(UserPeer.FIRST_NAME, part, Criteria.LIKE);
            Criteria.Criterion middleName =
                crit.getNewCriterion(UserPeer.MIDDLE_NAME, part, Criteria.LIKE);
            Criteria.Criterion lastName =
                crit.getNewCriterion(UserPeer.LAST_NAME, part, Criteria.LIKE);
            crit.and(accountName.or(firstName).or(middleName).or(lastName));
        }
        if (!StringUtils.isEmpty(keyword)) {
            String part = "%" + keyword + "%";
            Criteria.Criterion email =
                crit.getNewCriterion(UserPeer.EMAIL, part, Criteria.LIKE);
            Criteria.Criterion description =
                crit.getNewCriterion(UserPeer.DESCRIPTION, part, Criteria.LIKE);
            crit.and(email.or(description));
        }
        return query(crit);
    }
}
