/*
 * Created on 2003-10-25
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado.modules;
import com.cyclops.tornado.BrokerManager;
import com.cyclops.tornado.bo.UserBroker;
import com.cyclops.tornado.om.DUser;
import com.cyclops.tornado.services.user.UserEvent;
import com.cyclops.tornado.services.user.UserListener;
/**
 * @author jiaqi guo
 * @email g-cyclops@users.sourceforge.net
 */
public class UserSigninCounter implements UserListener {
    /** Implementation of method onSingIn() in this class
     * @see com.cyclops.tornado.services.user.UserListener#onSingIn(com.cyclops.tornado.services.user.UserEvent)
     */
    public void onSingIn(UserEvent event) throws Exception {
        if (event.getUser().isAnonymous()) {
            return;
        }
        BrokerManager bm = event.getBrokerManager();
        UserBroker ub = (UserBroker) bm.getObjectBroker(UserBroker.class);
        DUser user = ub.retrieveByName(event.getUser().getName());
        user.setLastSignin(System.currentTimeMillis());
        user.setSigninCounter(user.getSigninCounter() + 1);
        ub.save(user);
        if (event.getUser() instanceof DUser) {
            user.copyTo((DUser) event.getUser());
        }
    }
    /** Implementation of method onSingOut() in this class
     * @see com.cyclops.tornado.services.user.UserListener#onSingOut(com.cyclops.tornado.services.user.UserEvent)
     */
    public void onSingOut(UserEvent event) throws Exception {
        //do nothing
    }
}
