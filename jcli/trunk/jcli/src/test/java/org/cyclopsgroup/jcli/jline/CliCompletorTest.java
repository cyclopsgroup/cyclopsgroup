package org.cyclopsgroup.jcli.jline;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.beans.IntrospectionException;
import java.util.ArrayList;
import java.util.List;

import org.cyclopsgroup.jcli.ExampleNormalBean;
import org.cyclopsgroup.jcli.QuotedStringTokenizer;
import org.junit.Test;

/**
 * Test case for {@link CliCompletor}
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class CliCompletorTest
{
    /**
     * @throws IntrospectionException
     */
    @Test
    public void testCompleteWithEmpty()
        throws IntrospectionException
    {
        CliCompletor c = new CliCompletor( new ExampleNormalBean(), new QuotedStringTokenizer() );
        List<String> l = new ArrayList<String>();
        assertEquals( -1, c.complete( "", 0, l ) );
        assertArrayEquals( new String[] { "-2", "-b", "-f", "-i", "11111", "22222", "33333" }, l.toArray() );
    }
}
