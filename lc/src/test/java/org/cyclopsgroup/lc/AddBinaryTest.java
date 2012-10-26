package org.cyclopsgroup.lc;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AddBinaryTest
{
    @Test
    public void testGiven()
    {
        assertEquals( "100", new AddBinary().addBinary( "11", "1" ) );
    }
}
