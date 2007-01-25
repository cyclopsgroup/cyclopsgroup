package com.cyclopsgroup.waterview.impl.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.waterview.Context;
import com.cyclopsgroup.waterview.RunData;
import com.cyclopsgroup.waterview.ServiceManager;
import com.cyclopsgroup.waterview.ServiceNotFoundException;
import com.cyclopsgroup.waterview.impl.spring.SpringContainer;
import com.cyclopsgroup.waterview.spi.Waterview;

public abstract class AbstractSpringWaterviewServlet
    extends HttpServlet
{
    private SpringContainer container;

    private ServiceManager serviceManager = new ServiceManager()
    {
        @SuppressWarnings("unchecked")
        @Override
        public <T> T getService( String serviceRole )
            throws ServiceNotFoundException
        {
            if ( container == null )
            {
                throw new IllegalStateException( "Servlet is not ready, container is still null" );
            }

            return (T) container.getBean( serviceRole );
        }
    };

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
    protected void doGet( HttpServletRequest req, HttpServletResponse resp )
        throws ServletException, IOException
    {
        handleRequest( req, resp );
    }

    protected void doHandleException( HttpServletRequest req, HttpServletResponse resp, Exception ex )
        throws ServletException, IOException
    {
        ex.printStackTrace( new PrintWriter( resp.getOutputStream() ) );
        resp.getOutputStream().flush();
    }

    protected void doHandleRequest( HttpServletRequest req, HttpServletResponse resp )
        throws Exception
    {
        RunData data = new ServletRunData( waterview, getServletContext(), req, resp );
        Context requestContext = data.getRequestContext();
        requestContext.put( "httpRequest", req );
        requestContext.put( "httpResponse", resp );
        requestContext.put( "servletContext", getServletContext() );
        requestContext.put( "servletConfig", getServletConfig() );
        requestContext.put( "data", data );
        waterview.processRunData( data );
    }

    @Override
    protected void doPost( HttpServletRequest req, HttpServletResponse resp )
        throws ServletException, IOException
    {
        handleRequest( req, resp );
    }

    private void handleRequest( HttpServletRequest req, HttpServletResponse resp )
        throws ServletException, IOException
    {
        try
        {
            doHandleRequest( req, resp );
        }
        catch ( Exception e )
        {
            doHandleException( req, resp, e );
        }
        finally
        {
            resp.getOutputStream().flush();
        }
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

        try
        {
            waterview = serviceManager.getService( waterviewBeanId );
        }
        catch ( ServiceNotFoundException e )
        {
            throw new ServletException( "Waterview bean [" + waterviewBeanId + "] is not defined in spring container" );
        }
    }
}