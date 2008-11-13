package com.cyclopsgroup.waterview.impl.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;

import com.cyclopsgroup.waterview.ServiceManager;
import com.cyclopsgroup.waterview.ServiceNotFoundException;

public class SpringServiceManager
    extends ServiceManager
    implements BeanFactoryAware
{
    private BeanFactory beanFactory;

    @Override
    public Object getService( String serviceRole )
        throws ServiceNotFoundException
    {
        try
        {
            return beanFactory.getBean( serviceRole );
        }
        catch ( NoSuchBeanDefinitionException e )
        {
            throw new ServiceNotFoundException( serviceRole );
        }
    }

    public void setBeanFactory( BeanFactory beanFactory )
        throws BeansException
    {
        this.beanFactory = beanFactory;
    }
}
