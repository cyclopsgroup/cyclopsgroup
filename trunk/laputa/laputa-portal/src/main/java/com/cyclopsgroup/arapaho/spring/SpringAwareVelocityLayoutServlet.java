package com.cyclopsgroup.arapaho.spring;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.apache.velocity.Template;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.tools.view.servlet.VelocityLayoutServlet;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class SpringAwareVelocityLayoutServlet
    extends VelocityLayoutServlet
{
    private static final String VELOCITY_ENGINE_BEAN_NAME = "com.cyclopsgroup.arapaho.spring.VecocityEngine";

    @Override
    public Template getTemplate( String name )
        throws ResourceNotFoundException, ParseErrorException, Exception
    {
        String templateName = name.charAt( 0 ) == '/' ? name.substring( 1 ) : name;
        return super.getTemplate( templateName );
    }

    @Override
    public Template getTemplate( String name, String encoding )
        throws ResourceNotFoundException, ParseErrorException, Exception
    {
        String templateName = name.charAt( 0 ) == '/' ? name.substring( 1 ) : name;
        return super.getTemplate( templateName, encoding );
    }

    @Override
    protected void initVelocity( ServletConfig config )
        throws ServletException
    {
        WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext( getServletContext() );
        VelocityEngine velocity = (VelocityEngine) ctx.getBean( VELOCITY_ENGINE_BEAN_NAME );
        setVelocityEngine( velocity );
    }
}
