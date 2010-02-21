package org.cyclopsgroup.jcli.spi;

/**
 * Implementation of this interface is aware of syntax of command arguments
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
public interface CommandLineParser
{
    /**
     * @param arguments List of arguments
     * @param context Parsing context
     * @return Command line with values to pass to bean
     */
    CommandLine parse( String[] arguments, ParsingContext context );
}
