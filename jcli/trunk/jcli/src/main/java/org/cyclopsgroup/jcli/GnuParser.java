package org.cyclopsgroup.jcli;

import org.cyclopsgroup.jcli.spi.CommandLine;
import org.cyclopsgroup.jcli.spi.CommandLineBuilder;
import org.cyclopsgroup.jcli.spi.CommandLineParser;
import org.cyclopsgroup.jcli.spi.OptionDefinition;
import org.cyclopsgroup.jcli.spi.ParsingContext;

/**
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
public class GnuParser
    implements CommandLineParser
{
    /**
     * @inheritDoc
     */
    @Override
    public CommandLine parse( String[] arguments, ParsingContext context )
    {
        CommandLineBuilder builder = new CommandLineBuilder();
        boolean expectingOptionValue = false;
        String optionName = null;
        boolean shortOption = false;
        for ( String arg : arguments )
        {
            if ( expectingOptionValue )
            {
                if ( shortOption )
                {
                    builder.withShortOption( optionName, arg );
                }
                else
                {
                    builder.withLongOption( optionName, arg );
                }
                expectingOptionValue = false;
            }
            else if ( !arg.startsWith( "-" ) || arg.startsWith( "---" ) || arg.equals( "-" ) || arg.equals( "--" ) )
            {
                builder.withArgument( arg );
            }
            else if ( arg.startsWith( "--" ) )
            {
                optionName = arg.substring( 2 );
                OptionDefinition opt = context.optionWithLongName( optionName );
                if ( opt == null )
                {
                    builder.withUnexpectedLongOption( optionName );
                }
                else if ( opt.isFlag() )
                {
                    builder.withLongFlag( optionName );
                }
                else
                {
                    expectingOptionValue = true;
                    shortOption = false;
                }
            }
            else if ( arg.startsWith( "-" ) )
            {
                optionName = arg.substring( 1 );
                OptionDefinition opt = context.optionWithLongName( optionName );
                if ( opt == null )
                {
                    builder.withUnexpectedShortOption( optionName );
                }
                else if ( opt.isFlag() )
                {
                    builder.withShortFlag( optionName );
                }
                else
                {
                    expectingOptionValue = true;
                    shortOption = true;
                }
            }
            else
            {
                throw new AssertionError( "Unexpected argument " + arg );
            }
        }
        return builder.toCommandLine();
    }
}
