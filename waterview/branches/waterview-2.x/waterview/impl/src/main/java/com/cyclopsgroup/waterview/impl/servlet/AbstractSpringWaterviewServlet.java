package com.cyclopsgroup.waterview.impl.servlet;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.waterview.impl.spring.SpringContainer;
import com.cyclopsgroup.waterview.spi.Waterview;

public abstract class AbstractSpringWaterviewServlet
    extends AbstractWaterviewServlet
{
    private SpringContainer container;

    private Waterview waterview;

    protected abstract SpringContainer createSpringContainer()
        throws ServletException;

    @Override
    public void destroy()
    {
        container.dispose();
        container = null;
    }

    @Override
    protected Waterview getWaterview()
    {
        return waterview;
    }

    @Override
    public void init()
        throws ServletException
    {
        container = createSpringContainer();
        String webappDir = getServletContext().getRealPath( "" );
        container.setProperty( "webapp.dir", webappDir );
        container.setProperty( "basedir", webappDir );
        try
        {
            container.initialize();
            getServletContext().setAttribute( "spring.container", container );
        }
        catch ( IOException e )
        {
            throw new ServletException( "Initializing spring container failed", e );
        }

        String waterviewBeanId = getServletConfig().getInitParameter( "waterview.bean" );
        if ( StringUtils.isEmpty( waterviewBeanId ) )
        {
            waterviewBeanId = Waterview.class.getName();
        }

        waterview = (Waterview) container.getBean( waterviewBeanId );
    }
}