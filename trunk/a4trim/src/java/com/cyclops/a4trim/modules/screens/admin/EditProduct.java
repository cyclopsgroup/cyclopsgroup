/*
 * Created on 2003-9-24
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.a4trim.modules.screens.admin;
import java.util.List;

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
 * @since 2003-9-24 22:33:09
 *
 */
public class EditProduct extends TemplateScreen {
    /** Method doBuildTemplate()
     * @see org.apache.turbine.modules.Module#doBuildTemplate(org.apache.turbine.RunData, org.apache.turbine.TemplateContext)
     */
    protected void doBuildTemplate(RunData data, TemplateContext ctx)
        throws Exception {
        ParameterParser params = data.getParameters();
        String productCode = params.getString("product_code");
        ctx.put("productCode", productCode);
        Criteria crit = new Criteria();
        crit.and(ProductPeer.PRODUCT_CODE, productCode);
        List rs = ProductPeer.doSelect(crit);
        ctx.put("productSelected", new Boolean(!rs.isEmpty()));
        if (rs.isEmpty()) {
            data.setMessage("Please input product code to display the product");
            return;
        }
        Product product = (Product) rs.get(0);
        ctx.put("product", product);
        crit = new Criteria();
        crit.and(ProductCategoryPeer.PRODUCT_ID, product.getProductId());
        crit.addJoin(ProductCategoryPeer.CATEGORY_ID, CategoryPeer.CATEGORY_ID);
        List categories = CategoryPeer.doSelect(crit);
        ctx.put("categories", categories);
    }
}
