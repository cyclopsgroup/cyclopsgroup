package com.cyclops.tornado.om;
import org.apache.commons.httpclient.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.torque.TorqueException;
import org.apache.torque.om.Persistent;

import com.cyclops.tornado.Referencable;
import com.cyclops.tornado.bo.CreatedTimeTrackable;
import com.cyclops.tornado.bo.LastAccessTrackable;
/**
 * You should add additional methods to this class to meet the
 * application requirements.  This class will only be generated as
 * long as it does not already exist in the output directory.
 */
public class User
    extends com.cyclops.tornado.om.BaseUser
    implements Persistent, Referencable, LastAccessTrackable, CreatedTimeTrackable {
    private static final String REFERENCE_CATEGORY = "om.user";
    /** Expose copyTo method
     * @param user
     */
    public void copyTo(User user) throws TorqueException {
        copyInto(user);
        user.setNew(isNew());
        user.setModified(isModified());
        user.setUserId(getUserId());
    }
    /** Full name of this user
     * @return Fist name and last name
     */
    public String getFullName() {
        StringBuffer sb = new StringBuffer();
        if (!StringUtils.isEmpty(getFirstName())) {
            sb.append(getFirstName());
        }
        if (!StringUtils.isEmpty(getMiddleName())) {
            sb.append(" " + getMiddleName());
        }
        if (!StringUtils.isEmpty(getLastName())) {
            sb.append(" " + getLastName());
        }
        return StringUtils.trim(sb.toString());
    }
    /** Method getPassword()
     * @see org.apache.fulcrum.security.entity.User#getPassword()
     */
    public String getPassword() {
        return new String(Base64.decode(getEncryptedPassword().getBytes()));
    }
    /**
     * @see com.cyclops.tornado.Referencable#getReferenceCategory()
     */
    public String getReferenceCategory() {
        return REFERENCE_CATEGORY;
    }
    /**
     * @see com.cyclops.tornado.Referencable#getReferenceKey()
     */
    public String getReferenceKey() {
        return getUserName();
    }
    /** Method setPassword()
     * @see org.apache.fulcrum.security.entity.User#setPassword(java.lang.String)
     */
    public void setPassword(String pass) {
        setEncryptedPassword(new String(Base64.encode(pass.getBytes())));
    }
}
