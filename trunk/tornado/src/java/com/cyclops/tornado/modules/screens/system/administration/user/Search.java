/*
 * Created on 2003-11-2
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado.modules.screens.system.administration.user;
import org.apache.commons.lang.StringUtils;
import org.apache.turbine.ParameterParser;
import org.apache.turbine.RunData;
import org.apache.turbine.TemplateContext;

import com.cyclops.tornado.bo.UserBroker;
import com.cyclops.tornado.modules.Screen;
/**
 * @author jiaqi guo
 * @email g-cyclops@users.sourceforge.net
 */
public class Search extends Screen {
    /** Implementation of method doBuildTemplate() in this class
     * @see org.apache.turbine.modules.Module#doBuildTemplate(org.apache.turbine.RunData, org.apache.turbine.TemplateContext)
     */
    protected void doBuildTemplate(RunData data, TemplateContext ctx)
        throws Exception {
        ParameterParser params = data.getParameters();
        String userName = params.getString("user_name");
        ctx.put("userName", userName);
        String keyword = params.getString("keyword");
        ctx.put("keyword", keyword);
        if (StringUtils.isEmpty(userName) && StringUtils.isEmpty(keyword)) {
            data.setMessage("Please input search hint");
            return;
        }
        UserBroker ub = (UserBroker) getObjectBroker(UserBroker.class, data);
        ctx.put("users", ub.search(userName, keyword));
    }
}
