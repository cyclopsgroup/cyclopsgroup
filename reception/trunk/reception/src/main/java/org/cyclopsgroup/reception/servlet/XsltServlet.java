package org.cyclopsgroup.reception.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
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
        throws ServletException, IOException
    {
        resp.getWriter().printf( "Receive request: %s", req );
        resp.getWriter().flush();
        resp.getWriter().close();
    }
}
