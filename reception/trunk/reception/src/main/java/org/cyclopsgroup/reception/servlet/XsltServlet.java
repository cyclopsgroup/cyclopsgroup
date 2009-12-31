package org.cyclopsgroup.reception.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet that does a general XSLT transformation
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
@SuppressWarnings( "serial" )
public class XsltServlet
    extends HttpServlet
{
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
        try
        {
            out.println( req.toString() );
        }
        catch ( Exception e )
        {
            e.printStackTrace( out );
        }
        finally
        {
            out.flush();
        }
    }
}
