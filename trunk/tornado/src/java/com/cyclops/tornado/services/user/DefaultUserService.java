/*
 * Created on 2003-9-29
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado.services.user;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

import org.apache.commons.configuration.Configuration;

import com.cyclops.tornado.services.BaseService;
/** Default implementation of UserService
 * @author joeblack
 * @since 2003-9-29 17:21:32
 *
 * Class
 */
public class DefaultUserService extends BaseService implements UserService {
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
    private final class CheckingThread implements Runnable {
        public void run() {
            long checkInteval =
                configuration.getLong("user.inteval", DEFAULT_USER_INTEVAL);
            boolean running = true;
            while (running) {
                try {
                    ArrayList tobeRemoved = new ArrayList();
                    for (Iterator i = userRepo.keySet().iterator();
                            i.hasNext();) {
                        String key = (String) i.next();
                        UserEntry entry = (UserEntry) userRepo.get(key);
                        if (entry.isExpired()) {
                            tobeRemoved.add(key);
                        }
                    }
                    for (Iterator i = tobeRemoved.iterator(); i.hasNext();) {
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
    private static final long DEFAULT_USER_TIMEOUT = 1800000L;
    private static final long DEFAULT_USER_INTEVAL = 1000L;
    private User anonymousUser;
    private String userImpl;
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
            return null;
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
    protected void initialize(Configuration conf) throws Exception {
        String anonymousUserName = conf.getString("user.anonymous", "guest");
        userTimeout = conf.getLong("user.timeout", DEFAULT_USER_TIMEOUT);
        anonymousUser = loadUser(anonymousUserName, true);
        userRepo.put(anonymousUser.getName(), anonymousUser);
    }
    /** Method loadUser() in Class DefaultUserService
     * @param userName Name of the user
     * @param isAnonymous If this user is anonymous user
     * @return User object, null if loading failed
     */
    protected User loadUser(String userName, boolean isAnonymous) {
        return null;
    }
    /** Method shutdown()
     * @see org.apache.fulcrum.Service#shutdown()
     */
    public void shutdown() {
        super.shutdown();
    }
}
