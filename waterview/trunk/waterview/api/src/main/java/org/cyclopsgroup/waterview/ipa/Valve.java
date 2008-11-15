package org.cyclopsgroup.waterview.ipa;

import java.io.IOException;

/**
 * A step of processing pipeline
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public interface Valve
{
    /**
     * @param context Valve context
     * @throws IOException
     */
    void invoke( ValveContext context )
        throws IOException;
}
