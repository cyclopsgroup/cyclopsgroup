package org.cyclopsgroup.waterview.impl.valve;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Operations
{
    public static final String NAME = "operations";

    private final List<String> unmodifiedValues;

    private final List<String> values;

    public Operations( List<String> operations )
    {
        this.values = new ArrayList<String>( operations );
        this.unmodifiedValues = Collections.unmodifiableList( values );
    }

    /**
     * @return A string value
     */
    public String take()
    {
        return values.remove( 0 );
    }

    /**
     * Take away specified value
     * 
     * @param value Value to take away
     * @return True if value exists
     */
    public boolean take( String value )
    {
        return values.remove( value );
    }

    /**
     * @return List of values
     */
    public List<String> values()
    {
        return unmodifiedValues;
    }
}
