package com.cyclopsgroup.tornado.hibernate;

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
        HibernateService hib = (HibernateService) lookup( HibernateService.ROLE );
        assertNotNull( hib );

        hib.getSessionFactory( HibernateService.DEFAULT_DATASOURCE );
    }
}
