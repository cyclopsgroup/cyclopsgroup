package com.cyclopsgroup.arapaho.spring;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.SessionFactoryUtils;

import com.cyclopsgroup.arapaho.hibernate.SessionAware;

public class LocalHibernateSessionInterceptor
    implements MethodInterceptor
{
    private boolean allowCreateSession;

    private SessionFactory sessionFactory;

    public LocalHibernateSessionInterceptor( SessionFactory sessionFactory )
    {
        this.sessionFactory = sessionFactory;
    }

    public Object invoke( MethodInvocation invocation )
        throws Throwable
    {
        SessionAware target = (SessionAware) invocation.getThis();
        Session session = SessionFactoryUtils.getSession( sessionFactory, allowCreateSession );
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

    public void setAllowCreateSession( boolean allowCreateSession )
    {
        this.allowCreateSession = allowCreateSession;
    }
}
