package org.cyclopsgroup.caff.token;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * Test case of {@link EscapedStringTokenizer}
 *
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class EscapedStringTokenizerTest
{

    private void parseAndVerify( String expression, List<String> expectedResult )
    {
        final List<String> result = new ArrayList<String>();
        new EscapingValueTokenizer().parse( expression, new TokenEventHandler()
        {
            public void handleEvent( TokenEvent event )
            {
                result.add( event.getToken() );
            }
        } );
        assertEquals( expectedResult, result );
    }

    @Test
    public void testParseWithoutEscaping()
    {
        parseAndVerify( "a b  c d   ", Arrays.asList( "a", "b", "c", "d" ) );
    }

    @Test
    public void testParseWithEscaping()
    {
        parseAndVerify( " a b\\ c \\\\e ", Arrays.asList( "a", "b c", "\\e" ) );
    }

    public void escapeAndVerify( String expression, String expected )
    {
        assertEquals( expected, new EscapingValueTokenizer().escape( expression ) );
    }

    @Test
    public void testEscapeUnnecessarily()
    {
        escapeAndVerify( "abc", "abc" );
    }

    @Test
    public void testEscapeNormally()
    {
        escapeAndVerify( "  a\\ b\\", "\\ \\ a\\\\\\ b\\\\" );
    }
}
