package org.cyclopsgroup.waterview.impl.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cyclopsgroup.waterview.PageRedirection;
import org.cyclopsgroup.waterview.impl.pipeline.WebContextProcessor;

/**
 * Main servlet for waterview
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public abstract class AbstractWaterviewServlet
    extends HttpServlet
{
    private static final Log LOG = LogFactory.getLog( AbstractWaterviewServlet.class );

    private static final long serialVersionUID = 1L;

    private WebContextProcessor processor;

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
        PageRedirection redirection = context.getRedirection();
        if(redirection != null)
        {
            redirection.redirect( req, resp );
        }
    }

    /**
     * @return New instance of web context processor
     */
    protected abstract WebContextProcessor createWebContextProcessor();

    /**
     * @inheritDoc
     */
    @Override
    public void init()
        throws ServletException
    {
        processor = createWebContextProcessor();
        LOG.info( "Servlet is initialized with context processor " + processor );
    }
}
