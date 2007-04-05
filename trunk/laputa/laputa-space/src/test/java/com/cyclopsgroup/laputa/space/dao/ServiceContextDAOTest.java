package com.cyclopsgroup.laputa.space.dao;

import com.cyclopsgroup.laputa.space.impl.IntegrationTestCase;

public class ServiceContextDAOTest
    extends IntegrationTestCase
{
    public void testConfig()
    {
        ServiceContextDAO dao = (ServiceContextDAO) springContext.getBean( ServiceContextDAO.class.getName() );
        assertEquals( "hello", dao.echo( "hello" ) );
    }
}
