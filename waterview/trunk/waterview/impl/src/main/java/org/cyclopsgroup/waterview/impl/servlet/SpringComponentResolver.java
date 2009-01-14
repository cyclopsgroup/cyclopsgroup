package org.cyclopsgroup.waterview.impl.servlet;

import org.apache.commons.lang.Validate;
import org.cyclopsgroup.waterview.spi.ComponentResolver;
import org.springframework.beans.factory.BeanFactory;

public class SpringComponentResolver
    implements ComponentResolver
{
    @SuppressWarnings( "unchecked" )
    @Override
    public <T> T findComponent( Class<T> type, String name )
    {
        return (T) beanFactory.getBean( name, type );
    }

    private final BeanFactory beanFactory;

    public SpringComponentResolver( BeanFactory beanFactory )
    {
        Validate.notNull( beanFactory, "BeanFactory can't be NULL" );
        this.beanFactory = beanFactory;
    }

}
