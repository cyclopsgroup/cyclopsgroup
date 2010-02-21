package org.cyclopsgroup.jcli.spi;

public interface ParsingContext
{
    Option optionWithShortName( String shortName );

    Option optionWithLongName( String longName );
}
