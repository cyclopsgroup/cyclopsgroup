package com.cyclopsgroup.arapaho.hibernate;

import org.hibernate.Session;

public interface SessionAware
{
    Session getSession();

    void setSession( Session session );
}
