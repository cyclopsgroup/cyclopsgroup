/*
 * Created on 2003-10-21
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado.modules;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import com.cyclops.tornado.BrokerManager;
import com.cyclops.tornado.Passport;
import com.cyclops.tornado.bo.AclBroker;
import com.cyclops.tornado.bo.GroupBroker;
import com.cyclops.tornado.om.Acl;
import com.cyclops.tornado.om.Group;
import com.cyclops.tornado.services.user.User;
import com.cyclops.tornado.services.user.UserEvent;
import com.cyclops.tornado.services.user.UserListener;
/**
 * @author jiaqi guo
 * @email g-cyclops@users.sourceforge.net
 */
/**
 * @author jiaqi guo
 * @email g-cyclops@users.sourceforge.net
 */
public class PassportBuilder implements UserListener {
    /** Implementation of method onSingIn() in this class
     * @see com.cyclops.tornado.services.user.UserListener#onSingIn(com.cyclops.tornado.services.user.UserEvent)
     */
    public void onSingIn(UserEvent event) throws Exception {
        BrokerManager brokerManager = event.getBrokerManager();
        User user = event.getUser();
        HashSet roles = new HashSet();
        HashSet permissions = new HashSet();
        HashMap groups = new HashMap();
        AclBroker aclb =
            (AclBroker) brokerManager.getObjectBroker(AclBroker.class);
        List acls = new ArrayList();
        acls.addAll(aclb.queryByOwner(user.getName(), Acl.OWNER_TYPE_USER));
        GroupBroker groupb =
            (GroupBroker) brokerManager.getObjectBroker(GroupBroker.class);
        List rs = groupb.queryByUser(user.getId());
        while (!rs.isEmpty()) {
            Group group = (Group) rs.get(0);
            if (!groups.containsKey(new Integer(group.getGroupId()))) {
                groups.put(
                    new Integer(group.getGroupId()),
                    group.getGroupName());
                List parents = groupb.queryParents(group.getGroupId());
                rs.addAll(parents);
            }
            rs.remove(group);
        }
        rs =
            aclb.queryByOwner(
                (String[]) groups.values().toArray(new String[0]),
                Acl.OWNER_TYPE_GROUP);
        acls.addAll(rs);
        Passport passport = new Passport();
        for (Iterator i = acls.iterator(); i.hasNext();) {
            Acl acl = (Acl) i.next();
            if (acl.getIsRole()) {
                List perms =
                    aclb.queryByOwner(acl.getPermission(), Acl.OWNER_TYPE_ROLE);
                for (Iterator j = perms.iterator(); j.hasNext();) {
                    Acl p = (Acl) j.next();
                    passport.addPermission(p.getPermission());
                }
            } else {
                passport.addPermission(acl.getPermission());
            }
        }
    }
    /** Implementation of method onSingOut() in this class
     * @see com.cyclops.tornado.services.user.UserListener#onSingOut(com.cyclops.tornado.services.user.UserEvent)
     */
    public void onSingOut(UserEvent event) throws Exception {
        //do nothing
    }
}
