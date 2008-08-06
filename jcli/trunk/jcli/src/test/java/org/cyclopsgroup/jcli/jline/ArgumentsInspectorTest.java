package org.cyclopsgroup.jcli.jline;

import static org.junit.Assert.assertEquals;

import java.beans.IntrospectionException;

import org.cyclopsgroup.jcli.ExampleNormalBean;
import org.cyclopsgroup.jcli.spi.CliDefinition;
import org.cyclopsgroup.jcli.spi.CliUtils;
import org.junit.Before;
import org.junit.Test;

/**
 * Test case of {@link ArgumentsInspector}
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class ArgumentsInspectorTest
{
    private ArgumentsInspector ins;

    /**
     * Setup object to test
     * 
     * @throws IntrospectionException
     */
    @Before
    public void setUp()
        throws IntrospectionException
    {
        CliDefinition cli = CliUtils.defineCli( ExampleNormalBean.class );
        ins = new ArgumentsInspector( cli );
    }

    /**
     * Test state change of a sevies of argument consumption
     */
    @Test
    public void testConsume()
    {
        assertEquals( ArgumentsInspectorState.READY, ins.getState() );
        ins.consume( "xyz" );
        assertEquals( ArgumentsInspectorState.ARGUMENT, ins.getState() );
        assertEquals( "xyz", ins.getCurrentValue() );
        ins.consume( "-i" );
        assertEquals( ArgumentsInspectorState.OPTION, ins.getState() );
        ins.consume( "1234" );
        assertEquals( ArgumentsInspectorState.OPTION_VALUE, ins.getState() );
        assertEquals( "i", ins.getCurrentOption().getName() );
    }
}
