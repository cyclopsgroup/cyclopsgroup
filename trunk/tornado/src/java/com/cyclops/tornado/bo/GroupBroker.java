/*
 * Created on 2003-10-22
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado.bo;
import java.util.List;

import org.apache.torque.util.Criteria;

import com.cyclops.tornado.om.Group;
import com.cyclops.tornado.om.GroupHierarchyPeer;
import com.cyclops.tornado.om.GroupPeer;
import com.cyclops.tornado.om.UserGroupPeer;
/**
 * @author jiaqi guo
 * @email g-cyclops@users.sourceforge.net
 */
public class GroupBroker extends AbstractObjectBroker {
    /** Implementation of method getObjectClass() in this class
     * @see com.cyclops.tornado.bo.AbstractObjectBroker#getObjectClass()
     */
    protected Class getObjectClass() {
        return Group.class;
    }
    /** Implementation of method getPrimaryKey() in this class
     * @see com.cyclops.tornado.bo.AbstractObjectBroker#getPrimaryKey()
     */
    protected String getPrimaryKey() {
        return GroupPeer.GROUP_ID;
    }
    /** Method queryByUser()
     * @param userId id of user
     * @return list of group objects
     * @throws Exception from torque
     */
    public List queryByUser(int userId) throws Exception {
        Criteria crit = new Criteria();
        crit.and(UserGroupPeer.USER_ID, userId);
        crit.addJoin(UserGroupPeer.GROUP_ID, GroupPeer.GROUP_ID);
        return query(crit);
    }
    /** Method queryParents()
     * @param groupId Id of group
     * @return List of parent group objects
     * @throws Exception torque exception
     */
    public List queryParents(int groupId) throws Exception {
        Criteria crit = new Criteria();
        crit.and(GroupHierarchyPeer.GROUP_ID, groupId);
        crit.addJoin(GroupHierarchyPeer.PARENT_GROUP_ID, GroupPeer.GROUP_ID);
        return GroupPeer.doSelect(crit);
    }
}
