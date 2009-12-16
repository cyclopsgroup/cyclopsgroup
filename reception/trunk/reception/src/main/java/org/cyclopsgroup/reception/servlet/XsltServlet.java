package org.cyclopsgroup.reception.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.cache.CacheException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cyclopsgroup.reception.io.XsltManager;

/**
 * Servlet that does a general XSLT transformation
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
@SuppressWarnings( "serial" )
public class XsltServlet
    extends HttpServlet
{
    private final XsltManager xsltManager;

    /**
     * @throws CacheException If cache can't be initialized
     */
    public XsltServlet()
        throws CacheException
    {
        this.xsltManager = new XsltManager();
    }

    /**
     * @inheritDoc
     */
    @Override
    protected void doGet( HttpServletRequest req, HttpServletResponse resp )
        throws IOException
    {
        doProcess( req, resp.getWriter() );
    }

    private void doProcess( HttpServletRequest req, PrintWriter out )
    {
        String input = req.getParameter( "i" );
        String template = req.getParameter( "t" );
        try
        {
            xsltManager.transform( input, template, out );
        }
        catch ( Exception e )
        {
            out.println( "<pre>" );
            e.printStackTrace( out );
            out.println( "</pre>" );
        }
        finally
        {
            out.flush();
        }
    }
}
