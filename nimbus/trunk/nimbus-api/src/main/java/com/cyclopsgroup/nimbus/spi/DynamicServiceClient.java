package com.cyclopsgroup.nimbus.spi;

import java.lang.reflect.Method;

public abstract class DynamicServiceClient
    implements ServiceClientSpi
{
    public Object call( String methodName, Object[] arguments )
        throws Exception
    {
        Method method = findMethod( methodName, arguments );
        if ( method == null )
        {
            return null;
        }
        return method.invoke( this, arguments );
    }

    private Method findMethod( String methodName, Object[] arguments )
    {
        for ( Method method : getClass().getMethods() )
        {
            if ( !method.getName().equals( methodName ) )
            {
                continue;
            }
            Class<?>[] parameterTypes = method.getParameterTypes();
            if ( parameterTypes.length != arguments.length )
            {
                continue;
            }
            boolean mismatched = false;
            for ( int i = 0; i < parameterTypes.length; i++ )
            {
                if ( !parameterTypes[i].isAssignableFrom( arguments[i].getClass() ) )
                {
                    mismatched = true;
                    break;
                }
            }
            if ( !mismatched )
            {
                return method;
            }
        }
        return null;
    }
}
