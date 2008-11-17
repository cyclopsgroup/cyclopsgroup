package org.cyclopsgroup.waterview.impl.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cyclopsgroup.waterview.impl.WebContextProcessor;
import org.cyclopsgroup.waterview.impl.assembly.DefaultWebContextProcessor;

/**
 * Main servlet for waterview
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class WaterviewServlet
    extends HttpServlet
{
    private static final Log LOG = LogFactory.getLog( WaterviewServlet.class );

    private static final long serialVersionUID = 1L;

    private transient WebContextProcessor processor;

    /**
     * @inheritDoc
     */
    @Override
    public void destroy()
    {
        LOG.info( "Servlet is destroyed" );
    }

    /**
     * @inheritDoc
     */
    @Override
    protected void doGet( HttpServletRequest req, HttpServletResponse resp )
        throws ServletException, IOException
    {
        doProcess( req, resp );
    }

    /**
     * @inheritDoc
     */
    @Override
    protected void doPost( HttpServletRequest req, HttpServletResponse resp )
        throws ServletException, IOException
    {
        doProcess( req, resp );
    }

    private void doProcess( HttpServletRequest req, HttpServletResponse resp )
        throws IOException
    {
        RootWebContext context = new RootWebContext( req, resp );
        processor.process( context );
    }

    /**
     * @inheritDoc
     */
    @Override
    public void init()
        throws ServletException
    {
        String templatePath = getServletContext().getRealPath( "templates" );
        LOG.info( "Template root directory is " + templatePath );
        processor = new DefaultWebContextProcessor( templatePath );
        LOG.info( "Servlet is initialized" );
    }
}
