/*
 * Created on 2003-9-24
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.a4trim.modules.screens;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.torque.util.BasePeer;
import org.apache.torque.util.Criteria;
import org.apache.turbine.ParameterParser;
import org.apache.turbine.RunData;
import org.apache.turbine.TemplateContext;
import org.apache.turbine.TemplateScreen;

import com.cyclops.a4trim.SearchCriteria;
import com.cyclops.a4trim.om.Category;
import com.cyclops.a4trim.om.CategoryPeer;
import com.cyclops.a4trim.om.ProductCategoryPeer;
import com.cyclops.a4trim.om.ProductPeer;
/**
 * @author joeblack
 * @since 2003-9-24 16:56:08
 *
 */
public class Browse extends TemplateScreen {
    /** Method doBuildTemplate()
     * @see org.apache.turbine.modules.Module#doBuildTemplate(org.apache.turbine.RunData, org.apache.turbine.TemplateContext)
     */
    protected void doBuildTemplate(RunData data, TemplateContext ctx)
        throws Exception {
        SearchCriteria sc =
            (SearchCriteria) data.getUser().getTemp(SearchCriteria.KEY);
        if (sc == null) {
            sc = new SearchCriteria();
            sc.addCategoryPath("/");
            data.getUser().setTemp(SearchCriteria.KEY, sc);
        }
        ctx.put("searchCriteria", sc);
        ParameterParser params = data.getParameters();
        //pageSize and pageIndex
        int pageIndex = params.getInt("page_index", 0);
        ctx.put("pageIndex", new Integer(pageIndex));
        Criteria crit = new Criteria();
        crit.setLimit(sc.getPageSize());
        crit.setOffset(sc.getPageSize() * pageIndex);
        //Keyword criterion
        if (StringUtils.isNotEmpty(sc.getKeyword())) {
            Criteria.Criterion crn1 =
                crit.getNewCriterion(
                    ProductPeer.DESCRIPTION,
                    (Object) ("%" + sc.getKeyword() + "%"),
                    Criteria.LIKE);
            Criteria.Criterion crn2 =
                crit.getNewCriterion(
                    ProductPeer.PRODUCT_CODE,
                    (Object) ("%" + sc.getKeyword() + "%"),
                    Criteria.LIKE);
            crit.and(crn1.or(crn2));
        }
        //Product code criterion
        if (StringUtils.isNotEmpty(sc.getProductCode())) {
            crit.and(
                ProductPeer.PRODUCT_CODE,
                (Object) ("%" + sc.getProductCode() + "%"),
                Criteria.LIKE);
        }
        boolean needJoin = false;
        //Category paths criteria
        if (!sc.containsRootCategory()) {
            needJoin = true;
            Criteria.Criterion pathCron =
                crit.getNewCriterion(
                    ProductPeer.PRODUCT_ID,
                    new Integer(-1),
                    Criteria.EQUAL);
            for (Iterator i = sc.getCategoryPaths().iterator(); i.hasNext();) {
                String categoryPath = (String) i.next();
                pathCron.or(
                    crit.getNewCriterion(
                        ProductCategoryPeer.CATEGORY_PATH,
                        (Object) (categoryPath + "%"),
                        Criteria.LIKE));
            }
            crit.and(pathCron);
        }
        //Root category criterion and subcategories
        int rootId = params.getInt("root_id");
        ctx.put("rootId", new Integer(rootId));
        Criteria c = new Criteria();
        c.and(CategoryPeer.PARENT_ID, rootId);
        ctx.put("children", CategoryPeer.doSelect(c));
        List parentCategories = new ArrayList();
        if (rootId > 0) {
            Category rootCategory = CategoryPeer.retrieveByPK(rootId);
            ctx.put("rootCategory", rootCategory);
            crit.and(
                ProductCategoryPeer.CATEGORY_PATH,
                (Object) (rootCategory.getCategoryPath() + "%"),
                Criteria.LIKE);
            needJoin = true;
            //parent categories
            int parentId = rootCategory.getParentId();
            while (parentId > 0) {
                Category parent = CategoryPeer.retrieveByPK(parentId);
                parentCategories.add(0, parent);
                parentId = parent.getParentId();
            }
        }
        ctx.put("parentCategories", parentCategories);
        if (needJoin) {
            crit.addJoin(
                ProductCategoryPeer.PRODUCT_ID,
                ProductPeer.PRODUCT_ID);
        }
        ctx.put("isTop", new Boolean(rootId <= 0));
        //Run the query and output results
        crit.setDistinct();
        List result = ProductPeer.doSelect(crit);
        ctx.put("result", result);
        //Query record count, and compute page count
        crit.getSelectColumns().clear();
        crit.addSelectColumn(ProductPeer.PRODUCT_ID);
        int count = BasePeer.doSelect(crit).size();
        ctx.put("recordCount", new Integer(count));
        int pageCount = count / sc.getPageSize();
        if (count % sc.getPageSize() != 0) {
            pageCount++;
        }
        ctx.put("pageCount", new Integer(pageCount));
    }
}
