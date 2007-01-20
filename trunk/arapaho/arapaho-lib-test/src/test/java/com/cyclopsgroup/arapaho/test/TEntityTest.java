package com.cyclopsgroup.arapaho.test;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;

public class TEntityTest
    extends HsqlHibernateTestCase
{
    public void testPlayEntity()
        throws Exception
    {
        Session s = sessionFactory.openSession();
        TEntity e1 = new TEntity( 1L, "e1" );
        s.save( e1 );

        TEntity e = (TEntity) s.load( TEntity.class, 1L );
        assertEquals( "e1", e.getName() );

        s.delete( e );

        try
        {
            e = (TEntity) s.load( TEntity.class, 1L );
            fail( "TEntity is not supposed to be found after deletion" );
        }
        catch ( ObjectNotFoundException ex )
        {
            //do nothing
        }
    }
}