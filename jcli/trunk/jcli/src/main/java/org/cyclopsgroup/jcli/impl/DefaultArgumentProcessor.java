package org.cyclopsgroup.jcli.impl;

import org.cyclopsgroup.jcli.ArgumentProcessor;
import org.cyclopsgroup.jcli.spi.CommandLine;
import org.cyclopsgroup.jcli.spi.CommandLineParser;

class DefaultArgumentProcessor<T>
    extends ArgumentProcessor<T>
{
    private final Class<T> beanType;

    private final CommandLineParser parser;

    DefaultArgumentProcessor( Class<T> beanType, CommandLineParser parser )
    {
        this.beanType = beanType;
        this.parser = parser;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void process( String[] arguments, Object bean )
    {
        DefaultParsingContext context = new DefaultParsingContext( beanType );
        CommandLine cli = parser.parse( arguments, context );
        
    }
}
