package org.cyclopsgroup.caff.token;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class QuotedValueTokenizerTest
{
    private void escapeAndVerify( String expression, String expected )
    {
        assertEquals( expected, new QuotedValueTokenizer().escape( expression ) );
    }

    private void parseAndVerify( String expression, List<String> expectedResult )
    {
        final List<String> result = new ArrayList<String>();
        new QuotedValueTokenizer().parse( expression, new TokenEventHandler()
        {
            public void handleEvent( TokenEvent event )
            {
                result.add( event.getToken() );
            }
        } );
        assertEquals( expectedResult, result );
    }

    @Test
    public void testEscapeUnnecessarily()
    {
        escapeAndVerify( "abc", "abc" );
    }

    @Test
    public void testEscapeWithDelimiterOnly()
    {
        escapeAndVerify( "ab c", "\"ab c\"" );
    }

    @Test
    public void testEscapeWithQuotation()
    {
        escapeAndVerify( "ab \"c\"", "\"ab \"\"c\"\"\"" );
    }

    /**
     * Verify parsing works without quoting
     */
    @Test
    public void testParseWithoutQuoting()
    {
        parseAndVerify( "a b  c d   ", Arrays.asList( "a", "b", "c", "d" ) );
    }

    /**
     * Verify parsing works with quoting
     */
    @Test
    public void testParsingWithQuoting()
    {
        parseAndVerify( "a \"b\" \"c\"\"\"  \"d\"\"e\"  ", Arrays.asList( "a", "b", "c\"", "d\"e" ) );
    }
}
