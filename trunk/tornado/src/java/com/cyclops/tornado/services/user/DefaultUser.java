/*
 * Created on 2003-9-29
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado.services.user;
/**
 * @author joeblack
 * @since 2003-9-29 17:40:57
 */
public class DefaultUser implements User {
    private boolean isAnonymous;
    private String name;
    /** Method getName()
     * @see com.cyclops.tornado.services.user.User#getName()
     */
    public String getName() {
        return name;
    }
    /** Method isAnonymous()
     * @see com.cyclops.tornado.services.user.User#isAnonymous()
     */
    public boolean isAnonymous() {
        return isAnonymous;
    }
    /** Method setAnonymous() in Class DefaultUser
     * @param b If the user is anonymous
     */
    public void setAnonymous(boolean b) {
        isAnonymous = b;
    }
    /** Method setName() in Class DefaultUser
     * @param string Name of the user
     */
    public void setName(String string) {
        name = string;
    }
}
