package org.cyclopsgroup.doorman.service.hibernate;

import org.cyclopsgroup.doorman.api.UserOperationResult;

@SuppressWarnings( "serial" )
public class DataOperationException
    extends RuntimeException
{
    private final UserOperationResult result;

    DataOperationException( UserOperationResult result, String message )
    {
        super( message );
        this.result = result;
    }

    public final UserOperationResult getOperationResult()
    {
        return result;
    }
}
