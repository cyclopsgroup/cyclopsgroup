package org.cyclopsgroup.waterview.impl.module;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang.ArrayUtils;
import org.cyclopsgroup.waterview.InputParameter;
import org.cyclopsgroup.waterview.spi.WebContext;

/**
 * Parameter value that fetch one primitive parameter based on {@link InputParameter} annotation
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
class ValueParameterBuilder
    extends ParameterBuilder
{
    private final InputParameter annotation;

    private final Class<?> type;

    /**
     * @param annotation InputParameter annotation that defined value mapping
     * @param type Type of parameter
     */
    ValueParameterBuilder( InputParameter annotation, Class<?> type )
    {
        this.annotation = annotation;
        this.type = type;
    }

    /**
     * @inheritDoc
     */
    @Override
    Object buildParameter( WebContext context )
    {
        switch ( annotation.type() )
        {
            case PARAMETER:
                String[] paramValues = context.getServletRequest().getParameterValues( annotation.name() );
                if ( ArrayUtils.isEmpty( paramValues ) )
                {
                    return null;
                }
                if ( type.isArray() )
                {
                    // TODO implement array conversion
                    throw new UnsupportedOperationException( "Array is not supported yet" );
                }
                // TODO consider more use cases
                return ConvertUtils.convert( paramValues[0], type );
            case VARIABLE:
                return context.getVariable( annotation.name() );
            case HEADER:
                String value = context.getServletRequest().getHeader( annotation.name() );
                return ConvertUtils.convert( value, type );
            default:
                throw new AssertionError( "Invalid type " + annotation.type() );
        }
    }
}
