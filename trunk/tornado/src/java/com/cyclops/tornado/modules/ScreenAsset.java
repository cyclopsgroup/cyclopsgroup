/*
 * Created on 2003-10-22
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado.modules;
import org.apache.commons.lang.StringUtils;

import com.cyclops.tornado.Asset;
/**
 * @author jiaqi guo
 * @email g-cyclops@users.sourceforge.net
 */
public class ScreenAsset implements Asset {
    /** Screen asset name */
    public static final String ASSET_NAME = "screen";
    private String target;
    /** Only constructor of ScreenAsset
     * @param template Template or target
     */
    public ScreenAsset(String template) {
        if (template.indexOf("/") != -1) {
            target = StringUtils.join(StringUtils.split(template, "/"), ",");
        } else {
            target = template;
        }
    }
    /** Implementation of method getAssetCode() in this class
     * @see com.cyclops.tornado.Asset#getAssetCode()
     */
    public String getAssetCode() {
        return ASSET_NAME + ":" + target;
    }
}
