/*
 * Created on 2003-10-4
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado.modules.actions;
import org.apache.fulcrum.TurbineServices;
import org.apache.turbine.ParameterParser;
import org.apache.turbine.RunData;
import org.apache.turbine.TemplateAction;
import org.apache.turbine.TemplateContext;

import com.cyclops.tornado.services.user.UserService;
/**
 * @author joeblack
 * @since 2003-10-4 13:45:52
 */
public class UserIdentify extends TemplateAction {
    private UserService getUserService() {
        return (UserService) TurbineServices.getInstance().getService(
            UserService.SERVICE_NAME);
    }
    /** doSignin event in this action
     * @param data RunData object as input
     * @param ctx TemplateContext as output
     * @throws Exception anything could happen
     */
    public void doSignin(RunData data, TemplateContext ctx) throws Exception {
        ParameterParser params = data.getParameters();
        String userName = params.getString("user_name");
        int result =
            getUserService().checkUser(userName, params.getString("password"));
        String message = "Unknown error";
        switch (result) {
            case UserService.CHECK_RESULT_OK :
                getUserService().singin(data.getSession().getId(), userName);
                message = "Welcom, " + userName;
                break;
            case UserService.CHECK_RESULT_INCORRECT_PASSWORD :
                message = "Incorrect password, try again";
                break;
            case UserService.CHECK_RESULT_INVALID_USER :
                message = "User " + userName + " doesn't exist";
                break;
            case UserService.CHECK_RESULT_DISABLED_USER :
                message = "User " + userName + " is disabled";
                break;
            case UserService.CHECK_RESULT_EXCEPTION :
                message = "Exception occurred, call administrator pls";
                break;
            default :
                //DO NOTHING
        }
        data.setMessage(message);
    }
    /** Method doSignout() in Class UserIdentify
     * @param data RunData object as input
     * @param ctx TemplateContext as output
     * @throws Exception anything could happen
     */
    public void doSignout(RunData data, TemplateContext ctx) throws Exception {
        getUserService().singout(data.getSession().getId());
    }
}
