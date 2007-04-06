package com.cyclopsgroup.arapaho.hibernate;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import org.hibernate.Session;

public abstract class BaseHibernateGeneticDAO<T, K extends Serializable>
    implements SessionAware
{
    private Class<T> entityClass;

    private Class<K> primaryKeyClass;

    private Session session;

    protected BaseHibernateGeneticDAO()
    {
        entityClass = (Class<T>) ( (ParameterizedType) getClass().getGenericSuperclass() ).getActualTypeArguments()[0];

        primaryKeyClass = (Class<K>) ( (ParameterizedType) getClass().getGenericSuperclass() ).getActualTypeArguments()[1];
    }

    public void delete( T entity )
    {
        getSession().delete( entity );
    }

    public String echo( String msg )
    {
        return msg;
    }

    public Class<T> getEntityClass()
    {
        return entityClass;
    }

    public Class<K> getPrimaryKeyClass()
    {
        return primaryKeyClass;
    }

    public Session getSession()
    {
        if ( session == null )
        {
            throw new IllegalStateException( "Session isn't set yet" );
        }
        return session;
    }

    public void save( T entity )
    {
        getSession().save( entity );
    }

    public void setSession( Session session )
    {
        this.session = session;
    }
}
