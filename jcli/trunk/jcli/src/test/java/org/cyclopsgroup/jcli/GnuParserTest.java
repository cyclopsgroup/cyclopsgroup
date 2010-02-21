package org.cyclopsgroup.jcli;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;

import org.junit.Test;

/**
 * Verify the correctness of GNU argument parser
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
public class GnuParserTest
{
    /**
     * Verify use cases with all possible types of arguments
     */
    @Test
    public void testCombination()
    {
        ArgumentProcessor<ExampleNormalBean> p =
            ArgumentProcessor.newInstance( ExampleNormalBean.class, new GnuParser() );
        ExampleNormalBean b = new ExampleNormalBean();
        p.process( new String[] { "-i", "123", "a", "-f", "abc", "b" }, b );

        assertEquals( 123, b.getIntField() );
        assertEquals( "abc", b.getStringField1() );
        assertArrayEquals( new String[] { "a", "b" }, b.getValues().toArray() );
    }

    /**
     * Verify empty arguments doesn't reset default arguments
     */
    @Test
    public void testNormalBeanWithoutValues()
    {
        ArgumentProcessor<ExampleNormalBean> p =
            ArgumentProcessor.newInstance( ExampleNormalBean.class, new GnuParser() );
        ExampleNormalBean b = new ExampleNormalBean();
        p.process( new String[] { "-i", "123", "--field1", "abc" }, b );

        assertEquals( 123, b.getIntField() );
        assertEquals( "abc", b.getStringField1() );
        assertEquals( new ArrayList<String>(), b.getValues() );
    }

    /**
     * Verify simple argument can be handled correctly
     */
    @Test
    public void testSimpleArgumentWithMultiValues()
    {
        ArgumentProcessor<ExampleBeanWithSimpleArgument> p =
            ArgumentProcessor.newInstance( ExampleBeanWithSimpleArgument.class, new GnuParser() );
        ExampleBeanWithSimpleArgument b = new ExampleBeanWithSimpleArgument();
        p.process( new String[] { "a", "b" }, b );
        assertEquals( "a", b.getArg() );
    }

    /**
     * Verify simple argument without value can be handled correctly
     */
    @Test
    public void testSimpleArgumentWithoutValue()
    {
        ArgumentProcessor<ExampleBeanWithSimpleArgument> p =
            ArgumentProcessor.newInstance( ExampleBeanWithSimpleArgument.class, new GnuParser() );
        ExampleBeanWithSimpleArgument b = new ExampleBeanWithSimpleArgument();
        p.process( new String[] {}, b );
        assertNull( b.getArg() );
    }
}
