/*
 * Created on 2003-11-3
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado.modules.screens.system.administration.user;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.turbine.RunData;
import org.apache.turbine.TemplateContext;

import com.cyclops.tornado.bo.AclBroker;
import com.cyclops.tornado.bo.RoleBroker;
import com.cyclops.tornado.om.Acl;
import com.cyclops.tornado.om.Role;
/**
 * @author jiaqi guo
 * @email g-cyclops@users.sourceforge.net
 */
public class Permissions extends BaseUserScreen {
    /** Implementation of method doBuildTemplate() in this class
     * @see org.apache.turbine.modules.Module#doBuildTemplate(org.apache.turbine.RunData, org.apache.turbine.TemplateContext)
     */
    protected void doBuildTemplate(RunData data, TemplateContext ctx)
        throws Exception {
        super.doBuildTemplate(data, ctx);
        if (getUser() == null) {
            return;
        }
        AclBroker aclb = (AclBroker) getObjectBroker(AclBroker.class, data);
        List acls =
            aclb.queryByOwner(getUser().getUserName(), Acl.OWNER_TYPE_USER);
        List permissions = new ArrayList();
        List roles = new ArrayList();
        List roleNames = new ArrayList();
        for (Iterator i = acls.iterator(); i.hasNext();) {
            Acl acl = (Acl) i.next();
            if (acl.getIsRole()) {
                roles.add(acl);
                roleNames.add(acl.getPermission());
            } else {
                permissions.add(acl);
            }
        }
        ctx.put("permissions", permissions);
        ctx.put("roles", roles);
        RoleBroker rb = (RoleBroker) getObjectBroker(RoleBroker.class, data);
        List allRoles = rb.queryAll();
        List otherRoles = new ArrayList();
        for (Iterator i = allRoles.iterator(); i.hasNext();) {
            Role role = (Role) i.next();
            if (!roleNames.contains(role.getRoleName())) {
                otherRoles.add(role);
            }
        }
        ctx.put("otherRoles", otherRoles);
    }
}
