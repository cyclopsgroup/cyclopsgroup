/*
 * Created on 2003-10-25
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado.services;
/**
 * @author jiaqi guo
 * @email g-cyclops@users.sourceforge.net
 */
public interface Restartable {
    /** Restart the service
     * @throws Exception don't need to handle it in service
     */
    void restart() throws Exception;
}
