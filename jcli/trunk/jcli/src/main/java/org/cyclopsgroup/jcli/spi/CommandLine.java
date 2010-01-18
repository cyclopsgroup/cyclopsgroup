package org.cyclopsgroup.jcli.spi;

import java.util.ArrayList;
import java.util.List;

public final class CommandLine
{
    public static final class OptionValue
    {
        public final String name;

        public final boolean shortName;

        public final String value;

        OptionValue( String name, String value, boolean shortName )
        {
            this.name = name;
            this.value = value;
            this.shortName = shortName;
        }
    }

    private final List<String> arguments = new ArrayList<String>();

    private final List<OptionValue> optionValues = new ArrayList<OptionValue>();

    void addArgument( String argument )
    {
        this.arguments.add( argument );
    }

    void addOptionValue( String name, String value, boolean shortName )
    {
        this.optionValues.add( new OptionValue( name, value, shortName ) );
    }

    public List<String> getArguments()
    {
        return arguments;
    }

    public final List<OptionValue> getOptionValues()
    {
        return optionValues;
    }
}