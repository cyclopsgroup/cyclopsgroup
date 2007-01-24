package com.cyclopsgroup.waterview.velocity;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.ExtendedProperties;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.resource.loader.FileResourceLoader;
import org.apache.velocity.tools.view.servlet.VelocityLayoutServlet;

public class ViewServlet
    extends VelocityLayoutServlet
{
    @Override
    protected Context createContext( HttpServletRequest req, HttpServletResponse resp )
    {
        return super.createContext( req, resp );
    }

    @Override
    protected ExtendedProperties loadConfiguration( ServletConfig config )
        throws IOException
    {
        String webappDir = getServletContext().getRealPath( "" );
        ExtendedProperties p = new ExtendedProperties();
        p.setProperty( "webapp.dir", webappDir );

        //Define file system resource loader
        p.addProperty( "resource.loader", "__file__" );
        p.setProperty( "__file__.resource.loader.class", FileResourceLoader.class.getName() );
        p.setProperty( "__file__.resource.laoder.description", "Template loader that loads vm from webapp directory" );
        p.setProperty( "__file__.resource.loader.path", webappDir );

        //Define classpath resource loader
        p.addProperty( "resource.loader", "__classpath__" );
        p.setProperty( "__classpath__.resource.loader.class", TemplateResourceLoader.class.getName() );
        p.setProperty( "__classpath__.resource.loader.decription", "Internal package based classpath resource loader" );

        String pathMappings = config.getInitParameter( "path-mappings" );
        if ( StringUtils.isNotEmpty( pathMappings ) )
        {
            for ( String pathMapping : StringUtils.split( pathMappings, ',' ) )
            {
                p.addProperty( "__classpath__.resource.loader.mapping", pathMapping.trim() );
            }
        }

        //Define extra properties if there's any
        String velocityProperties = config.getInitParameter( "velocity.properties" );
        if ( StringUtils.isNotEmpty( velocityProperties ) )
        {
            p.load( new FileInputStream( velocityProperties ) );
        }
        return p;
    }

    /**
     * Overrides VelocityViewServlet.mergeTemplate to do a two-pass 
     * render for handling layouts
     */
    protected void mergeTemplate( Template template, Context context, HttpServletResponse response )
        throws ResourceNotFoundException, ParseErrorException, MethodInvocationException, IOException,
        UnsupportedEncodingException, Exception
    {
        //
        // this section is based on Tim Colson's "two pass render"
        //
        // Render the screen content
        StringWriter sw = new StringWriter();
        template.merge( context, sw );
        // Add the resulting content to the context
        context.put( KEY_SCREEN_CONTENT, sw.toString() );

        // Check for an alternate layout
        //
        // we check after merging the screen template so the screen 
        // can overrule any layout set in the request parameters
        // by doing #set( $layout = "MyLayout.vm" )
        Object obj = context.get( KEY_LAYOUT );
        String layout = ( obj == null ) ? null : obj.toString();
        if ( layout == null )
        {
            // no alternate, use default
            layout = defaultLayout;
        }
        else
        {
            // make it a full(er) path
            layout = layoutDir + layout;
        }

        try
        {
            //load the layout template
            template = getTemplate( layout );
        }
        catch ( Exception e )
        {
            getVelocityEngine().error( "VelocityLayoutServlet: Can't load layout \"" + layout + "\": " + e );

            // if it was an alternate layout we couldn't get...
            if ( !layout.equals( defaultLayout ) )
            {
                // try to get the default layout
                // if this also fails, let the exception go
                template = getTemplate( defaultLayout );
            }
        }

    }
}
