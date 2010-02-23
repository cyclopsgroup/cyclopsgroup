package org.cyclopsgroup.caff.token;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class QuotedValueTokenizerTest
{
    private void parseAndVerifyResult( String expression, List<String> expectedResult )
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

    /**
     * Verify parsing works without quoting
     */
    @Test
    public void testParseWithoutQuoting()
    {
        parseAndVerifyResult( "a b  c d   ", Arrays.asList( "a", "b", "c", "d" ) );
    }

    /**
     * Verify parsing works with quoting
     */
    @Test
    public void testParsingWithQuoting()
    {
        parseAndVerifyResult( "a \"b\" \"c\"\"\"  \"d\"\"e\"  ", Arrays.asList( "a", "b", "c\"", "d\"e" ) );
    }
}
