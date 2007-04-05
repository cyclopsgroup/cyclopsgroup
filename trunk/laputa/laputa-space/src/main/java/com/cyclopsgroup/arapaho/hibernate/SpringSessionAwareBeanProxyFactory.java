package com.cyclopsgroup.arapaho.hibernate;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collection;

import org.apache.commons.beanutils.MethodUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.orm.hibernate3.SessionFactoryUtils;

public class SpringSessionAwareBeanProxyFactory
    implements FactoryBean
{
    private SessionFactory sessionFactory;

    private SessionAware invocationTarget;

    private Class[] interfaces;

    private boolean singleton = true;

    private InvocationHandler handler = new InvocationHandler()
    {

        public Object invoke( Object proxy, Method method, Object[] args )
            throws Throwable
        {
            synchronized ( invocationTarget )
            {
                if ( method.getName().equals( "setSession" ) && method.getParameterTypes().length == 1
                    && method.getParameterTypes()[0] == Session.class )
                {
                    throw new IllegalAccessError( "setSession method is not supposed to be called here" );
                }
                if ( method.getName().equals( "getSession" ) && method.getParameterTypes().length == 0 )
                {
                    return invocationTarget.getSession();
                }
                //Transaction needs to be started explictly, so allowCreation is false
                Session session = SessionFactoryUtils.getSession( sessionFactory, false );
                invocationTarget.setSession( session );
                try
                {
                    MethodUtils
                        .invokeExactMethod( invocationTarget, method.getName(), args, method.getParameterTypes() );
                }
                finally
                {
                    invocationTarget.setSession( null );
                }
                return null;
            }
        }

    };

    public SpringSessionAwareBeanProxyFactory( SessionFactory sessionFactory, SessionAware invocationTarget )
    {
        this.sessionFactory = sessionFactory;
        this.invocationTarget = invocationTarget;
    }

    public Object getObject()
        throws Exception
    {
        return Proxy.newProxyInstance( Thread.currentThread().getContextClassLoader(), interfaces, handler );
    }

    public Class getObjectType()
    {
        return Object.class;
    }

    public boolean isSingleton()
    {
        return singleton;
    }

    public void setInterface( Class interfaceClass )
    {
        setInterfaces( new Class[] { interfaceClass } );
    }

    public void setInterface( String interfaceName )
        throws ClassNotFoundException
    {
        setInterface( Class.forName( interfaceName ) );
    }

    public void setInterfaces( Class[] interfaces )
    {
        this.interfaces = interfaces;
    }

    public void setInterfaces( Collection<String> interfaceNames )
        throws ClassNotFoundException
    {
        Class[] interfaceArray = new Class[interfaceNames.size()];
        int i = 0;
        for ( String interfaceName : interfaceNames )
        {
            interfaceArray[i++] = Class.forName( interfaceName );
        }
        setInterfaces( interfaceArray );
    }

    public void setSingleton( boolean singleton )
    {
        this.singleton = singleton;
    }
}
