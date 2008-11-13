package com.cyclopsgroup.waterview;

public abstract class ServiceManager
{
    @SuppressWarnings("unchecked")
    public <T> T getService( Class<T> serviceClass )
        throws ServiceNotFoundException
    {
        return (T) getService( serviceClass.getName() );
    }

    @SuppressWarnings("unchecked")
    public <T> T getService( Class<T> serviceClass, String roleHint )
        throws ServiceNotFoundException
    {
        return (T) getService( serviceClass.getName() + '.' + roleHint );
    }

    public abstract Object getService( String serviceRole )
        throws ServiceNotFoundException;
}
