package wearj;

import java.math.BigDecimal;

import org.cyclopsgroup.jemf.TestMethod;
import org.cyclopsgroup.jemf.TestObject;

public class DollarToCents
    extends TestObject
{
    private final String stringValue;

    private final double doubleValue;

    public DollarToCents( String stringValue, double doubleValue )
    {
        this.stringValue = stringValue;
        this.doubleValue = doubleValue;
    }

    @TestMethod
    public int directMultiply()
    {
        return (int) ( doubleValue * 100 );
    }

    @TestMethod
    public int doubleToBigDecimal()
    {
        return new BigDecimal( doubleValue ).movePointRight( 2 ).intValue();
    }

    @TestMethod
    public int stringToBigDecimal()
    {
        return new BigDecimal( stringValue ).movePointRight( 2 ).intValue();
    }

}
