/*
 * Created on 2003-10-25
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado.modules.screens.system.administration.user;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.apache.turbine.RunData;
import org.apache.turbine.TemplateContext;

import com.cyclops.tornado.bo.GroupBroker;
import com.cyclops.tornado.om.Group;
/**
 * @author jiaqi guo
 * @email g-cyclops@users.sourceforge.net
 */
public class Groups extends BaseUserScreen {
    /** Implementation of method doBuildTemplate() in this class
     * @see org.apache.turbine.modules.Module#doBuildTemplate(org.apache.turbine.RunData, org.apache.turbine.TemplateContext)
     */
    protected void doBuildTemplate(RunData data, TemplateContext ctx)
        throws Exception {
        super.doBuildTemplate(data, ctx);
        GroupBroker gb = (GroupBroker) getObjectBroker(GroupBroker.class, data);
        List groups = gb.queryByUser(getUser().getUserId());
        ctx.put("belongToGroups", groups);
        HashSet ids = new HashSet();
        for (Iterator i = groups.iterator(); i.hasNext();) {
            Group group = (Group) i.next();
            ids.add(new Integer(group.getGroupId()));
        }
        List allGroups = gb.queryAll();
        List otherGroups = new ArrayList();
        for (Iterator i = allGroups.iterator(); i.hasNext();) {
            Group group = (Group) i.next();
            if (!ids.contains(new Integer(group.getGroupId()))) {
                otherGroups.add(group);
            }
        }
        ctx.put("otherGroups", otherGroups);
    }
}
