/*
 * Created on 2003-10-22
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado.bo;
import java.util.Collections;
import java.util.List;

import org.apache.torque.util.Criteria;

import com.cyclops.tornado.om.Acl;
import com.cyclops.tornado.om.AclPeer;
/**
 * @author jiaqi guo
 * @email g-cyclops@users.sourceforge.net
 */
public class AclBroker extends AbstractObjectBroker {
    /** Implementation of method getObjectClass() in this class
     * @see com.cyclops.tornado.bo.AbstractObjectBroker#getObjectClass()
     */
    protected Class getObjectClass() {
        return Acl.class;
    }
    /** Implementation of method getPrimaryKey() in this class
     * @see com.cyclops.tornado.bo.AbstractObjectBroker#getPrimaryKey()
     */
    protected String getPrimaryKey() {
        return AclPeer.ACL_ID;
    }
    /** Method queryByOwner()
     * @param ownerName Owner name
     * @param ownerType Owner type
     * @return List of result
     * @throws Exception torque exception
     */
    public List queryByOwner(String ownerName, String ownerType)
        throws Exception {
        Criteria crit = new Criteria();
        crit.and(AclPeer.OWNER_NAME, ownerName);
        crit.and(AclPeer.OWNER_TYPE, ownerType);
        return query(crit);
    }
    /** Method queryByOwner()
     * @param ownerNames Array of owner names
     * @param ownerType Owner type
     * @return List of results
     * @throws Exception torque exception
     */
    public List queryByOwner(String[] ownerNames, String ownerType)
        throws Exception {
        if (ownerNames == null || ownerNames.length == 0) {
            return Collections.EMPTY_LIST;
        }
        Criteria crit = new Criteria();
        crit.andIn(AclPeer.OWNER_NAME, ownerNames);
        crit.and(AclPeer.OWNER_TYPE, ownerType);
        return query(crit);
    }
}
