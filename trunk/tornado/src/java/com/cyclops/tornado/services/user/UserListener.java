/*
 * Created on 2003-10-10
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado.services.user;
/**
 * @author jiaqi guo
 * @since 2003-10-10 17:58:24
 */
public interface UserListener {
    /**
     * Method onSingIn() in Class UserListener
     * Triggered after user signed in
     * @param user Signed user
     */
    void onSingIn(User user);
    /** Method onSingOut() in Class UserListener
     * Triggered before user signed out
     * @param user Signed out user
     */
    void onSingOut(User user);
}
