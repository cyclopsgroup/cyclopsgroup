package org.cyclopsgroup.jcli;

import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.cyclopsgroup.jcli.annotation.Argument;
import org.cyclopsgroup.jcli.annotation.Cli;
import org.cyclopsgroup.jcli.annotation.Option;

/**
 * Example bean for testing purpose
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
@Cli( name = "sample", description = "A test" )
public class ExampleNormalBean
{
    private boolean booleanField;

    private int intField;

    private String stringField1;

    private String stringFIeld2;

    private List<String> values;

    /**
     * @return An integer option
     */
    public final int getIntField()
    {
        return intField;
    }

    /**
     * @return One string option
     */
    public final String getStringField1()
    {
        return stringField1;
    }

    /**
     * @return Another string option
     */
    public final String getStringFIeld2()
    {
        return stringFIeld2;
    }

    /**
     * @return Arguments
     */
    public final List<String> getValues()
    {
        return values;
    }

    /**
     * @return Boolean flag
     */
    public final boolean isBooleanField()
    {
        return booleanField;
    }

    /**
     * @param booleanField A flag option
     */
    @Option( name = "b", longName = "boolean", description = "Test boolean field" )
    public final void setBooleanField( boolean booleanField )
    {
        this.booleanField = booleanField;
    }

    /**
     * @param intField An integer option
     */
    @Option( name = "i", longName = "tint", description = "Test int value" )
    public final void setIntField( int intField )
    {
        this.intField = intField;
    }

    /**
     * @param stringField1 One string option
     */
    @Option( name = "f", longName = "field1", required = true )
    public final void setStringField1( String stringField1 )
    {
        this.stringField1 = stringField1;
    }

    /**
     * @param stringFIeld2 Another string option
     */
    @Option( name = "2", longName = "field2" )
    public final void setStringFIeld2( String stringFIeld2 )
    {
        this.stringFIeld2 = stringFIeld2;
    }

    /**
     * @param values Arguments
     */
    @Argument
    public final void setValues( List<String> values )
    {
        this.values = values;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString()
    {
        return ReflectionToStringBuilder.toString( this );
    }
}
