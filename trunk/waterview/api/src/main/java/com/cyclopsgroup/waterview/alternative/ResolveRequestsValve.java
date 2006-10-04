package com.cyclopsgroup.waterview.alternative;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import com.cyclopsgroup.waterview.ExecutionException;
import com.cyclopsgroup.waterview.spi.PipelineContext;
import com.cyclopsgroup.waterview.spi.RequestResolver;
import com.cyclopsgroup.waterview.spi.RunDataSpi;
import com.cyclopsgroup.waterview.spi.Valve;

/**
 * Valve that resolve all requests in data
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class ResolveRequestsValve
    implements Valve
{
    private final List<RequestResolver> resolvers = new Vector<RequestResolver>();

    public void addRequestResolver( RequestResolver resolver )
    {
        resolvers.add( resolver );
    }

    public void invoke( RunDataSpi data, PipelineContext context )
        throws ExecutionException, IOException
    {
        for ( RequestResolver resolver : resolvers )
        {
            for ( RunDataSpi.Request request : data.getRequests() )
            {
                if ( resolver.isRequestResolvable( request ) )
                {
                    resolver.resolveRequest( data, request );
                }
            }
        }

        context.invokeNextValve( data );
    }
}
