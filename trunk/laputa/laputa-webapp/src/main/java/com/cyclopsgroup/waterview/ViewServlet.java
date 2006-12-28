package com.cyclopsgroup.waterview;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;


public class ViewServlet
    extends HttpServlet
    implements Constants
{
    @Override
    protected void doGet( HttpServletRequest req, HttpServletResponse resp )
        throws ServletException, IOException
    {
        handleRequest( req, resp );
    }

    protected void doHandleRequest( HttpServletRequest req, HttpServletResponse resp )
        throws Exception
    {
        String viewMode = req.getParameter( HTTP_HEADER_VIEW_MODE );
        if ( StringUtils.isEmpty( viewMode ) )
        {
            viewMode = req.getHeader( HTTP_HEADER_VIEW_MODE );
        }
        if ( StringUtils.isEmpty( viewMode ) )
        {
            viewMode = VIEW_MODE_BROWSE;
        }
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
        catch ( IOException e )
        {
            throw e;
        }
        catch ( ServletException e )
        {
            throw e;
        }
        catch ( Exception e )
        {
            throw new ServletException( e );
        }
    }

    @Override
    public void init()
        throws ServletException
    {
        // TODO Auto-generated method stub
        super.init();
    }
}