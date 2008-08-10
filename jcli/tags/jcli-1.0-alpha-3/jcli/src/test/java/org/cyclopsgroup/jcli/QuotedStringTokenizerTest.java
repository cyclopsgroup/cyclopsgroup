package org.cyclopsgroup.jcli;

import static org.junit.Assert.assertEquals;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

/**
 * Test case of {@link QuotedStringTokenizer}
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class QuotedStringTokenizerTest
{
    /**
     * Escape strings and verify result
     */
    @Test
    public void testEscape()
    {
        QuotedStringTokenizer t = new QuotedStringTokenizer();
        assertEquals( "abc", t.escape( "abc" ) );
        assertEquals( "\"ab c\"", t.escape( "ab c" ) );
        assertEquals( "\"ab\"\"c\"", t.escape( "ab\"c" ) );
        assertEquals( "\"a b\"\"c\"", t.escape( "a b\"c" ) );
    }

    /**
     * Parse string and verify results
     */
    @Test
    public void testParse()
    {
        QuotedStringTokenizer t = new QuotedStringTokenizer();
        assertEquals( "a,b,c d ,e,f,g\"h", StringUtils.join( t.parse( "a b \"c d \" e  f    g\"h " ), ',' ) );
        assertEquals( "123,4\",56", StringUtils.join( t.parse( "  123   4\" 56" ), ',' ) );
        assertEquals( "1,22\"2,34", StringUtils.join( t.parse( "1 \"22\"\"2\" \"34" ), ',' ) );
    }
}
