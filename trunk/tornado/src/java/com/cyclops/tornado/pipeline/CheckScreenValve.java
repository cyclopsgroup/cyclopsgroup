/*
 * Created on 2003-10-26
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.tornado.pipeline;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.turbine.RunData;
import org.apache.turbine.TurbineException;
import org.apache.turbine.ValveContext;
import org.apache.turbine.pipeline.AbstractValve;

import com.cyclops.tornado.Passport;
import com.cyclops.tornado.TornadoUser;
import com.cyclops.tornado.modules.ScreenAsset;
/**
 * @author jiaqi guo
 * @email g-cyclops@users.sourceforge.net
 */
public class CheckScreenValve extends AbstractValve {
    private Log logger = LogFactory.getLog(getClass());
    /** Implementation of method invoke() in this class
     * @see org.apache.turbine.Valve#invoke(org.apache.turbine.RunData, org.apache.turbine.ValveContext)
     */
    public void invoke(RunData data, ValveContext ctx)
        throws IOException, TurbineException {
        try {
            TornadoUser user = TornadoUser.getInstance(data);
            Passport passport = user.getPassport();
            ScreenAsset sa = new ScreenAsset(data.getTarget());
            if (!passport.accept(sa)) {
                data.setTarget("Default.vm");
            }
        } catch (Exception e) {
            logger.error("Security check failed", e);
        } finally {
            ctx.invokeNext(data);
        }
    }
}
