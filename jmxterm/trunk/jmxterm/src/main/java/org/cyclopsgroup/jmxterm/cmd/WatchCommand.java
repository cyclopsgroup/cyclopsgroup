package org.cyclopsgroup.jmxterm.cmd;

import java.io.IOException;
import java.text.FieldPosition;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.management.JMException;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;

import jline.ConsoleReader;

import org.cyclopsgroup.jcli.annotation.Argument;
import org.cyclopsgroup.jcli.annotation.Cli;
import org.cyclopsgroup.jcli.annotation.Option;
import org.cyclopsgroup.jmxterm.Command;
import org.cyclopsgroup.jmxterm.Session;
import org.cyclopsgroup.jmxterm.io.JlineCommandInput;

/**
 * Command to watch an MBean attribute TODO Consider the use case for CSV file backend generation
 *
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
@Cli( name = "watch", description = "Watch the value of one MBean attribute constantly", note = "DO NOT call this command in a script and expect decent output" )
public class WatchCommand
    extends Command
{
    private static final int DEFAULT_REFRESH_INTERVAL = 1;

    private static final String BUILDING_ATTRIBUTE_NOW = "%now";

    private List<String> attributes = new ArrayList<String>();

    private int refreshInterval = DEFAULT_REFRESH_INTERVAL;

    private String outputFormat;

    /**
     * @param outputFormat Pattern used in {@link MessageFormat}
     */
    @Option( name = "f", longName = "format", description = "Java pattern(java.text.MessageFormat) to print attribute values" )
    public final void setOutputFormat( String outputFormat )
    {
        this.outputFormat = outputFormat;
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<String> doSuggestArgument()
        throws IOException, JMException
    {
        if ( getSession().getBean() != null )
        {
            MBeanServerConnection con = getSession().getConnection().getServerConnection();
            MBeanAttributeInfo[] ais = con.getMBeanInfo( new ObjectName( getSession().getBean() ) ).getAttributes();
            List<String> results = new ArrayList<String>( ais.length );
            for ( MBeanAttributeInfo ai : ais )
            {
                results.add( ai.getName() );
            }
            results.add( BUILDING_ATTRIBUTE_NOW );
            return results;
        }
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void execute()
        throws IOException, JMException
    {
        Session session = getSession();
        if ( !( session.getInput() instanceof JlineCommandInput ) )
        {
            throw new IllegalStateException( "Under current context, watch command can't execute." );
        }
        final JlineCommandInput input = (JlineCommandInput) session.getInput();
        String domain = DomainCommand.getDomainName( null, session );
        if ( domain == null )
        {
            throw new IllegalStateException( "Please specify a domain using domain command first." );
        }
        String beanName = BeanCommand.getBeanName( null, domain, session );
        if ( beanName == null )
        {
            throw new IllegalStateException( "Please specify a bean using bean command first." );
        }

        final ObjectName name = new ObjectName( beanName );
        final MBeanServerConnection con = session.getConnection().getServerConnection();

        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        getSession().output.printMessage( "press any key to stop. DO NOT press Ctrl+C !!!" );
        executor.scheduleWithFixedDelay( new Runnable()
        {
            public void run()
            {
                try
                {
                    printValues( name, con, input.getConsole() );
                }
                catch ( IOException e )
                {
                    // TODO Haven't found a decent approach to handle this exception yet
                    e.printStackTrace();
                }
                catch ( JMException e )
                {
                    // TODO Haven't found a decent approach to handle this exception yet
                    e.printStackTrace();
                }
            }
        }, 0, refreshInterval, TimeUnit.SECONDS );
        System.in.read();
        executor.shutdownNow();
        input.getConsole().printNewline();
    }

    private void printValues( ObjectName beanName, MBeanServerConnection connection, ConsoleReader console )
        throws IOException, JMException
    {
        console.redrawLine();
        StringBuffer result = new StringBuffer();
        if ( outputFormat == null )
        {
            boolean first = true;
            for ( String attributeName : attributes )
            {
                if ( first )
                {
                    first = false;
                }
                else
                {
                    result.append( ", " );
                }
                result.append( getAttributeValue( beanName, attributeName, connection ) );
            }
        }
        else
        {
            Object[] values = new Object[attributes.size()];
            int i = 0;
            for ( String attributeNamne : attributes )
            {
                values[i++] = getAttributeValue( beanName, attributeNamne, connection );
            }
            MessageFormat format = new MessageFormat( outputFormat );
            format.format( values, result, new FieldPosition( 0 ) );
        }
        console.printString( result.toString() );
    }

    private Object getAttributeValue( ObjectName beanName, String attributeName, MBeanServerConnection connection )
        throws JMException, IOException
    {
        // $now is a reserved keyword for current java.util.Date
        if ( attributeName.equals( BUILDING_ATTRIBUTE_NOW ) )
        {
            return new Date();
        }
        return connection.getAttribute( beanName, attributeName );
    }

    /**
     * @param attributes Name of attributes to watch
     */
    @Argument( description = "Name of attributes to watch", requires = 1 )
    public final void setAttributes( List<String> attributes )
    {
        this.attributes = attributes;
    }

    /**
     * @param refreshInterval Refreshing interval in seconds
     */
    @Option( name = "i", longName = "interval", description = "Optional number of seconds between consecutive poll, default is 1 second", defaultValue = "1" )
    public final void setRefreshInterval( int refreshInterval )
    {
        this.refreshInterval = refreshInterval;
    }
}
