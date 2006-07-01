package com.cyclopsgroup.tornado.hibernate;

import org.hibernate.Session;

import com.cyclopsgroup.waterview.test.WaterviewTestCase;

/**
 * Test case for HibernateService
 *
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 */
public class HibernateServiceTest
    extends WaterviewTestCase
{
    public void testAvaliability()
        throws Exception
    {
        HibernateManager hib = (HibernateManager) lookup( HibernateManager.ROLE );
        assertNotNull( hib );

        HibernateService hs = hib.getDefaultHibernateService();
        assertNotNull( hs );

        Session s = hs.getSession();
        assertNotNull( s );

        hs.closeSession();
        assertFalse( s.isOpen() );
    }
}
