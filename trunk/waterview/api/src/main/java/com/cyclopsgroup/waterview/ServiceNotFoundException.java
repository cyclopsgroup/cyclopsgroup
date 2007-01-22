package com.cyclopsgroup.waterview;

public class ServiceNotFoundException
    extends Exception
{
    public ServiceNotFoundException( String serviceRole )
    {
        super( "Service [" + serviceRole + "] is not found in container" );
    }
}
