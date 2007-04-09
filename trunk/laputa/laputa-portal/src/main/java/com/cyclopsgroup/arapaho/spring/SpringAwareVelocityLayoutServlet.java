package com.cyclopsgroup.arapaho.spring;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.apache.commons.lang.StringUtils;
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
    private static final String DEFAULT_VELOCITY_ENGINE_BEAN = VelocityEngine.class.getName();

    private static final String VELOCITY_BEAN_PARAMETER = "velocity.engine.bean";

    private String velocityEngineBeanName;

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
    public void init( ServletConfig config )
        throws ServletException
    {
        velocityEngineBeanName = config.getInitParameter( VELOCITY_BEAN_PARAMETER );
        if ( StringUtils.isEmpty( velocityEngineBeanName ) )
        {
            velocityEngineBeanName = DEFAULT_VELOCITY_ENGINE_BEAN;
        }
        super.init( config );
    }

    @Override
    protected void initVelocity( ServletConfig config )
        throws ServletException
    {
        WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext( getServletContext() );
        VelocityEngine velocity = (VelocityEngine) ctx.getBean( velocityEngineBeanName );
        setVelocityEngine( velocity );
    }
}
