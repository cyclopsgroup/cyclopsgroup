package wearj;

import org.cyclopsgroup.jemf.TestMethod;
import org.cyclopsgroup.jemf.TestObject;

public class StringConcat
    extends TestObject
{
    private final int n;

    public StringConcat( int n )
    {
        this.n = n;
    }

    @TestMethod
    public String operators()
    {
        return "Hello " + "number " + n;
    }

    @TestMethod
    public String stringBuilder()
    {
        return new StringBuilder( "Hello " ).append( "number " ).append( n ).toString();
    }

    @TestMethod
    public String multiLineOperator()
    {
        String s = "Hello ";
        s += "number ";
        s += n;
        return s;
    }

    @TestMethod
    public String concat()
    {
        return "Hello ".concat( "number " ).concat( "" + n );
    }

    @TestMethod
    public String stringFormat()
    {
        return String.format( "Hello number %s", n );
    }
}
