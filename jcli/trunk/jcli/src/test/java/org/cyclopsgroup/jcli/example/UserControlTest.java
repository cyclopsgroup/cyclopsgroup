package org.cyclopsgroup.jcli.example;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.cyclopsgroup.jcli.ArgumentProcessor;
import org.cyclopsgroup.jcli.GnuParser;
import org.junit.Test;

public class UserControlTest
{
    @Test
    public void testWithSingleUser()
    {
        ArgumentProcessor<UserControl> p = ArgumentProcessor.newInstance( UserControl.class, new GnuParser() );
        UserControl c = new UserControl();
        p.process( Arrays.asList( "-a", "ADD", "john" ), c );
        assertEquals( UserControlAction.ADD, c.getAction() );
        assertEquals( Arrays.asList( "john" ), c.getUserNames() );
    }
}
