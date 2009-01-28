package org.cyclopsgroup.jemf.runner;

public class MethodResult
{
    private String description;
    private long executionTime;
    private String methodName;
    private int repeat;
    public final String getDescription()
    {
        return description;
    }
    public final long getExecutionTime()
    {
        return executionTime;
    }
    public final String getMethodName()
    {
        return methodName;
    }
    public final int getRepeat()
    {
        return repeat;
    }
    public final void setDescription( String description )
    {
        this.description = description;
    }
    public final void setExecutionTime( long executionTime )
    {
        this.executionTime = executionTime;
    }
    public final void setMethodName( String methodName )
    {
        this.methodName = methodName;
    }
    public final void setRepeat( int repeat )
    {
        this.repeat = repeat;
    }
}
