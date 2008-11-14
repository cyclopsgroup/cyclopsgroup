package com.cyclopsgroup.waterview.impl.servlet;

import java.io.File;
import java.net.MalformedURLException;

import javax.servlet.ServletException;

import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.waterview.impl.spring.SpringContainer;

public class DedicatedSpringWaterviewServlet
    extends AbstractSpringWaterviewServlet
{
    private static final long serialVersionUID = 1L;

    @Override
    protected SpringContainer createSpringContainer()
        throws ServletException
    {
        String springConfig = getServletConfig().getInitParameter( "spring.config" );

        if ( StringUtils.isNotEmpty( springConfig ) )
        {
            try
            {
                springConfig = getServletContext().getRealPath( springConfig );
                return new SpringContainer( new File( springConfig ).toURL() );
            }
            catch ( MalformedURLException e )
            {
                throw new ServletException( "Bad spring config path", e );
            }
        }
        return new SpringContainer();
    }
}