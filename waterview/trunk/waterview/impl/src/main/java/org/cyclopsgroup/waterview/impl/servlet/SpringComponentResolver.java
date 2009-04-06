package org.cyclopsgroup.waterview.impl.servlet;

import org.apache.commons.lang.Validate;
import org.cyclopsgroup.waterview.spi.ComponentResolver;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;

public class SpringComponentResolver
    implements ComponentResolver
{
    /**
     * @inheritDoc
     */
    @SuppressWarnings( "unchecked" )
    @Override
    public <T> T findComponent( Class<T> type, String name )
    {
        try
        {
            return (T) beanFactory.getBean( name, type );
        }
        catch ( NoSuchBeanDefinitionException e )
        {
            return null;
        }
    }

    private final BeanFactory beanFactory;

    public SpringComponentResolver( BeanFactory beanFactory )
    {
        Validate.notNull( beanFactory, "BeanFactory can't be NULL" );
        this.beanFactory = beanFactory;
    }

}
