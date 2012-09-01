package org.cyclopsgroup.thomasd.api.intel;

import java.io.IOException;

public class IntelNotFoundException
    extends IOException
{
    private static final long serialVersionUID = 1L;

    public IntelNotFoundException( String message, Throwable cause )
    {
        super( message, cause );
    }
}
