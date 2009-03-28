/*
 * Created on 2003-9-25
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.a4trim.modules.screens.admin;
import org.apache.turbine.RunData;
import org.apache.turbine.TemplateContext;
import org.apache.turbine.TemplateScreen;

import com.cyclops.a4trim.om.Category;
import com.cyclops.a4trim.om.CategoryPeer;
/**
 * @author joeblack
 * @since 2003-9-25 14:13:46
 *
 */
public class CategoryDetail extends TemplateScreen {
    /** Method doBuildTemplate()
     * @see org.apache.turbine.modules.Module#doBuildTemplate(org.apache.turbine.RunData, org.apache.turbine.TemplateContext)
     */
    protected void doBuildTemplate(RunData data, TemplateContext ctx)
        throws Exception {
        int id = data.getParameters().getInt("category_id", -1);
        Category category = CategoryPeer.retrieveByPK(id);
        ctx.put("category", category);
    }
}
