/*
 * Created on 2003-10-24
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado.modules.actions.system.administration;
import org.apache.turbine.ParameterParser;
import org.apache.turbine.RunData;
import org.apache.turbine.TemplateContext;

import com.cyclops.tornado.bo.UserBroker;
import com.cyclops.tornado.modules.Action;
import com.cyclops.tornado.om.User;
/**
 * @author jiaqi guo
 * @email g-cyclops@users.sourceforge.net
 */
public class UserAction extends Action {
    public void doSaveprof(RunData data, TemplateContext ctx)
        throws Exception {
        ParameterParser params = data.getParameters();
        UserBroker ub = (UserBroker) getObjectBroker(UserBroker.class, data);
        int userId = params.getInt("user_id");
        User user = (User) ub.retrieveByPK(userId);
        user.setFirstName(params.getString("first_name"));
        user.setMiddleName(params.getString("middle_name"));
        user.setLastName(params.getString("last_name"));
        user.setEmail(params.getString("email"));
        user.setDescription(params.getString("description"));
        ub.save(user);
        data.setMessage("User profile [" + user.getUserName() + "] is updated");
    }
}
