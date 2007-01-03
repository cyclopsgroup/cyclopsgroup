package com.cyclopsgroup.arapaho.hibernate;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class SimpleHibernateService
    implements HibernateService
{
    private Random idGenerator = new Random( System.currentTimeMillis() );

    private Map<String, HibernateProvider> instances = new ConcurrentHashMap<String, HibernateProvider>();

    private Map<String, SessionFactory> sessionFactories = new ConcurrentHashMap<String, SessionFactory>();

    private Map<Long, Session> sessions = new ConcurrentHashMap<Long, Session>();

    public SimpleHibernateService( Map<String, HibernateProvider> instances )
    {
        this.instances.putAll( instances );
        for ( String name : instances.keySet() )
        {
            HibernateProvider inst = instances.get( name );
            SessionFactory sf = inst.createSessionFactory();
            sessionFactories.put( name, sf );
        }
    }

    public void closeSession( long sessionId )
    {
        Session session = getSession( sessionId );
        session.flush();
        session.close();
        sessions.remove( sessionId );
    }

    public HibernateProvider getHibernateProvider( String hibernateName )
    {
        return instances.get( hibernateName );
    }

    public Session getSession( long sessionId )
    {
        return sessions.get( sessionId );
    }

    public SessionFactory getSessionFactory()
    {
        return getSessionFactory( DEFAULT_HIBERNATE );
    }

    public SessionFactory getSessionFactory( String hibernateName )
    {
        return sessionFactories.get( hibernateName );
    }

    public long openSession( String hibernateName )
    {
        long sessionId = idGenerator.nextLong();
        SessionFactory sf = getSessionFactory( hibernateName );
        Session session = sf.openSession();
        sessions.put( sessionId, session );
        return sessionId;
    }
}