package org.cyclopsgroup.waterview.impl;

import java.io.IOException;

import org.cyclopsgroup.waterview.WebContext;

/**
 * The processor interface servlet calls
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public interface WebContextProcessor
{
    /**
     * @param context Web context
     * @throws IOException Thrown to servlet
     */
    void process( WebContext context )
        throws IOException;
}
