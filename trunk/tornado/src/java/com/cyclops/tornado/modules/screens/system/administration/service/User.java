/*
 * Created on 2003-10-24
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado.modules.screens.system.administration.service;
import com.cyclops.tornado.services.user.UserService;
/**
 * @author jiaqi guo
 * @email g-cyclops@users.sourceforge.net
 */
public class User extends BaseServiceScreen {
    /** Implementation of method getServiceName() in this class
     * @see com.cyclops.tornado.modules.screens.system.administration.service.BaseServiceScreen#getServiceName()
     */
    protected String getServiceName() {
        return UserService.SERVICE_NAME;
    }
}
