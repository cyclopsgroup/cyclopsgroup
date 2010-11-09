package org.cyclopsgroup.doorman.service.dao;

import org.cyclopsgroup.doorman.api.UserOperationResult;

/**
 * An runtime exception that indicates operation fails for known reason and expected result should be returned
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
@SuppressWarnings( "serial" )
public class DataOperationException
    extends RuntimeException
{
    private final UserOperationResult result;

    /**
     * @param result Result expected to return
     * @param message Verbol description of exception
     */
    public DataOperationException( UserOperationResult result, String message )
    {
        super( message );
        this.result = result;
    }

    /**
     * @return The expected result that should be returned
     */
    public final UserOperationResult getOperationResult()
    {
        return result;
    }
}
