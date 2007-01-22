package com.cyclopsgroup.waterview;

public abstract class ServiceManager
{
    public <T> T getService( Class<T> serviceClass )
        throws ServiceNotFoundException
    {
        return getService( serviceClass.getName() );
    }

    public <T> T getService( Class<T> serviceClass, String roleHint )
        throws ServiceNotFoundException
    {
        return getService( serviceClass.getName() + '.' + roleHint );
    }

    public abstract <T> T getService( String serviceRole )
        throws ServiceNotFoundException;
}
