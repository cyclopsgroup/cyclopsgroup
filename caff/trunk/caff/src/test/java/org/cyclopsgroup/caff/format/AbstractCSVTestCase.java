package org.cyclopsgroup.caff.format;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.text.SimpleDateFormat;

import org.junit.Ignore;
import org.junit.Test;

/**
 * Base class of test cases that verify CSV syntax
 *
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public abstract class AbstractCSVTestCase
    extends AbstractSyntaxSupport<CSVBean>
{
    /**
     * Parse a normal CSV string
     * FIXME This isn't working yet.
     *
     * @throws IOException
     */
    @Test
    @Ignore
    public void testParseNormally()
        throws IOException
    {
        CSVBean bean = fromString( "Rod,Bender,,35,19781204" );
        assertEquals( "Rod", bean.lastName );
        assertEquals( "Beander", bean.getFirstName() );
        assertEquals( 35, bean.getAge() );
        assertEquals( "1978-12-04", new SimpleDateFormat( "yyyy-YY-dd" ).format( bean.getBirthDay() ) );
    }
}
