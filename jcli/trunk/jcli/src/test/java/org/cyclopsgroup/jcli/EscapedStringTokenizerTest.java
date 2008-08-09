package org.cyclopsgroup.jcli;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Test case of {@link EscapedStringTokenizer}
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class EscapedStringTokenizerTest
{
    /**
     * Verify strings are escaped
     */
    @Test
    public void testEscape()
    {
        EscapedStringTokenizer t = new EscapedStringTokenizer();
        assertEquals( "xyz", t.escape( "xyz" ) );
        assertEquals( "xy\\ z", t.escape( "xy z" ) );
    }

    /**
     * Test parsing arguments
     */
    @Test
    public void testParse()
    {
        EscapedStringTokenizer t = new EscapedStringTokenizer();
        assertArrayEquals( new String[] { "a", "b", "c", "d" }, t.parse( "a b  c d  " ).toArray() );
        assertArrayEquals( new String[] { "a", "b c", "\\e" }, t.parse( " a b\\ c \\\\e " ).toArray() );
    }
}
