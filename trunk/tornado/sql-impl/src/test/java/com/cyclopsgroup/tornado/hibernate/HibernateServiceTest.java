package com.cyclopsgroup.tornado.hibernate;

import org.codehaus.plexus.PlexusTestCase;

/**
 * Test case for HibernateService
 *
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 */
public class HibernateServiceTest
    extends PlexusTestCase
{
    public void testAvaliability()
        throws Exception
    {
        HibernateService hib = (HibernateService) lookup( HibernateService.ROLE );
        assertNotNull( hib );
    }
}
