package org.cyclopsgroup.waterview.impl.render;

import java.io.IOException;

/**
 * Exception is thrown when template of requested path can't be found
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class UnresolvableTemplateException
    extends IOException
{
    private static final long serialVersionUID = -1L;

    /**
     * @param msg Error message
     */
    public UnresolvableTemplateException( String msg )
    {
        super( msg );
    }
}
