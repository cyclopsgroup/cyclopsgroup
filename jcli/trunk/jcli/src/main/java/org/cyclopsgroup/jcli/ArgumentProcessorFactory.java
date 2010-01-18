package org.cyclopsgroup.jcli;

import java.util.Iterator;
import java.util.ServiceLoader;

import org.cyclopsgroup.jcli.spi.CommandLineParser;

public abstract class ArgumentProcessorFactory
{
    static ArgumentProcessorFactory getInstance()
    {
        Iterator<ArgumentProcessorFactory> factories = ServiceLoader.load( ArgumentProcessorFactory.class ).iterator();
        if ( factories.hasNext() )
        {
            return factories.next();
        }
        throw new AssertionError( "Can't find an implementation of " + ArgumentProcessorFactory.class.getName()
            + " from service loader" );
    }

    protected abstract <T> ArgumentProcessor<T> newProcessor( Class<T> beanType, CommandLineParser parser );
}
