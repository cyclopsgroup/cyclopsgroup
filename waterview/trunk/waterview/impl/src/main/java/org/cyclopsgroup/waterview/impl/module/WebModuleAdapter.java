package org.cyclopsgroup.waterview.impl.module;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.apache.commons.lang.Validate;
import org.cyclopsgroup.waterview.Module;
import org.cyclopsgroup.waterview.WebContext;

/**
 * Internal adapter that implements WebModule
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
class WebModuleAdapter
    implements WebModule
{
    private static final Class<?>[] METHOD_PARAMETER_TYPES = new Class<?>[] { WebContext.class };

    private final Module definition;

    private final Method method;

    private final Object object;
    
    /**
     * @param object Object to invoke upon
     * @throws IllegalArgumentException Thrown when object is not annotated with right Annotation, method doesn't exist
     *             or not accessible
     */
    WebModuleAdapter( Object object )
        throws IllegalArgumentException
    {
        Validate.notNull( object, "Internal object can't be NULL" );
        this.object = object;

        definition = object.getClass().getAnnotation( Module.class );
        if ( definition == null )
        {
            throw new IllegalArgumentException( "Class " + object.getClass() + " is not annotated with " + Module.class );
        }
        try
        {
            method = object.getClass().getMethod( definition.method(), METHOD_PARAMETER_TYPES );
        }
        catch ( SecurityException e )
        {
            throw new IllegalArgumentException( "Method " + definition.method() + " of " + object + " isn't accessible", e );
        }
        catch ( NoSuchMethodException e )
        {
            throw new IllegalArgumentException( "Method " + definition.method() + "(WebContext) doesn't exist", e );
        }
        if ( ( method.getModifiers() & Modifier.PUBLIC ) == 0 )
        {
            throw new IllegalArgumentException( "Method " + definition.method() + "(WebContext) of " + object +
                " is not public" );
        }
    }

    /**
     * @return Value of field definition
     */
    public final Module getDefinition()
    {
        return definition;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getPath()
    {
        return definition.path();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void render( WebContext context )
    {
        try
        {
            method.invoke( object, context );
        }
        catch ( IllegalAccessException e )
        {
            throw new RuntimeException( "Method " + method + " illegal access", e );
        }
        catch ( InvocationTargetException e )
        {
            throw new RuntimeException( "Invocation of " + method + " on " + object + " failed", e );
        }
    }
}
