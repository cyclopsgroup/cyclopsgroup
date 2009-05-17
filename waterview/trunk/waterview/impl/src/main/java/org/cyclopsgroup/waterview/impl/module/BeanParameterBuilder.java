package org.cyclopsgroup.waterview.impl.module;

import org.cyclopsgroup.waterview.InputBean;
import org.cyclopsgroup.waterview.spi.WebContext;

/**
 * Parameter builder that creates Java bean as parameter
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
class BeanParameterBuilder
    extends ParameterBuilder
{
    private final InputBean annotation;

    private final Class<?> type;

    /**
     * @param annotation Annotation associated with parameter
     * @param type Type of Java bean
     */
    BeanParameterBuilder( InputBean annotation, Class<?> type )
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
        Object bean;
        try
        {
            bean = type.newInstance();
            //TODO pass values properly
            return bean;
        }
        catch ( InstantiationException e )
        {
            throw new RuntimeException("Can't create bean " + type, e);
        }
        catch ( IllegalAccessException e )
        {
            throw new RuntimeException("Can't create bean " + type, e);
        }
    }
}
