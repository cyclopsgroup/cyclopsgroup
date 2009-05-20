package org.cyclopsgroup.gallerian.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.cyclopsgroup.waterview.spi.Valve;
import org.cyclopsgroup.waterview.spi.ValveContext;
import org.cyclopsgroup.waterview.spi.WebContext;

/**
 * Valve to parse request URL in gallerian specific way
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class ParseRequestValve
    implements Valve
{
    private static final Pattern ACTION_PATTERN = Pattern.compile( "\\/\\w+\\.\\w+$" );

    private static final String DEFAULT_ACTION = "/browse.vm";
    private static final String DOWNLOAD_ACTION = "/download.do";

    /**
     * @inheritDoc
     */
    @Override
    public void invoke( ValveContext context )
        throws IOException
    {
        WebContext wc = context.getWebContext();
        String pathInfo = wc.getServletRequest().getServletPath();

        String action;
        String contentPath;
        if ( StringUtils.isEmpty( pathInfo ) )
        {
            action = DEFAULT_ACTION;
            contentPath = "";
        }
        else
        {
            Matcher matcher = ACTION_PATTERN.matcher( pathInfo );
            if ( matcher.find() )
            {
                String name = matcher.group();
                if(name.endsWith( ".vm" ))
                {
                    contentPath = StringUtils.substring( pathInfo, 0, pathInfo.length() - name.length() );
                    action = name;
                }
                else {
                    contentPath = pathInfo;
                    action = DOWNLOAD_ACTION;
                }
            }
            else
            {
                action = DEFAULT_ACTION;
                contentPath = pathInfo;
            }
        }
        context.setActions( new ArrayList<String>(Arrays.asList( action )) );
        if(StringUtils.isEmpty( contentPath ))
        {
            contentPath = "/";
        }
        wc.setVariable( "contentPath", contentPath );
        context.invokeNext();
    }
}
