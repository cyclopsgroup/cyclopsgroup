/*
 * Created on 2003-9-24
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.a4trim.modules.actions;
import java.util.List;

import org.apache.torque.util.Criteria;
import org.apache.turbine.ParameterParser;
import org.apache.turbine.RunData;
import org.apache.turbine.TemplateAction;
import org.apache.turbine.TemplateContext;

import com.cyclops.a4trim.om.Category;
import com.cyclops.a4trim.om.CategoryPeer;
import com.cyclops.a4trim.om.ProductCategoryPeer;
import com.cyclops.a4trim.tools.A4TrimTool;
import com.cyclops.a4trim.utils.NumberUtils;
/**
 * @author joeblack
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CategoryAction extends TemplateAction {
    private static final int CODE_LENGTH = 4;
    /** Method doAdd() in Class CategoryAction
     * @param data RunData object as input
     * @param ctx TemplateContext object as output
     * @throws Exception Throw when action failed
     */
    public void doAdd(RunData data, TemplateContext ctx) throws Exception {
        ParameterParser params = data.getParameters();
        String categoryCode = params.getString("category_code");
        Criteria crit = new Criteria();
        crit.and(CategoryPeer.CATEGORY_CODE, categoryCode);
        List existed = CategoryPeer.doSelect(crit);
        if (!existed.isEmpty()) {
            data.setMessage(
                "Product Category #" + categoryCode + " already exists!");
            return;
        }
        int parentId = params.getInt("parent_id", -1);
        Category parent = null;
        if (parentId > 0) {
            parent = CategoryPeer.retrieveByPK(parentId);
        }
        Category category = new Category();
        category.setCategoryCode(categoryCode);
        category.setDepth((parent == null) ? 0 : (parent.getDepth() + 1));
        category.setDescription(params.getString("description"));
        category.setParentId(parentId);
        category.setCategoryPath("/");
        category.save();
        StringBuffer path =
            new StringBuffer(parent == null ? "/" : parent.getCategoryPath());
        path.append(
            NumberUtils.toHex(category.getCategoryId(), CODE_LENGTH)).append(
            "/");
        category.setCategoryPath(path.toString());
        category.save();
        if (params.getBoolean("close_window")) {
            data.setTarget("admin,EditCategory.vm");
        }
        if (parentId == -1) {
            A4TrimTool tool = (A4TrimTool) getTool(ctx, "a4trim");
            tool.reloadRootCategories();
        }
    }
    /** Method doDelete() in Class CategoryAction
     * @param data RunData object as Input
     * @param ctx TemplateContext object as Output
     * @throws Exception anything could happen here
     */
    public void doDelete(RunData data, TemplateContext ctx) throws Exception {
        ParameterParser params = data.getParameters();
        int id = params.getInt("category_id", -1);
        if (id <= 0) {
            data.setMessage("The root category is undeletable");
            return;
        }
        Category category = CategoryPeer.retrieveByPK(id);
        Criteria crit = new Criteria();
        crit.and(
            ProductCategoryPeer.CATEGORY_PATH,
            (Object) (category.getCategoryPath() + "%"),
            Criteria.LIKE);
        ProductCategoryPeer.doDelete(crit);
        crit = new Criteria();
        crit.and(
            CategoryPeer.CATEGORY_PATH,
            (Object) (category.getCategoryPath() + "%"),
            Criteria.LIKE);
        CategoryPeer.doDelete(crit);
        if (category.getParentId() == -1) {
            A4TrimTool tool = (A4TrimTool) getTool(ctx, "a4trim");
            tool.reloadRootCategories();
        }
    }
    /** Save user change for product category
     * @param data RunData object as Input
     * @param ctx TemplateContext object as Output
     * @throws Exception anything could happen here
     */
    public void doSave(RunData data, TemplateContext ctx) throws Exception {
        ParameterParser params = data.getParameters();
        int id = params.getInt("category_id");
        Category category = CategoryPeer.retrieveByPK(id);
        category.setDescription(params.getString("description"));
        category.save();
        data.setMessage(
            "Product Category #" + category.getCategoryCode() + " changed");
    }
}
