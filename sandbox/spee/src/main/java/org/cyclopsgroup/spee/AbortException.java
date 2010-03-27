package org.cyclopsgroup.spee;

import java.io.Serializable;

public class AbortException
    extends ExecutionException
{
    private static final long serialVersionUID = 1L;

    private final Serializable message;

    public AbortException( Serializable message, String description )
    {
        super( "Flow is aborted: " + description + "! message=" + message );
        this.message = message;
    }

    public final Serializable getErrorMessage()
    {
        return message;
    }
}
