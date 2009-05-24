package org.cyclopsgroup.waterview.impl.module;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.lang.StringUtils;
import org.cyclopsgroup.waterview.Module;
import org.cyclopsgroup.waterview.spi.WebContext;

/**
 * Internal adapter that implements {@literal WebModule}. Internally it takes a POJO annotated with {@link Module}.
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
class WebModuleAdapter
    implements WebModule
{
    private final Module definition;

    private final Method method;

    private final Object object;

    private final ParametersBuilder parametersBuilder;

    /**
     * @param object Object to invoke upon
     * @param method Method to invoke upon
     * @throws IllegalArgumentException Thrown when object is not annotated with right Annotation, method doesn't exist
     *             or not accessible
     */
    WebModuleAdapter( Object object, Method method )
        throws IllegalArgumentException
    {
        this.object = object;
        this.method = method;
        definition = method.getAnnotation( Module.class );
        parametersBuilder = new ParametersBuilder( method );
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
    public String getTemplate()
    {
        return definition.template();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void render( WebContext context )
    {
        Object[] parameters = parametersBuilder.createParameters( context );
        Object result;
        try
        {
            result = method.invoke( object, parameters );
        }
        catch ( IllegalAccessException e )
        {
            throw new RuntimeException( "Method " + method + " illegal access", e );
        }
        catch ( InvocationTargetException e )
        {
            throw new RuntimeException( "Invocation of " + method + " on " + object + " failed", e );
        }
        if ( StringUtils.isNotEmpty( definition.returnVariable() ) )
        {
            context.setVariable( definition.returnVariable(), result );
        }
    }
}
