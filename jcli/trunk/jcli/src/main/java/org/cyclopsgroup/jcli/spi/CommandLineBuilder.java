package org.cyclopsgroup.jcli.spi;

import java.util.List;

public final class CommandLineBuilder
{
    private static final String FLAG_VALUE = Boolean.TRUE.toString();

    private final CommandLine cl = new CommandLine();

    public CommandLine toCommandLine()
    {
        return cl;
    }

    public void withArgument( String argument )
    {
        cl.addArgument( argument );
    }

    public void withArguments( List<String> arguments )
    {
        for ( String arg : arguments )
        {
            withArgument( arg );
        }
    }

    public void withLongFlag( String name )
    {
        withLongOption( name, FLAG_VALUE );
    }

    public void withLongOption( String name, String value )
    {
        cl.addOptionValue( name, value, false );
    }

    public void withShortFlag( String name )
    {
        withShortOption( name, FLAG_VALUE );
    }

    public void withShortOption( String name, String value )
    {
        cl.addOptionValue( name, value, true );
    }
}
