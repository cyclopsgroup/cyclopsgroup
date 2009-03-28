/*
 * Created on 2003-9-24
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.cyclops.a4trim.utils;
import junit.framework.TestCase;
/**
 * @author joeblack
 * @since 2003-9-24 12:06:14
 *
 * Class NumberUtilsTest
 */
public class NumberUtilsTest extends TestCase {
    /** Method testToHex() in Class NumberUtilsTest */
    public void testToHex() {
        assertEquals("0000", NumberUtils.toHex(0, 4));
        assertEquals("00FE", NumberUtils.toHex(254, 4));
        assertEquals("E", NumberUtils.toHex(254, 1));
    }
}
