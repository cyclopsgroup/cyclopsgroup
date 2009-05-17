package org.cyclopsgroup.waterview.impl.module;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.cyclopsgroup.waterview.InputBean;
import org.cyclopsgroup.waterview.InputParameter;
import org.cyclopsgroup.waterview.spi.WebContext;

/**
 * Builder that builds array of parameters for given method
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
class ParametersBuilder
{
    private final List<ParameterBuilder> parameterBuilders;

    /**
     * @param method
     */
    ParametersBuilder( Method method )
    {
        Class<?>[] paramTypes = method.getParameterTypes();
        List<ParameterBuilder> builders = new ArrayList<ParameterBuilder>( paramTypes.length );

        int index = 0;
        for ( Annotation[] annotations : method.getParameterAnnotations() )
        {
            Class<?> paramType = paramTypes[index++];
            ParameterBuilder builder = null;
            for ( Annotation annotation : annotations )
            {
                if ( annotation instanceof InputParameter )
                {
                    builder = new ValueParameterBuilder( (InputParameter) annotation, paramType );
                    break;
                }
                if ( annotation instanceof InputBean )
                {
                    builder = new BeanParameterBuilder( (InputBean) annotation, paramType );
                    break;
                }
            }
            if ( builder == null )
            {
                throw new IllegalArgumentException(
                                                    "Method is not qualified, one of parameter isn't annotated properly" );
            }
            builders.add( builder );
        }
        parameterBuilders = Collections.unmodifiableList( builders );
    }

    /**
     * @param context Web context input
     * @return Array of parameters to pass
     */
    Object[] createParameters( WebContext context )
    {
        List<Object> result = new ArrayList<Object>();
        for(ParameterBuilder builder:parameterBuilders){
            result.add( builder.buildParameter( context ) );
        }
        return result.toArray();
    }
}
