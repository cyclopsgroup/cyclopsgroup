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
import com.cyclops.tornado.om.DUser;
/**
 * @author jiaqi guo
 * @email g-cyclops@users.sourceforge.net
 */
public class UserAction extends Action {
    /** Force to change password of a user
     * @param data RunData object as input
     * @param ctx TemplateContext object as output
     * @throws Exception From brokers
     */
    public void doChangepwd(RunData data, TemplateContext ctx)
        throws Exception {
        ParameterParser params = data.getParameters();
        String userName = params.getString("user_name");
        UserBroker ub = (UserBroker) getObjectBroker(UserBroker.class, data);
        DUser user = ub.retrieveByName(userName);
        user.setPassword(params.getString("new_password"));
        ub.save(user);
        data.setMessage("Password for user " + userName + " is changed");
    }
    /** Save user profile
     * @param data RunData object as input
     * @param ctx TemplateContext as output
     * @throws Exception From broker objects
     */
    public void doSaveprof(RunData data, TemplateContext ctx)
        throws Exception {
        ParameterParser params = data.getParameters();
        UserBroker ub = (UserBroker) getObjectBroker(UserBroker.class, data);
        int userId = params.getInt("user_id");
        DUser user = (DUser) ub.retrieveByPK(userId);
        user.setFirstName(params.getString("first_name"));
        user.setMiddleName(params.getString("middle_name"));
        user.setLastName(params.getString("last_name"));
        user.setEmail(params.getString("email"));
        user.setDescription(params.getString("description"));
        ub.save(user);
        data.setMessage("User profile [" + user.getUserName() + "] is updated");
    }
    /** Method doCreate()
     * @param data RunData object as input
     * @param ctx TemplateContext as output
     * @throws Exception From broker objects
     */
    public void doCreate(RunData data, TemplateContext ctx) throws Exception {
        ParameterParser params = data.getParameters();
        UserBroker ub = (UserBroker) getObjectBroker(UserBroker.class, data);
        String userName = params.getString("user_name");
        DUser existed = ub.retrieveByName(userName);
        if (existed != null) {
            data.setMessage("User [" + userName + "] already exist");
            return;
        }
        DUser user = new DUser();
        user.setUserName(userName);
        user.setPassword(params.getString("password"));
        user.setFirstName(params.getString("first_name"));
        user.setMiddleName(params.getString("middle_name"));
        user.setLastName(params.getString("last_name"));
        user.setEmail(params.getString("email"));
        user.setDescription(params.getString("description"));
        ub.save(user);
        data.setMessage("User [" + userName + "] created");
    }
    /** Method doDelete()
     * @param data RunData object as input
     * @param ctx TemplateContext as output
     * @throws Exception From broker objects
     */
    public void doDelete(RunData data, TemplateContext ctx) throws Exception {
        int id = data.getParameters().getInt("user_id");
        UserBroker ub = (UserBroker) getObjectBroker(UserBroker.class, data);
        DUser user = (DUser) ub.retrieveByPK(id);
        if (user != null) {
            user.setIsDisabled(true);
            ub.save(user);
            data.setMessage("User [" + user.getUserName() + "] deleted.");
        }
    }
}
