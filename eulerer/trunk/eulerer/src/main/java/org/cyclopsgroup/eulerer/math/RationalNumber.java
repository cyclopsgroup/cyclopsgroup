package org.cyclopsgroup.eulerer.math;

import java.math.BigInteger;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
public class RationalNumber
{
    /**
     * ZERO constant
     */
    public static RationalNumber ZERO = new RationalNumber( 0, 1 );

    /**
     * One constant
     */
    public static RationalNumber ONE = new RationalNumber( 1, 1 );

    /**
     * The normalized denominator, always positive
     */
    public final BigInteger denominator;

    /**
     * The normalized numerator
     */
    public final BigInteger numerator;

    /**
     * @param numerator Numerator value
     * @param denominator Denominator value
     */
    public RationalNumber( long numerator, long denominator )
    {
        this( BigInteger.valueOf( numerator ), BigInteger.valueOf( denominator ) );
    }

    public RationalNumber( BigInteger numerator, BigInteger denominator )
    {
        Validate.isTrue( !denominator.equals( BigInteger.ZERO ), "Zero denominator" );
        // Denominator is always greater than zero
        if ( denominator.compareTo( BigInteger.ZERO ) < 0 )
        {
            denominator = denominator.negate();
            numerator = numerator.negate();
        }
        BigInteger g = numerator.gcd( denominator );

        this.numerator = numerator.divide( g );
        this.denominator = denominator.divide( g );
    }

    public RationalNumber( long numerator )
    {
        this( numerator, 1 );
    }

    public RationalNumber divide( RationalNumber b )
    {
        RationalNumber a = this;
        return a.multiply( b.reciprocal() );
    }

    public boolean equals( Object y )
    {
        if ( !( y instanceof RationalNumber ) )
        {
            return false;
        }
        RationalNumber other = (RationalNumber) y;
        if ( numerator.equals( BigInteger.ZERO ) && other.numerator.equals( BigInteger.ZERO ) )
        {
            return true;
        }
        return new EqualsBuilder().append( numerator, other.numerator ).append( denominator, other.denominator ).isEquals();
    }

    /**
     * @inheritDoc
     */
    @Override
    public int hashCode()
    {
        if ( numerator.equals( BigInteger.ZERO ) )
        {
            return 0;
        }
        return new HashCodeBuilder().append( numerator ).append( denominator ).toHashCode();
    }

    public RationalNumber minus( RationalNumber b )
    {
        RationalNumber a = this;
        return a.add( b.negate() );
    }

    public RationalNumber multiply( RationalNumber b )
    {
        return new RationalNumber( numerator.multiply( b.numerator ), denominator.multiply( b.denominator ) );
    }

    public RationalNumber negate()
    {
        return new RationalNumber( numerator.negate(), denominator );
    }

    public RationalNumber add( RationalNumber b )
    {
        RationalNumber a = this;
        if ( a.numerator.equals( BigInteger.ZERO ) )
        {
            return b;
        }
        if ( b.numerator.equals( BigInteger.ZERO ) )
        {
            return a;
        }
        return new RationalNumber( a.numerator.multiply( b.denominator ).add( b.numerator.multiply( a.denominator ) ),
                                   a.denominator.multiply( b.denominator ) );
    }

    public RationalNumber reciprocal()
    {
        return new RationalNumber( denominator, numerator );
    }

    public double toDouble()
    {
        return numerator.doubleValue() / denominator.doubleValue();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString()
    {
        return numerator + "/" + denominator;
    }
}
