/*
 * Created on 2003-11-2
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado.pipeline;
import java.io.StringReader;

import org.apache.fulcrum.xslt.TurbineXSLT;
import org.apache.turbine.RunData;
import org.apache.turbine.TemplateContext;
import org.apache.turbine.pipeline.DefaultTargetValve;
import org.apache.turbine.pipeline.Renderer;
/**
 * @author jiaqi guo
 * @email g-cyclops@users.sourceforge.net
 */
public class XSLTTargetValve extends DefaultTargetValve {
    /** Implementation of method render() in this class
     * @see org.apache.turbine.pipeline.DefaultTargetValve#render(org.apache.turbine.RunData, org.apache.turbine.TemplateContext, java.lang.String)
     */
    protected void render(RunData data, TemplateContext ctx, String target)
        throws Exception {
        Renderer r = new Renderer(data);
        ctx.put("renderer", r);
        String out = r.render(target);
        //Meet error when handle &quot;&nbsp;&quot;
        TurbineXSLT.transform("default", new StringReader(out), data.getOut());
    }
}
