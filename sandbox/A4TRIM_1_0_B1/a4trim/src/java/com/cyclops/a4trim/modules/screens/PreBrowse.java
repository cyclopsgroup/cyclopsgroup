/*
 * Created on 2003-9-25
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.a4trim.modules.screens;
import org.apache.turbine.ParameterParser;
import org.apache.turbine.RunData;
import org.apache.turbine.TemplateContext;
import org.apache.turbine.TemplateScreen;

import com.cyclops.a4trim.SearchCriteria;
/**
 * @author joeblack
 * @since 2003-9-25 16:17:23
 */
public class PreBrowse extends TemplateScreen {
    /** Method doBuildTemplate()
     * @see org.apache.turbine.modules.Module#doBuildTemplate(org.apache.turbine.RunData, org.apache.turbine.TemplateContext)
     */
    protected void doBuildTemplate(RunData data, TemplateContext ctx)
        throws Exception {
        ParameterParser params = data.getParameters();
        SearchCriteria sc = new SearchCriteria();
        sc.setKeyword(params.getString("keyword", ""));
        sc.setProductCode(params.getString("product_code", ""));
        sc.setPageSize(params.getInt("page_size", sc.getPageSize()));
        String[] categoryPaths = params.getStrings("category_path");
        for (int i = 0;
            categoryPaths != null && i < categoryPaths.length;
            i++) {
            String cp = categoryPaths[i];
            sc.addCategoryPath(cp);
        }
        data.getUser().setTemp(SearchCriteria.KEY, sc);
    }
}
