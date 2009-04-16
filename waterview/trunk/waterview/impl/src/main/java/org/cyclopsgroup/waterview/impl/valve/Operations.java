package org.cyclopsgroup.waterview.impl.valve;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A POJO to store operations to proceed
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
class Operations
{
    /**
     * Variable name of operation
     */
    static final String NAME = Operations.class.getName();

    private final List<String> unmodifiedValues;

    private final List<String> values;

    /**
     * @param operations List of operation names
     */
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
