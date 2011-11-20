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
     * One constant
     */
    public static RationalNumber ONE = new RationalNumber( 1, 1 );

    /**
     * ZERO constant
     */
    public static RationalNumber ZERO = new RationalNumber( 0, 1 );

    /**
     * The normalized denominator, always positive
     */
    public final BigInteger denominator;

    /**
     * The normalized numerator
     */
    public final BigInteger numerator;

    /**
     * @param numerator The numerator
     * @param denominator The denominator
     */
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

    /**
     * Constructor that sets denominator to one
     *
     * @param numerator Numerator
     */
    public RationalNumber( long numerator )
    {
        this( numerator, 1 );
    }

    /**
     * @param numerator Numerator value
     * @param denominator Denominator value
     */
    public RationalNumber( long numerator, long denominator )
    {
        this( BigInteger.valueOf( numerator ), BigInteger.valueOf( denominator ) );
    }

    /**
     * Add operation
     *
     * @param b Number to add
     * @return Adding result
     */
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

    /**
     * @param b The divisor
     * @return Result
     */
    public RationalNumber divide( BigInteger b )
    {
        return new RationalNumber( numerator, denominator.multiply( b ) );
    }

    /**
     * Divide operation
     *
     * @param b Number divided by
     * @return Dividing result
     */
    public RationalNumber divide( RationalNumber b )
    {
        RationalNumber a = this;
        return a.multiply( b.reciprocal() );
    }

    /**
     * @inheritDoc
     */
    @Override
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

    /**
     * @param b Number to subtract
     * @return Result after subtraction
     */
    public RationalNumber minus( RationalNumber b )
    {
        RationalNumber a = this;
        return a.add( b.negate() );
    }

    /**
     * @param b Number to multiply by
     * @return Result
     */
    public RationalNumber multiply( BigInteger b )
    {
        return new RationalNumber( numerator.multiply( b ), denominator );
    }

    /**
     * @param b Number to multiply
     * @return Multiplication result
     */
    public RationalNumber multiply( RationalNumber b )
    {
        return new RationalNumber( numerator.multiply( b.numerator ), denominator.multiply( b.denominator ) );
    }

    /**
     * @return Negative result
     */
    public RationalNumber negate()
    {
        return new RationalNumber( numerator.negate(), denominator );
    }

    /**
     * @return Reciprocal result
     */
    public RationalNumber reciprocal()
    {
        return new RationalNumber( denominator, numerator );
    }

    /**
     * @return Approximate double form of number
     */
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
