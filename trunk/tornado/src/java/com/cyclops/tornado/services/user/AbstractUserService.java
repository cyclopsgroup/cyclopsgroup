/*
 * Created on 2003-9-29
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado.services.user;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.lang.StringUtils;

import com.cyclops.tornado.BrokerManager;
import com.cyclops.tornado.services.BaseService;
import com.cyclops.tornado.services.Restartable;
/** Default implementation of UserService
 * @author joeblack
 * @since 2003-9-29 17:21:32
 *
 * Class
 */
public abstract class AbstractUserService
    extends BaseService
    implements UserService, Restartable {
    private final class CheckingThread implements Runnable {
        public void run() {
            long checkInteval =
                configuration.getLong("user.inteval", DEFAULT_USER_INTEVAL);
            boolean running = true;
            while (running) {
                try {
                    ArrayList tobeRemoved = new ArrayList();
                    Iterator i = userRepo.keySet().iterator();
                    while (i.hasNext()) {
                        String key = (String) i.next();
                        UserEntry entry = (UserEntry) userRepo.get(key);
                        if (entry.isExpired()) {
                            tobeRemoved.add(key);
                        }
                    }
                    for (i = tobeRemoved.iterator(); i.hasNext();) {
                        String key = (String) i.next();
                        userRepo.remove(key);
                    }
                    Thread.sleep(checkInteval);
                } catch (InterruptedException e) {
                    running = false;
                }
            }
        }
    }
    private final class UserEntry {
        private long latestAccess;
        private User user;
        private UserEntry(User usr) {
            this.user = usr;
            latestAccess = System.currentTimeMillis();
        }
        private boolean isExpired() {
            return (!user.isAnonymous())
                && System.currentTimeMillis() - latestAccess > userTimeout;
        }
    }
    /** Default user interval value */
    public static final long DEFAULT_USER_INTEVAL = 1000L;
    /** Default user timeout */
    public static final long DEFAULT_USER_TIMEOUT = 1800000L;
    private User anonymousUser;
    private Thread checkingThread;
    private Vector userListeners = new Vector();
    private Hashtable userRepo = new Hashtable();
    private long userTimeout;
    /** Method getActiveUser()
     * @see com.cyclops.tornado.services.user.UserService#getActiveUser(java.lang.String)
     */
    public User getActiveUser(String key) {
        if (userRepo.containsKey(key)) {
            UserEntry entry = (UserEntry) userRepo.get(key);
            entry.latestAccess = System.currentTimeMillis();
            return entry.user;
        } else {
            return getAnonymousUser();
        }
    }
    /** Method getActiveUsers()
     * @see com.cyclops.tornado.services.user.UserService#getActiveUsers()
     */
    public User[] getActiveUsers() {
        ArrayList users = new ArrayList();
        for (Iterator i = userRepo.values().iterator(); i.hasNext();) {
            UserEntry entry = (UserEntry) i.next();
            users.add(entry.user);
        }
        return (User[]) users.toArray(User.EMPTY_ARRAY);
    }
    /** Method getAnonymousUser()
     * @see com.cyclops.tornado.services.user.UserService#getAnonymousUser()
     */
    public User getAnonymousUser() {
        return anonymousUser;
    }
    /** Method initialize()
     * @see com.cyclops.tornado.services.BaseService#initialize(org.apache.commons.configuration.Configuration)
     */
    protected void initialize(Configuration conf, BrokerManager brokerManager)
        throws Exception {
        String anonymousUserName = conf.getString("user.anonymous", "guest");
        userTimeout = conf.getLong("user.timeout", DEFAULT_USER_TIMEOUT);
        HashMap context = new HashMap();
        try {
            anonymousUser = loadUser(anonymousUserName, true, brokerManager);
            userRepo.put(anonymousUser.getName(), new UserEntry(anonymousUser));
            triggerSigninEvent(new UserEvent(anonymousUser, brokerManager));
        } catch (Exception e) {
            logger.error("Load guest error", e);
        } finally {
            checkingThread = new Thread(new CheckingThread());
            checkingThread.setDaemon(true);
            checkingThread.setPriority(Thread.MIN_PRIORITY);
            checkingThread.start();
        }
    }
    /** In default implementation, it load user object from database.
     * New implementation of this class can override this method to make different authorization way.
     * @param userName Name of the user
     * @param isAnonymous If this user is anonymous user
     * @param brokerManager BrokerManager object
     * @return User object, null if loading failed
     * @throws Exception DB exception
     */
    protected User loadUser(
        String userName,
        boolean isAnonymous,
        BrokerManager brokerManager)
        throws Exception {
        String userClassName =
            configuration.getString(
                "user.implementation",
                DefaultUser.class.getName());
        User du = (User) Class.forName(userClassName).newInstance();
        du.setName(userName);
        du.setAnonymous(isAnonymous);
        return du;
    }
    /** Method shutdown()
     * @see org.apache.fulcrum.Service#shutdown()
     */
    public void shutdown() {
        checkingThread.stop();
        userRepo.clear();
        userListeners.clear();
        anonymousUser = null;
    }
    /** Implementation of method singin() in this class
     * @see com.cyclops.tornado.services.user.UserService#singin(java.lang.String, java.lang.String, com.cyclops.tornado.BrokerManager)
     */
    public void singin(
        String key,
        String userName,
        BrokerManager brokerManager) {
        boolean existed = false;
        User user = null;
        for (Iterator i = userRepo.values().iterator(); i.hasNext();) {
            UserEntry entry = (UserEntry) i.next();
            if (StringUtils.equals(userName, entry.user.getName())) {
                existed = true;
                userRepo.put(key, entry);
                user = entry.user;
                entry.latestAccess = System.currentTimeMillis();
                break;
            }
        }
        if (!existed) {
            try {
                User userInstance = loadUser(userName, false, brokerManager);
                UserEntry entry = new UserEntry(userInstance);
                userRepo.put(key, entry);
                user = userInstance;
            } catch (Exception e) {
                logger.error("Loading User Error", e);
            }
        }
        triggerSigninEvent(new UserEvent(user, brokerManager));
    }
    /** Implementation of method singout() in this class
     * @see com.cyclops.tornado.services.user.UserService#singout(java.lang.String, com.cyclops.tornado.BrokerManager)
     */
    public void singout(String key, BrokerManager brokerManager) {
        if (userRepo.containsKey(key)) {
            UserEntry entry = (UserEntry) userRepo.get(key);
            triggerSignoutEvent(new UserEvent(entry.user, brokerManager));
            userRepo.remove(key);
        }
    }
    private void triggerSigninEvent(UserEvent event) {
        for (Iterator i = userListeners.iterator(); i.hasNext();) {
            UserListener listener = (UserListener) i.next();
            try {
                listener.onSingIn(event);
            } catch (Exception e) {
                logger.error(e);
            }
        }
    }
    private void triggerSignoutEvent(UserEvent event) {
        for (Iterator i = userListeners.iterator(); i.hasNext();) {
            UserListener listener = (UserListener) i.next();
            try {
                listener.onSingOut(event);
            } catch (Exception e) {
                logger.error(e);
            }
        }
    }
}
