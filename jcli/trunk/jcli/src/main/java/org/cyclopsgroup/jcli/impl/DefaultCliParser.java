package org.cyclopsgroup.jcli.impl;

import java.beans.IntrospectionException;
import java.io.PrintWriter;

import org.cyclopsgroup.jcli.CliParser;
import org.cyclopsgroup.jcli.spi.CliDefinition;
import org.cyclopsgroup.jcli.spi.CliUtils;

/**
 * Default internal implementation of {@link CliParser}
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class DefaultCliParser
    implements CliParser
{
    private boolean strict;

    public final boolean isStrict()
    {
        return strict;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean parse( String[] args, Object bean )
    {
        CliDefinition cliDefinition = CliUtils.defineCli( bean.getClass() );
        for ( String arg : args )
        {
            if ( arg.startsWith( "--" ) )
            {

            }
            else if ( arg.startsWith( "-" ) )
            {

            }
        }
        return true;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void printUsage( Class<?> beanType, PrintWriter output )
        throws IntrospectionException
    {
        // TODO Auto-generated method stub

    }

    public final void setStrict( boolean strict )
    {
        this.strict = strict;
    }

}
