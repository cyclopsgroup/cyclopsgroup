package org.cyclopsgroup.waterview.impl.module;

import org.cyclopsgroup.waterview.spi.WebContext;

/**
 * A class that can figure out input parameter from given context
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
abstract class ParameterBuilder
{
    /**
     * Create parameter value based on given context
     * 
     * @param context Given web context
     * @return Value to pass
     */
    abstract Object buildParameter(WebContext context);
}
