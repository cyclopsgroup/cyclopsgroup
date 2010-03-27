package org.cyclopsgroup.spee;

public class ExecutionException
    extends RuntimeException
{
    private static final long serialVersionUID = 5717699088539131676L;

    public ExecutionException( String description, Throwable cause )
    {
        super( description, cause );
    }

    public ExecutionException( String description )
    {
        super( description );
    }

    public ExecutionException( Throwable cause )
    {
        super( cause );
    }
}
