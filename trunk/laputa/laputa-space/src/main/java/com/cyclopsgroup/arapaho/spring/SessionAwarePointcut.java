package com.cyclopsgroup.arapaho.spring;

import java.lang.reflect.Method;

import org.hibernate.Session;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;

import com.cyclopsgroup.arapaho.hibernate.SessionAware;

public class SessionAwarePointcut
    implements Pointcut
{
    private ClassFilter classFilter = new ClassFilter()
    {
        @SuppressWarnings("unchecked")
        public boolean matches( Class clazz )
        {
            return SessionAware.class.isAssignableFrom( clazz );
        }
    };

    private MethodMatcher methodMatcher = new MethodMatcher()
    {
        public boolean isRuntime()
        {
            return true;
        }

        @SuppressWarnings("unchecked")
        public boolean matches( Method method, Class targetClass )
        {
            if ( method.getName().equals( "getSession" ) && method.getParameterTypes().length == 0 )
            {
                return false;
            }
            if ( method.getName().equals( "setSession" ) && method.getParameterTypes().length == 1
                && method.getParameterTypes()[0] == Session.class )
            {
                return false;
            }
            return true;
        }

        @SuppressWarnings("unchecked")
        public boolean matches( Method method, Class targetClass, Object[] args )
        {
            return matches( method, targetClass );
        }

    };

    public ClassFilter getClassFilter()
    {
        return classFilter;
    }

    public MethodMatcher getMethodMatcher()
    {
        return methodMatcher;
    }
}
