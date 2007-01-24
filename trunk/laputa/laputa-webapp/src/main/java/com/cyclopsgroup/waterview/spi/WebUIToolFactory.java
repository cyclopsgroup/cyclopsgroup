package com.cyclopsgroup.waterview.spi;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface WebUIToolFactory
{
    void destroy( HttpServlet servlet )
        throws Exception;

    Object getTool( HttpServlet servlet, HttpServletRequest req, HttpServletResponse resp )
        throws Exception;

    void init( HttpServlet servlet )
        throws Exception;
}
