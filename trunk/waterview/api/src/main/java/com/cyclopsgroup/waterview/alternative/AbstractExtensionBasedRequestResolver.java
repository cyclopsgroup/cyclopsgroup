package com.cyclopsgroup.waterview.alternative;

import com.cyclopsgroup.waterview.spi.RequestResolver;
import com.cyclopsgroup.waterview.spi.RunDataSpi.Request;

/**
 * Abstract request resolver interesting in request based on path extension
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 */
public abstract class AbstractExtensionBasedRequestResolver
    implements RequestResolver
{
    private final String extension;

    private final String extensionSuffix;

    protected AbstractExtensionBasedRequestResolver( String extension )
    {
        this.extension = extension;
        this.extensionSuffix = "." + extension;
    }

    public final String getExtension()
    {
        return extension;
    }

    /**
     * @see com.cyclopsgroup.waterview.spi.RequestResolver#isRequestResolvable(com.cyclopsgroup.waterview.spi.RunDataSpi.Request)
     */
    public boolean isRequestResolvable( Request request )
    {
        return request.getRequestPath().endsWith( extensionSuffix );
    }
}
