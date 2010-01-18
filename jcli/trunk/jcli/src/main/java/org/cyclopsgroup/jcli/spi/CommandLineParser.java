package org.cyclopsgroup.jcli.spi;

public interface CommandLineParser
{
    CommandLine parse( String[] arguments, ParsingContext context );
}
