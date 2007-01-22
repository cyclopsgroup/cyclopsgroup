package com.cyclopsgroup.waterview.impl.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.waterview.RunData;
import com.cyclopsgroup.waterview.ServiceManager;
import com.cyclopsgroup.waterview.ServiceNotFoundException;
import com.cyclopsgroup.waterview.spi.Waterview;

public class SpringWaterviewServlet
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
        Waterview waterview = serviceManager.getService( Waterview.class );
        RunData data = new ServletRunData( waterview, getServletContext(), req, resp );
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
    }

    @Override
    public void init()
        throws ServletException
    {
        synchronized ( getServletContext() )
        {
            container = (SpringContainer) getServletContext().getAttribute( "spring.container" );
            if ( container != null )
            {
                return;
            }

            String springConfig = getServletConfig().getInitParameter( "spring.config" );

            if ( StringUtils.isNotEmpty( springConfig ) )
            {
                try
                {
                    container = new SpringContainer( new File( springConfig ).toURL() );
                }
                catch ( MalformedURLException e )
                {
                    throw new ServletException( "Bad spring config path", e );
                }
            }
            else
            {
                container = new SpringContainer();
            }
            try
            {
                container.initialize();
                getServletContext().setAttribute( "spring.container", container );
            }
            catch ( IOException e )
            {
                throw new ServletException( "Initializing spring container failed", e );
            }
        }
    }
}