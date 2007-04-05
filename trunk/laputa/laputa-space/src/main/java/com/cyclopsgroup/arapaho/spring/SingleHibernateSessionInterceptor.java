package com.cyclopsgroup.arapaho.spring;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.hibernate.Session;

import com.cyclopsgroup.arapaho.hibernate.SessionAware;

public class SingleHibernateSessionInterceptor
    implements MethodInterceptor
{
    private Session session;

    public SingleHibernateSessionInterceptor( Session session )
    {
        this.session = session;
    }

    public Object invoke( MethodInvocation invocation )
        throws Throwable
    {
        SessionAware target = (SessionAware) invocation.getThis();
        synchronized ( target )
        {
            target.setSession( session );
            try
            {
                return invocation.proceed();
            }
            finally
            {
                target.setSession( null );
            }
        }
    }
}
