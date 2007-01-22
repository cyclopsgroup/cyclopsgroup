package com.cyclopsgroup.waterview.impl.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        // TODO Auto-generated method stub
        super.destroy();
    }

    @Override
    public void init()
        throws ServletException
    {
        // TODO Auto-generated method stub
        super.init();
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
        ex.printStackTrace( resp.getWriter() );
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
}
