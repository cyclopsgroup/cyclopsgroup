package org.cyclopsgroup.jcli.example;

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
        p.process( new String[] { "-a", "ADD", "john" }, c );
        System.out.println( c );
    }
}
