package com.cyclopsgroup.waterview.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.waterview.alternative.AbstractRunData;
import com.cyclopsgroup.waterview.spi.WaterviewSpi;

public class ServletRunData
    extends AbstractRunData
{
    private final HttpServletRequest servletRequest;

    private final HttpServletResponse servletResponse;

    public ServletRunData( WaterviewSpi waterview, HttpServletRequest request, HttpServletResponse response,
                           FileUpload fileUpload )
    {
        super( waterview );
        servletRequest = request;
        servletResponse = response;

        setQueryString( request.getQueryString() );
        setRefererUrl( request.getHeader( "referer" ) );

        //Session Context
        setSessionContext( new HttpSessionContext( request.getSession() ) );
        setSessionId( request.getSession().getId() );

        setRequestContext( new ServletRequestContext( request ) );

        //Request path
        String requestPath = request.getPathInfo();
        setRequestPath( requestPath == null ? StringUtils.EMPTY : requestPath );

        if ( FileUploadBase.isMultipartContent( servletRequest ) )
        {

        }
        else
        {

        }
    }

    public String getMimeType( String fileName )
    {
        // TODO Auto-generated method stub
        return null;
    }

    public PrintWriter getOutput()
        throws IOException
    {
        return servletResponse.getWriter();
    }

    public OutputStream getOutputStream()
        throws IOException
    {
        return servletResponse.getOutputStream();
    }

    public void setOutputContentType( String contentType )
    {
        // TODO Auto-generated method stub

    }

}
