/*
 * Created on 2003-10-6
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado.modules.screens.system.administration.user;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.torque.util.Criteria;
import org.apache.turbine.ParameterParser;
import org.apache.turbine.RunData;
import org.apache.turbine.TemplateContext;
import org.apache.turbine.TemplateScreen;

import com.cyclops.tornado.om.User;
import com.cyclops.tornado.om.UserPeer;
/**
 * @author joeblack
 * @since 2003-10-6 20:41:48
 */
public class BaseUser extends TemplateScreen {
    private User dbuser;
    /** Method doBuildTemplate()
     * @see org.apache.turbine.modules.Module#doBuildTemplate(org.apache.turbine.RunData, org.apache.turbine.TemplateContext)
     */
    protected void doBuildTemplate(RunData data, TemplateContext ctx)
        throws Exception {
        ParameterParser params = data.getParameters();
        String userName = params.getString("user_name");
        ctx.put("userName", userName);
        if (StringUtils.isEmpty(userName)) {
            data.setMessage("Please input user name");
        } else {
            Criteria crit = new Criteria();
            crit.and(UserPeer.USER_NAME, userName);
            List users = UserPeer.doSelect(crit);
            if (users.isEmpty()) {
                data.setMessage("User [" + userName + "] doesn't exist");
            } else {
                dbuser = (User) users.get(0);
                ctx.put("user", dbuser);
            }
        }
    }
    /** Method getUserOM() in Class BaseUser
     * @return User OM object
     */
    protected final User getUserOM() {
        return dbuser;
    }
}
