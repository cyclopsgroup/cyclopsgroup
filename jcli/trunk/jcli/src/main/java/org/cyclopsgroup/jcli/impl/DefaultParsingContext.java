package org.cyclopsgroup.jcli.impl;

import org.cyclopsgroup.jcli.spi.CliDefinition;
import org.cyclopsgroup.jcli.spi.CliUtils;
import org.cyclopsgroup.jcli.spi.OptionDefinition;
import org.cyclopsgroup.jcli.spi.ParsingContext;

class DefaultParsingContext
    implements ParsingContext
{
    private final CliDefinition cli;

    DefaultParsingContext( Class<?> beanType )
    {
        cli = CliUtils.defineCli( beanType );
    }

    /**
     * @inheritDoc
     */
    @Override
    public OptionDefinition optionWithLongName( String longName )
    {
        for ( OptionDefinition o : cli.getOptions().values() )
        {
            if ( o.getOption().longName().equals( longName ) )
            {
                return o;
            }
        }
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public OptionDefinition optionWithShortName( String shortName )
    {
        for ( OptionDefinition o : cli.getOptions().values() )
        {
            if ( o.getOption().name().equals( shortName ) )
            {
                return o;
            }
        }
        return null;
    }
}
