/*
 * Created on 2003-10-22
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado.services.user;
import com.cyclops.tornado.BrokerManager;
/**
 * @author jiaqi guo
 * @email g-cyclops@users.sourceforge.net
 */
public class UserEvent {
    private BrokerManager brokerManager;
    private User user;
    /** Constructor of UserEvent
     * @param u User object
     * @param bm BrokerManager instance
     */
    public UserEvent(User u, BrokerManager bm) {
        user = u;
        brokerManager = bm;
    }
    /** Method getBrokerManager()
     * @return BrokerManager instance
     */
    public BrokerManager getBrokerManager() {
        return brokerManager;
    }
    /** Method getUser()
     * @return User instance
     */
    public User getUser() {
        return user;
    }
}
