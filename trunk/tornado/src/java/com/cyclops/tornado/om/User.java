package com.cyclops.tornado.om;
import java.util.Date;

import org.apache.torque.om.Persistent;
/**
 * You should add additional methods to this class to meet the
 * application requirements.  This class will only be generated as
 * long as it does not already exist in the output directory.
 */
public class User
    extends com.cyclops.tornado.om.BaseUser
    implements Persistent {
    /** Method getCreateDate()
     * @see org.apache.fulcrum.security.entity.User#getCreateDate()
     */
    public Date getCreateDate() {
        return new Date(getCreatedTime());
    }
    /** Method getLastAccessDate()
     * @see org.apache.fulcrum.security.entity.User#getLastAccessDate()
     */
    public Date getLastAccessDate() {
        return new Date(getLastAccessTime());
    }
    /** Method getLastLogin()
     * @see org.apache.fulcrum.security.entity.User#getLastLogin()
     */
    public Date getLastLogin() {
        return new Date(getLastLoginTime());
    }
    /** Method setCreateDate()
     * @see org.apache.fulcrum.security.entity.User#setCreateDate(java.util.Date)
     */
    public void setCreateDate(Date date) {
        setCreatedTime(date.getTime());
    }
    /** Method setLastAccessDate()
     * @see org.apache.fulcrum.security.entity.User#setLastAccessDate()
     */
    public void setLastAccessDate() {
        setLastAccessTime(System.currentTimeMillis());
    }
    /** Method setLastLogin()
     * @see org.apache.fulcrum.security.entity.User#setLastLogin(java.util.Date)
     */
    public void setLastLogin(Date date) {
        setLastLoginTime(date.getTime());
    }
}
