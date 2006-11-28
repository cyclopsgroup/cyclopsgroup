package com.cyclopsgroup.waterview.marmalade;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.marmalade.model.MarmaladeScript;
import org.codehaus.marmalade.runtime.DefaultContext;
import org.codehaus.marmalade.runtime.MarmaladeExecutionContext;

public class MarmaladeViewServlet
    extends HttpServlet
{
    public static final String VIEW_VARIABLES_NAME = "viewVariables";

    private ScriptLoader scriptLoader;

    @SuppressWarnings("unchecked")
    protected MarmaladeExecutionContext createExecutionContext( HttpServletRequest req, HttpServletResponse resp )
        throws Exception
    {
        Map<String, Object> viewVariables = (Map<String, Object>) req.getAttribute( VIEW_VARIABLES_NAME );
        DefaultContext context;
        if ( viewVariables == null )
        {
            context = new DefaultContext();
        }
        else
        {
            context = new DefaultContext( viewVariables );
        }

        context.setOutWriter( resp.getWriter() );

        return context;
    }

    protected ScriptLoader createScriptLoader()
    {
        return new InstantFileScriptLoader()
        {
            @Override
            protected File getScriptFile( String scriptPath )
            {
                return new File( getServletContext().getRealPath( scriptPath ) );
            }
        };
    }

    @Override
    protected void doGet( HttpServletRequest req, HttpServletResponse resp )
        throws ServletException, IOException
    {
        process( req, resp );
    }

    protected void doInit()
        throws Exception
    {
        scriptLoader = createScriptLoader();
    }

    @Override
    protected void doPost( HttpServletRequest req, HttpServletResponse resp )
        throws ServletException, IOException
    {
        process( req, resp );
    }

    protected void doProcess( HttpServletRequest req, HttpServletResponse resp )
        throws Exception
    {
        String scriptPath = req.getServletPath();
        if ( req.getPathInfo() != null )
        {
            scriptPath += req.getPathInfo();
        }
        MarmaladeScript script = scriptLoader.loadScript( scriptPath );

        MarmaladeExecutionContext executionContext = createExecutionContext( req, resp );

        script.execute( executionContext );
    }

    @Override
    public void init()
        throws ServletException
    {
        try
        {
            doInit();
        }
        catch ( ServletException e )
        {
            throw e;
        }
        catch ( Exception e )
        {
            throw new ServletException( "Initialization error", e );
        }
    }

    protected void process( HttpServletRequest req, HttpServletResponse resp )
        throws ServletException, IOException
    {
        try
        {
            doProcess( req, resp );
        }
        catch ( ServletException e )
        {
            throw e;
        }
        catch ( IOException e )
        {
            throw e;
        }
        catch ( Exception e )
        {
            throw new ServletException( "Servlet execution exception", e );
        }
    }
}
