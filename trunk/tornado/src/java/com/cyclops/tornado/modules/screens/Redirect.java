/*
 * Created on 2003-10-15
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado.modules.screens;
import org.apache.commons.lang.StringUtils;
import org.apache.turbine.RunData;
import org.apache.turbine.TemplateContext;

import com.cyclops.tornado.modules.Screen;
/**
 * @author joeblack
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Redirect extends Screen {
    /**
     * @see org.apache.turbine.modules.Module#doBuildTemplate(org.apache.turbine.RunData, org.apache.turbine.TemplateContext)
     */
    protected void doBuildTemplate(RunData data, TemplateContext ctx)
        throws Exception {
        String redirectto = data.getParameters().getString("redirectto");
        if (!StringUtils.isEmpty(redirectto)) {
            data.getResponse().sendRedirect(redirectto);
        }
    }
}
