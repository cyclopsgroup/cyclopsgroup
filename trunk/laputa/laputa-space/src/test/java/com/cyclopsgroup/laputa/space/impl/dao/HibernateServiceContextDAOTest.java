package com.cyclopsgroup.laputa.space.impl.dao;

import com.cyclopsgroup.laputa.space.impl.IntegrationTestCase;
import com.cyclopsgroup.laputa.space.pojo.SpaceServiceContext;

public class HibernateServiceContextDAOTest
    extends IntegrationTestCase
{
    private HibernateServiceContextDAO dao;

    @Override
    protected void setUp()
    {
        super.setUp();
        dao = new HibernateServiceContextDAO();
        dao.setSession( session );
    }

    public void testCreateForUserAccount()
    {
        SpaceServiceContext ctx = dao.createForUserAccount( 111, "abc" );
        session.flush();
        assertNotNull( ctx );
        ctx = dao.findByUserAccount( 111 );
        assertNotNull( ctx );
        assertEquals( "abc", ctx.getTitle() );
        System.out.println( ctx.getId() );
    }

    public void testFindByUserAccount()
    {
        assertNull( dao.findByUserAccount( 111 ) );
    }
}
