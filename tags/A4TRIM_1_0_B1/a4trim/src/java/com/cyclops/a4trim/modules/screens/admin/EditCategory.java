/*
 * Created on 2003-9-23
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.a4trim.modules.screens.admin;
import java.util.List;

import org.apache.torque.util.Criteria;
import org.apache.turbine.RunData;
import org.apache.turbine.TemplateContext;
import org.apache.turbine.TemplateScreen;

import com.cyclops.a4trim.om.CategoryPeer;
/**
 * @author joeblack
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class EditCategory extends TemplateScreen {
    /** Method doBuildTemplate()
     * @see org.apache.turbine.modules.Module#doBuildTemplate(org.apache.turbine.RunData, org.apache.turbine.TemplateContext)
     */
    protected void doBuildTemplate(RunData data, TemplateContext ctx)
        throws Exception {
        Criteria crit = new Criteria();
        crit.addAscendingOrderByColumn(CategoryPeer.CATEGORY_PATH);
        List categories = CategoryPeer.doSelect(crit);
        ctx.put("categories", categories);
    }
}
