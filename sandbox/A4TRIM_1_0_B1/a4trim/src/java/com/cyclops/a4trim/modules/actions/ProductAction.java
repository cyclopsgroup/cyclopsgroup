/*
 * Created on 2003-9-24
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.a4trim.modules.actions;
import java.io.File;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.FileUtils;
import org.apache.torque.util.Criteria;
import org.apache.turbine.ParameterParser;
import org.apache.turbine.RunData;
import org.apache.turbine.TemplateAction;
import org.apache.turbine.TemplateContext;
import org.apache.turbine.Turbine;

import com.cyclops.a4trim.A4Trim;
import com.cyclops.a4trim.om.Category;
import com.cyclops.a4trim.om.CategoryPeer;
import com.cyclops.a4trim.om.Product;
import com.cyclops.a4trim.om.ProductCategory;
import com.cyclops.a4trim.om.ProductCategoryPeer;
import com.cyclops.a4trim.om.ProductPeer;
/**
 * @author joeblack
 * @since 2003-9-24 17:54:52
 *
 * Class ProductAction
 */
public class ProductAction extends TemplateAction {
    /** Method doAdd() in Class ProductAction
     * @param data RunData object as Input
     * @param ctx TemplateContext object as Output
     * @throws Exception anything could happen here
     */
    public void doAdd(RunData data, TemplateContext ctx) throws Exception {
        ParameterParser params = data.getParameters();
        String productCode = params.getString("product_code");
        Criteria crit = new Criteria();
        crit.and(ProductPeer.PRODUCT_CODE, productCode);
        List existed = ProductPeer.doSelect(crit);
        if (!existed.isEmpty()) {
            data.setMessage("Product #" + productCode + " already exists!");
            return;
        }
        Product product = new Product();
        product.setProductCode(productCode);
        product.setDescription(params.getString("description"));
        product.setAttribute1(params.getString("attribute_1"));
        product.setAttribute1(params.getString("attribute_2"));
        product.setAttribute1(params.getString("attribute_3"));
        product.setAttribute1(params.getString("attribute_4"));
        product.setAttribute1(params.getString("attribute_5"));
        FileItem fileItem = params.getFileItem("upload_image");
        boolean imageSaved = false;
        try {
            if (fileItem != null) {
                String fileName = new File(fileItem.getName()).getName();
                String folderPath =
                    A4Trim.IMAGE_REPO
                        + "/product/"
                        + product.getProductCode()
                        + "/";
                File folder = new File(Turbine.getRealPath(folderPath));
                if (!folder.isDirectory()) {
                    folder.mkdirs();
                }
                File targetFile = new File(folder, fileName);
                fileItem.write(targetFile.getPath());
                product.setImageUri(folderPath + fileName);
                File thumbnailFile =
                    A4Trim.generateThumbnail(
                        targetFile,
                        A4Trim.PRODUCT_THUMBNAIL_WIDTH,
                        A4Trim.PRODUCT_THUMBNAIL_HEIGHT);
                product.setThumbnailUri(
                    folderPath + "/" + thumbnailFile.getName());
                imageSaved = true;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (imageSaved) {
                product.setImageAvailable(true);
                product.setThumbnailAvailable(true);
            }
            product.save();
            data.setMessage(
                "Product #" + product.getProductCode() + " created");
        }
    }
    /** Method doSave() in Class ProductAction
     * @param data RunData object as Input
     * @param ctx TemplateContext object as Output
     * @throws Exception anything could happen here
     */
    public void doSave(RunData data, TemplateContext ctx) throws Exception {
        ParameterParser params = data.getParameters();
        int id = params.getInt("product_id");
        Product product = ProductPeer.retrieveByPK(id);
        product.setDescription(params.getString("description"));
        int handleImage = params.getInt("handle_image");
        String folderPath =
            A4Trim.IMAGE_REPO + "/product/" + product.getProductCode();
        File folder = new File(Turbine.getRealPath(folderPath));
        if (handleImage == 2) {
            product.setThumbnailAvailable(false);
            product.setImageAvailable(false);
            if (folder.isDirectory()) {
                FileUtils.deleteDirectory(folder);
            }
        }
        if (handleImage == 1) {
            if (!folder.isDirectory()) {
                folder.mkdirs();
            }
            FileItem fileItem = params.getFileItem("upload_image");
            if (fileItem != null) {
                String fileName = new File(fileItem.getName()).getName();
                File targetFile = new File(folder, fileName);
                fileItem.write(targetFile.getPath());
                product.setImageAvailable(true);
                product.setImageUri(folderPath + "/" + fileName);
                File thumbnail =
                    A4Trim.generateThumbnail(
                        targetFile,
                        A4Trim.PRODUCT_THUMBNAIL_WIDTH,
                        A4Trim.PRODUCT_THUMBNAIL_HEIGHT);
                product.setThumbnailAvailable(true);
                product.setThumbnailUri(folderPath + "/" + thumbnail.getName());
            }
        }
        product.save();
        data.setMessage("Your changes has been saved");
    }
    /** Product deletion action
     * @param data RunData object as Input
     * @param ctx TemplateContext object as Output
     * @throws Exception anything could happen here
     */
    public void doDelete(RunData data, TemplateContext ctx) throws Exception {
        ParameterParser params = data.getParameters();
        int id = params.getInt("product_id");
        Criteria crit = new Criteria();
        crit.and(ProductCategoryPeer.PRODUCT_ID, id);
        ProductCategoryPeer.doDelete(crit);
        crit = new Criteria();
        crit.and(ProductPeer.PRODUCT_ID, id);
        ProductPeer.doDelete(crit);
        data.setMessage("Product is deleted");
    }
    /** Method doAssociate() in Class ProductAction
     * @param data RunData object as Input
     * @param ctx TemplateContext object as Output
     * @throws Exception anything could happen here
     */
    public void doAssociate(RunData data, TemplateContext ctx)
        throws Exception {
        ParameterParser params = data.getParameters();
        int id = params.getInt("product_id", -1);
        if (id <= 0) {
            data.setMessage("Invalid product! Association failed.");
        }
        String categoryCode = params.getString("category_code");
        Criteria crit = new Criteria();
        crit.and(CategoryPeer.CATEGORY_CODE, categoryCode);
        List rs = CategoryPeer.doSelect(crit);
        if (rs.isEmpty()) {
            data.setMessage(
                "Invalid category code #"
                    + categoryCode
                    + " !Association failed !");
            return;
        }
        Category category = (Category) rs.get(0);
        ProductCategory pc = new ProductCategory();
        pc.setProductId(id);
        pc.setCategoryId(category.getCategoryId());
        pc.setCategoryPath(category.getCategoryPath());
        pc.save();
    }
    /** Method doDisassociate() in Class ProductAction
     * @param data RunData object as Input
     * @param ctx TemplateContext object as Output
     * @throws Exception anything could happen here
     */
    public void doDisassociate(RunData data, TemplateContext ctx)
        throws Exception {
        ParameterParser params = data.getParameters();
        int productId = params.getInt("product_id", -1);
        int categoryId = params.getInt("category_id", -1);
        Criteria crit = new Criteria();
        crit.and(ProductCategoryPeer.PRODUCT_ID, productId);
        crit.and(ProductCategoryPeer.CATEGORY_ID, categoryId);
        ProductCategoryPeer.doDelete(crit);
    }
}
