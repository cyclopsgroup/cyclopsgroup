/*
 * Created on 2003-9-26
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.a4trim.modules.screens;
import org.apache.torque.util.Criteria;
import org.apache.turbine.ParameterParser;
import org.apache.turbine.RunData;
import org.apache.turbine.TemplateContext;
import org.apache.turbine.TemplateScreen;

import com.cyclops.a4trim.om.CategoryPeer;
import com.cyclops.a4trim.om.Product;
import com.cyclops.a4trim.om.ProductCategoryPeer;
import com.cyclops.a4trim.om.ProductPeer;

/**
 * @author joeblack
 * @since 2003-9-30 18:20:37
 */
public class ProductDetail extends TemplateScreen {
    /** Method doBuildTemplate()
     * @see org.apache.turbine.modules.Module#doBuildTemplate(org.apache.turbine.RunData, org.apache.turbine.TemplateContext)
     */
    protected void doBuildTemplate(RunData data, TemplateContext ctx)
        throws Exception {
        ParameterParser params = data.getParameters();
        int id = params.getInt("product_id", -1);
        if (id <= 0) {
            data.setMessage("Noexisted product!");
            return;
        }
        Product product = ProductPeer.retrieveByPK(id);
        ctx.put("product", product);
        Criteria crit = new Criteria();
        crit.and(ProductCategoryPeer.PRODUCT_ID, id);
        crit.addJoin(ProductCategoryPeer.CATEGORY_ID, CategoryPeer.CATEGORY_ID);
        ctx.put("categories", CategoryPeer.doSelect(crit));
    }
}
