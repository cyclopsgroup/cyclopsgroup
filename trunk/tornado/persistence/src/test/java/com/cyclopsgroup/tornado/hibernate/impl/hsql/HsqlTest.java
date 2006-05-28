package com.cyclopsgroup.tornado.hibernate.impl.hsql;

import junit.framework.TestCase;

import org.hsqldb.Server;

/**
 * Test the availability of hsql
 *
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 */
public class HsqlTest
    extends TestCase
{
    public void testDummy()
        throws Exception
    {
        Server.main( new String[] {} );
    }
}
