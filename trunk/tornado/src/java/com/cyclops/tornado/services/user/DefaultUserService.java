/*
 * Created on 2003-10-4
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado.services.user;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.httpclient.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.torque.util.BasePeer;
import org.apache.torque.util.Criteria;

import com.cyclops.tornado.om.UserPeer;
/** Default UserService implementation
 * @author joeblack
 * @since 2003-10-4 20:05:45
 */
public class DefaultUserService extends AbstractUserService {
    /** Method checkUser()
     * @see com.cyclops.tornado.services.user.UserService#checkUser(java.lang.String, java.lang.String)
     */
    public int checkUser(String userName, String password) {
        if (StringUtils.equals(userName, getAnonymousUser().getName())) {
            return CHECK_RESULT_DISABLED_USER;
        }
        try {
            Criteria crit = new Criteria();
            crit.and(UserPeer.USER_NAME, userName);
            List rs = UserPeer.doSelect(crit);
            if (rs.isEmpty()) {
                return CHECK_RESULT_INVALID_USER;
            }
            com.cyclops.tornado.om.User user =
                (com.cyclops.tornado.om.User) rs.get(0);
            if (user.getIsDisabled()) {
                return CHECK_RESULT_DISABLED_USER;
            }
            String pwd =
                new String(
                    Base64.decode(user.getEncryptedPassword().getBytes()));
            if (!StringUtils.equals(password, pwd)) {
                return CHECK_RESULT_INCORRECT_PASSWORD;
            }
            return CHECK_RESULT_OK;
        } catch (Exception e) {
            logger.error("User identification exception", e);
            return CHECK_RESULT_EXCEPTION;
        }
    }
    /** Method loadUser()
     * @see com.cyclops.tornado.services.user.AbstractUserService#loadUser(java.lang.String, boolean)
     */
    protected User loadUser(String userName, boolean isAnonymous)
        throws Exception {
        User user = super.loadUser(userName, isAnonymous);
        Criteria crit = new Criteria();
        crit.and(UserPeer.USER_NAME, userName);
        List rs = BasePeer.doSelect(crit);
        BeanUtils.copyProperties(rs.get(0), user);
        user.setName(userName);
        user.setAnonymous(isAnonymous);
        return user;
    }
}
