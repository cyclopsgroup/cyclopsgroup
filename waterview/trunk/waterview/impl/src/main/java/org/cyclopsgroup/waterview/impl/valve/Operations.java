package org.cyclopsgroup.waterview.impl.valve;

import java.util.ArrayList;
import java.util.List;

public class Operations
{
    public static final String NAME = "operations";

    private final List<String> values;

    public Operations( List<String> operations )
    {
        this.values = new ArrayList<String>( operations );
    }

    /**
     * @return A string value
     */
    public String take()
    {
        return values.remove( 0 );
    }
}
