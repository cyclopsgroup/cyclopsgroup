package org.cyclopsgroup.jcli.spi;

import java.util.Collection;

/**
 * A builder class to create {@link CommandLine}
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
public final class CommandLineBuilder
{
    private static final String FLAG_VALUE = Boolean.TRUE.toString();

    private final CommandLine cl = new CommandLine();

    /**
     * @return The command line instance with information that has been added so far
     */
    public CommandLine toCommandLine()
    {
        return cl;
    }

    /**
     * @param argument Add an argument expression
     */
    public void withArgument( String argument )
    {
        cl.addArgument( argument );
    }

    /**
     * @param arguments Add a list of arguments
     */
    public void withArguments( Collection<String> arguments )
    {
        for ( String arg : arguments )
        {
            withArgument( arg );
        }
    }

    /**
     * Add a flag option with its long name
     *
     * @param name Long name of the flag option to add
     */
    public void withLongFlag( String name )
    {
        withLongOption( name, FLAG_VALUE );
    }

    /**
     * Add an option with its long name
     *
     * @param name Long name of the option to add
     * @param value Value of the option to add
     */
    public void withLongOption( String name, String value )
    {
        cl.addOptionValue( name, value, false );
    }

    /**
     * Add a flag option with its short name
     *
     * @param name Short name of option to add
     */
    public void withShortFlag( String name )
    {
        withShortOption( name, FLAG_VALUE );
    }

    /**
     * Add an option with its short name
     *
     * @param name Short name of the option to add
     * @param value Value of option to add
     */
    public void withShortOption( String name, String value )
    {
        cl.addOptionValue( name, value, true );
    }
}
