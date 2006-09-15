package com.cyclopsgroup.waterview.utils;

import junit.framework.TestCase;

/**
 * Test case for TypeUtils
 *
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 */
public class TypeUtilsTest
    extends TestCase
{
    /**
     * Test getting convert utils
     */
    public void testGetConvertUtils()
    {
        assertNotNull( TypeUtils.getConvertUtils() );
    }

    public void testConvert()
    {
        assertEquals( 3, (int) TypeUtils.convert( "3", Integer.TYPE ) );
        assertEquals( 1.0, Double.class.cast( TypeUtils.convert( "1.0", "double" ) ) );
    }
}
