/*
 * Created on 2003-10-22
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado.bo;
/**
 * @author jiaqi guo
 * @email g-cyclops@users.sourceforge.net
 */
public interface LastAccessTrackable {
    /** Method getLastAccess()
     * @return Last access time
     */
    long getLastAccess();
    /** Method setLastAccess()
     * @param value last access time
     */
    void setLastAccess(long value);
}
