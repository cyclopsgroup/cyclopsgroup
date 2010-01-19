package org.cyclopsgroup.jcli.spi;


public interface ParsingContext
{
    OptionDefinition optionWithShortName( String shortName );

    OptionDefinition optionWithLongName( String longName );
}
