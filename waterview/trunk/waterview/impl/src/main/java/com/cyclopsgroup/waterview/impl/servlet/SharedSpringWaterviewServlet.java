package com.cyclopsgroup.waterview.impl.servlet;

import java.io.File;
import java.net.MalformedURLException;

import javax.servlet.ServletException;

import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.waterview.impl.spring.SpringContainer;

public class SharedSpringWaterviewServlet
    extends AbstractSpringWaterviewServlet
{
    private static final long serialVersionUID = 1L;

    private static final String SPRING_CONTAINER = "spring.container";

    @Override
    protected SpringContainer createSpringContainer()
        throws ServletException
    {
        SpringContainer sc = null;
        synchronized ( getServletContext() )
        {
            sc = (SpringContainer) getServletContext().getAttribute( SPRING_CONTAINER );
            if ( sc != null )
            {
                return sc;
            }

            String springConfig = getServletContext().getInitParameter( "spring.config" );
            if ( StringUtils.isNotEmpty( springConfig ) )
            {
                try
                {
                    springConfig = getServletContext().getRealPath( springConfig );
                    sc = new SpringContainer( new File( springConfig ).toURL() );
                }
                catch ( MalformedURLException e )
                {
                    throw new ServletException( "Bad spring config path", e );
                }
            }
            else
            {
                sc = new SpringContainer();
            }
            getServletContext().setAttribute( SPRING_CONTAINER, sc );
            return sc;
        }
    }
}
