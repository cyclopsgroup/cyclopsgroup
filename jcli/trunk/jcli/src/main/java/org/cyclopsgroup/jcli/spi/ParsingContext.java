package org.cyclopsgroup.jcli.spi;

import java.util.List;

/**
 * Context for parsing arguments for given bean
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
public interface ParsingContext
{
    /**
     * @param shortName Name of option
     * @return Option model. NULL if it doesn't exist
     */
    Option optionWithShortName( String shortName );

    /**
     * @param longName Long name of option
     * @return Option model. NULL if it doesn't exist
     */
    Option optionWithLongName( String longName );

    /**
     * @return List of all options
     */
    List<Option> options();

    /**
     * @return Command line model
     */
    Cli cli();
}
