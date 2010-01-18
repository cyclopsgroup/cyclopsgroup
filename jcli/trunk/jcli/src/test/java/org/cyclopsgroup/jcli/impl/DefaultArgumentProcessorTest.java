package org.cyclopsgroup.jcli.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.cyclopsgroup.jcli.ArgumentProcessor;
import org.cyclopsgroup.jcli.ExampleNormalBean;
import org.junit.Test;

public class DefaultArgumentProcessorTest
{
    @Test
    public void testAvailability()
    {
        ArgumentProcessor<ExampleNormalBean> p = ArgumentProcessor.newInstance( ExampleNormalBean.class, null );
        assertNotNull( p );
        assertTrue( p.getClass() == DefaultArgumentProcessor.class );
    }
}
