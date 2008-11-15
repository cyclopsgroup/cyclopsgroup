package org.cyclopsgroup.waterview.impl.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Main servlet for waterview
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class WaterviewServlet
    extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    /**
     * @inheritDoc
     */
    @Override
    public void destroy()
    {
        // TODO Auto-generated method stub
        super.destroy();
    }

    /**
     * @inheritDoc
     */
    @Override
    protected void doGet( HttpServletRequest req, HttpServletResponse resp )
        throws ServletException, IOException
    {
        RootWebContext rootContext = new RootWebContext( req, resp );
    }

    /**
     * @inheritDoc
     */
    @Override
    protected void doPost( HttpServletRequest req, HttpServletResponse resp )
        throws ServletException, IOException
    {
        // TODO Auto-generated method stub
        super.doPost( req, resp );
    }

    /**
     * @inheritDoc
     */
    @Override
    public void init()
        throws ServletException
    {
        // TODO Auto-generated method stub
        super.init();
    }

}
