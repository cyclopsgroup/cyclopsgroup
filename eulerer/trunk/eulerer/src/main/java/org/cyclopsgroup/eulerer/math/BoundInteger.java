package org.cyclopsgroup.eulerer.math;

import java.math.BigInteger;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * An integer bound within given length of digits
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
public class BoundInteger
{
    private final BigInteger bound;

    private final BigInteger value;

    private BoundInteger( BigInteger value, BigInteger bound )
    {
        this.value = value;
        this.bound = bound;
    }

    /**
     * @param v Long value
     * @param length Length of digits to bound to
     */
    public BoundInteger( long v, int length )
    {
        this.bound = new BigInteger( "1" + StringUtils.repeat( "0", length ) );
        this.value = BigInteger.valueOf( v ).mod( bound );
    }

    /**
     * @param v Value to add
     * @return Adding result
     */
    public BoundInteger add( BoundInteger v )
    {
        Validate.isTrue( bound.equals( v.bound ), "Unmatched bound" );
        return new BoundInteger( value.add( v.value ).mod( bound ), bound );
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean equals( Object obj )
    {
        return EqualsBuilder.reflectionEquals( this, obj );
    }

    /**
     * @inheritDoc
     */
    @Override
    public int hashCode()
    {
        return HashCodeBuilder.reflectionHashCode( this );
    }

    /**
     * TODO This implementation can be further improved
     *
     * @param v Value to multiply
     * @return Multiplying result
     */
    public BoundInteger multiply( BoundInteger v )
    {
        Validate.isTrue( bound.equals( v.bound ), "Unmatched bound" );
        return new BoundInteger( value.multiply( v.value ).mod( bound ), bound );
    }

    /**
     * @param power Number of power
     * @return Result of power
     */
    public BoundInteger power( int power )
    {
        BoundInteger v = new BoundInteger( BigInteger.ONE, bound );
        for ( int i = 0; i < power; i++ )
        {
            v = v.multiply( this );
        }
        return v;
    }

    /**
     * @return BigInteger form of value
     */
    public BigInteger toBigInteger()
    {
        return value;
    }

    /**
     * @return Long form of value
     */
    public long toLong()
    {
        return value.longValue();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString()
    {
        return value.toString();
    }
}
