/*
 * Created on 2003-9-24
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.a4trim.modules.screens;
import java.util.List;

import org.apache.torque.util.Criteria;
import org.apache.turbine.RunData;
import org.apache.turbine.TemplateContext;
import org.apache.turbine.TemplateScreen;

import com.cyclops.a4trim.SearchCriteria;
import com.cyclops.a4trim.om.Category;
import com.cyclops.a4trim.om.CategoryPeer;
/**
 * @author joeblack
 * @since 2003-9-24 15:57:09
 */
public class AdvancedSearch extends TemplateScreen {
    /** Method doBuildTemplate()
     * @see org.apache.turbine.modules.Module#doBuildTemplate(org.apache.turbine.RunData, org.apache.turbine.TemplateContext)
     */
    protected void doBuildTemplate(RunData data, TemplateContext ctx)
        throws Exception {
        int rootId = data.getParameters().getInt("root_id", -1);
        ctx.put("rootId", new Integer(rootId));
        Criteria crit = new Criteria();
        if (rootId > 0) {
            Category root = CategoryPeer.retrieveByPK(rootId);
            ctx.put("rootCategory", root);
            crit.and(
                CategoryPeer.CATEGORY_PATH,
                (Object) (root.getCategoryPath() + "%"),
                Criteria.LIKE);
        }
        crit.addAscendingOrderByColumn(CategoryPeer.CATEGORY_PATH);
        List categories = CategoryPeer.doSelect(crit);
        ctx.put("categories", categories);
        SearchCriteria sc =
            (SearchCriteria) data.getUser().getTemp(SearchCriteria.KEY);
        if (sc == null) {
            sc = new SearchCriteria();
            sc.addCategoryPath("/");
            data.getUser().setTemp(SearchCriteria.KEY, sc);
        }
        ctx.put("searchCriteria", sc);
    }
}
