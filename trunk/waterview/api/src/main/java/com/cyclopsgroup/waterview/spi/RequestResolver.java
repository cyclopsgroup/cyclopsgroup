package com.cyclopsgroup.waterview.spi;

import java.io.IOException;

import com.cyclopsgroup.waterview.ExecutionException;

public interface RequestResolver
{
    boolean isRequestResolvable( RunDataSpi.Request request );

    void resolveRequest( RunDataSpi data, RunDataSpi.Request request )
        throws ExecutionException, IOException;
}
