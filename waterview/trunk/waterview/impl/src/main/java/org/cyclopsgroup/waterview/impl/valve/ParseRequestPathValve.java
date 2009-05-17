package org.cyclopsgroup.waterview.impl.valve;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.Validate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cyclopsgroup.waterview.spi.Valve;
import org.cyclopsgroup.waterview.spi.ValveContext;
import org.cyclopsgroup.waterview.spi.WebContext;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class ParseRequestPathValve
    implements Valve
{
    private static final Log LOG = LogFactory.getLog( ParseRequestPathValve.class );

    private static Pattern EXTENSION_PATTERN = Pattern.compile( "\\.\\w+\\/" );

    private final String defaultPage;

    /**
     * @param defaultPage Default page
     */
    public ParseRequestPathValve( String defaultPage )
    {
        Validate.notNull( defaultPage, "Default page can't be NULL" );
        this.defaultPage = defaultPage;
    }

    /**
     * Default constructor
     */
    public ParseRequestPathValve()
    {
        this( "/index.vm" );
    }

    /**
     * @inheritDoc
     */
    @Override
    public void invoke( ValveContext context )
        throws IOException
    {
        WebContext wc = context.getWebContext();
        String pathInfo = wc.getServletRequest().getServletPath();
        Matcher matcher = EXTENSION_PATTERN.matcher( pathInfo );
        List<String> actions = new ArrayList<String>();
        int start = 0;
        while ( matcher.find() )
        {
            String path = pathInfo.substring( start, matcher.end() - 1 );
            start = matcher.end() - 1;
            actions.add( path );
        }
        String lastOperation = pathInfo.substring( start );
        if ( lastOperation.length() <= 1 )
        {
            actions.add( defaultPage );
        }
        else
        {
            actions.add( pathInfo.substring( start ) );
        }
        if ( LOG.isDebugEnabled() )
        {
            LOG.debug( "From requested " + pathInfo + ", waterview figures out operations: " + actions );
        }
        context.setActions( actions );
        context.invokeNext();
    }
}
