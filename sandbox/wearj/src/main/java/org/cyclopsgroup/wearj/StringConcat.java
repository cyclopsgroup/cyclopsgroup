package org.cyclopsgroup.wearj;

import org.cyclopsgroup.jemf.TestMethod;
import org.cyclopsgroup.jemf.TestObject;

public class StringConcat
    extends TestObject
{
    private final String name;

    public StringConcat( String name )
    {
        this.name = name;
    }

    @TestMethod
    public String operators()
    {
        return "Hello " + "number " + name;
    }

    @TestMethod
    public String stringBuilder()
    {
        return new StringBuilder( "Hello " ).append( "number " ).append( name ).toString();
    }

    @TestMethod
    public String multiLineOperator()
    {
        String s = "Hello ";
        s += "number ";
        s += name;
        return s;
    }

    @TestMethod
    public String concat()
    {
        return "Hello ".concat( "number " ).concat( name );
    }

    @TestMethod
    public String stringFormat()
    {
        return String.format( "Hello number %s", name );
    }
}
