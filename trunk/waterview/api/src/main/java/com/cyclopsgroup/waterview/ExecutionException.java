package com.cyclopsgroup.waterview;

public class ExecutionException
    extends Exception
{
    private RunData data;

    public ExecutionException( String msg, Throwable cause, RunData data )
    {
        super( msg, cause );
        this.data = data;
    }

    public ExecutionException( Throwable cause, RunData data )
    {
        super( cause );
        this.data = data;
    }

    public RunData getRunData()
    {
        return data;
    }
}
