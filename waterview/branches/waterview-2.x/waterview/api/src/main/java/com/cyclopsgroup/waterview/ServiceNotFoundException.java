package com.cyclopsgroup.waterview;

public class ServiceNotFoundException
    extends Exception
{
    private static final long serialVersionUID = 1L;

    public ServiceNotFoundException( String serviceRole )
    {
        super( "Service [" + serviceRole + "] is not found in container" );
    }
}
