package org.cyclopsgroup.jcli.spi;

import java.util.List;

public final class CommandLineBuilder
{
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

    public void withOptionForLongName( String name, String value )
    {
        cl.addOptionValue( name, value, false );
    }

    public void withOptionForShortName( String name, String value )
    {
        cl.addOptionValue( name, value, true );
    }
}
