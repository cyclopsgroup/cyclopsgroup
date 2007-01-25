package com.cyclopsgroup.waterview.impl.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cyclopsgroup.waterview.Context;
import com.cyclopsgroup.waterview.RunData;
import com.cyclopsgroup.waterview.spi.Waterview;

public abstract class AbstractWaterviewServlet
    extends HttpServlet
{
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
        RunData data = new ServletRunData( getWaterview(), getServletContext(), req, resp );
        Context requestContext = data.getRequestContext();
        requestContext.put( "httpRequest", req );
        requestContext.put( "httpResponse", resp );
        requestContext.put( "servletContext", getServletContext() );
        requestContext.put( "servletConfig", getServletConfig() );
        requestContext.put( "data", data );
        getWaterview().processRunData( data );
    }

    @Override
    protected void doPost( HttpServletRequest req, HttpServletResponse resp )
        throws ServletException, IOException
    {
        handleRequest( req, resp );
    }

    protected abstract Waterview getWaterview();

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
}
