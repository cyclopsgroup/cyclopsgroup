/*
 * Created on 2003-10-22
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado.modules;
import com.cyclops.tornado.Asset;
/**
 * @author jiaqi guo
 * @email g-cyclops@users.sourceforge.net
 */
public class ActionAsset implements Asset {
    /** Name of this asset, &quot;action&quot; */
    public static final String ASSET_NAME = "action";
    private String event;
    private String target;
    /** Only constructor of this asset
     * @param actionTarget target
     * @param actionEvent event
     */
    public ActionAsset(String actionTarget, String actionEvent) {
        target = actionTarget;
        event = actionEvent;
    }
    /** Implementation of method getAssetCode() in this class
     * @see com.cyclops.tornado.Asset#getAssetCode()
     */
    public String getAssetCode() {
        return ASSET_NAME + ":" + target + "|" + event;
    }
}
