/*
 * Created on 2003-9-29
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado.services;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cyclops.tornado.PathTransformable;
/**
 * @author joeblack
 * @since 2003-9-29 17:03:56
 *
 * Class BaseService in tornado project
 */
public abstract class BaseService
    extends org.apache.fulcrum.BaseService
    implements PathTransformable {
    /** Log object used in the derived classes */
    protected Log logger = LogFactory.getLog(getClass());
    /** Configuration object for this service */
    protected Configuration configuration;
    /** Method init()
     * @see org.apache.fulcrum.Service#init()
     */
    public void init() {
        try {
            configuration = getConfiguration();
            initialize(configuration);
        } catch (Exception e) {
            logger.error("Service " + getName() + "failed to initialize", e);
        } finally {
            setInit(true);
        }
    }
    /** Derived classes will override this method to initialize service
     * @param conf Configuration object
     * @throws Exception Any exception could be thrown
     */
    protected abstract void initialize(Configuration conf) throws Exception;
}
