package org.cyclopsgroup.jcli;

import org.cyclopsgroup.jcli.spi.CommandLineParser;

public abstract class ArgumentProcessor<T>
{
    public static <T> ArgumentProcessor<T> newInstance( Class<T> beanType, CommandLineParser parser )
    {
        return ArgumentProcessorFactory.getInstance().newProcessor( beanType, parser );
    }

    public abstract void process( String[] arguments, T bean );
}
