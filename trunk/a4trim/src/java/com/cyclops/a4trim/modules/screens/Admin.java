/*
 * Created on 2003-9-26
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.a4trim.modules.screens;
import org.apache.commons.lang.StringUtils;
import org.apache.turbine.ParameterParser;
import org.apache.turbine.RunData;
import org.apache.turbine.TemplateContext;
import org.apache.turbine.TemplateScreen;

import com.cyclops.a4trim.tools.A4TrimTool;
/**
 * @author joeblack
 * @since 2003-9-26 14:43:49
 */
public class Admin extends TemplateScreen {
    /** Method doBuildTemplate()
     * @see org.apache.turbine.modules.Module#doBuildTemplate(org.apache.turbine.RunData, org.apache.turbine.TemplateContext)
     */
    protected void doBuildTemplate(RunData data, TemplateContext ctx)
        throws Exception {
        ParameterParser params = data.getParameters();
        String s = params.getString("switch");
        A4TrimTool tool = (A4TrimTool) getTool(ctx, "a4trim");
        if (StringUtils.equals(s, "off")) {
            tool.adminSignOut();
        } else if (StringUtils.equals(s, "on")) {
            if (!tool.adminSignIn(params.getString("password"))) {
                data.setMessage("Invalid admin password");
            }
        }
    }
}
