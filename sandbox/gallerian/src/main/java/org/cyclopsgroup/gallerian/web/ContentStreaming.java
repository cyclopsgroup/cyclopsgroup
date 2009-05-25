package org.cyclopsgroup.gallerian.web;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.cyclopsgroup.gallerian.ContentId;
import org.cyclopsgroup.gallerian.ContentStreamService;
import org.cyclopsgroup.waterview.Constants;
import org.cyclopsgroup.waterview.InputParameter;
import org.cyclopsgroup.waterview.InputParameterType;
import org.cyclopsgroup.waterview.Module;
import org.cyclopsgroup.waterview.Page;

/**
 * Web module for streaming
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class ContentStreaming
{
    private static final Pattern REPOSITORY_NAME = Pattern.compile( "^\\/\\w+" );

    private static ContentId getContentId( String path )
    {
        String contentPath = REPOSITORY_NAME.matcher( path ).replaceFirst( "" );
        String repository = path.substring( 1, path.length() - contentPath.length() );
        return new ContentId( contentPath, repository );
    }

    private final ContentStreamService streaming;

    /**
     * @param streaming Streaming service interface
     */
    public ContentStreaming( ContentStreamService streaming )
    {
        this.streaming = streaming;
    }

    /**
     * Stream content to http response
     * 
     * @param path Path of content
     * @param servletContext Servlet context
     * @param response HTTP servlet response to stream content to
     * @throws IOException If network IO fails
     */
    @Module( path = "/download.do" )
    @Page( raw = true )
    public void download(
                          @InputParameter( name = WebConstants.CONTENT_PATH, type = InputParameterType.VARIABLE ) String path,
                          @InputParameter( name = Constants.SERVLET_CONTEXT, type = InputParameterType.VARIABLE ) ServletContext servletContext,
                          @InputParameter( name = Constants.SERVLET_RESPONSE, type = InputParameterType.VARIABLE ) HttpServletResponse response )
        throws IOException
    {
        ContentId contentId = getContentId( path );

        String contentType = servletContext.getMimeType( contentId.getPath() );
        InputStream content = streaming.openContent( contentId );
        try
        {
            if ( contentType == null )
            {
                File file = new File( contentId.getPath() );
                response.addHeader( "content-disposition", "attachment; filename=" + file.getName() );
            }
            else
            {
                response.addHeader( "content-type", contentType );
            }
            IOUtils.copy( content, response.getOutputStream() );
            response.getOutputStream().flush();
        }
        finally
        {
            content.close();
        }
    }

    /**
     * Display icon image
     * 
     * @param contentType Given MIME type
     * @param response HTTP response
     * @throws IOException If network IO or file system fails
     */
    @Module( path = "/icon.do" )
    @Page( raw = true )
    public void icon(
                      @InputParameter( name = WebConstants.CONTENT_TYPE, type = InputParameterType.VARIABLE ) String contentType,
                      @InputParameter( name = Constants.SERVLET_RESPONSE, type = InputParameterType.VARIABLE ) HttpServletResponse response )
        throws IOException
    {
        response.addHeader( "content-type", streaming.getIconMimeType() );
        InputStream content = streaming.openIcon( contentType );
        try
        {
            IOUtils.copy( content, response.getOutputStream() );
            response.getOutputStream().flush();
        }
        finally
        {
            content.close();
        }
    }

    /**
     * Stream content of icon to http response
     * 
     * @param path Path of content
     * @param response HTTP servlet response to stream content to
     * @throws IOException If network IO fails
     */
    @Module( path = "/thumbnail.do" )
    @Page( raw = true )
    public void thumbnail(
                           @InputParameter( name = WebConstants.CONTENT_PATH, type = InputParameterType.VARIABLE ) String path,
                           @InputParameter( name = Constants.SERVLET_RESPONSE, type = InputParameterType.VARIABLE ) HttpServletResponse response )
        throws IOException
    {
        ContentId contentId = getContentId( path );
        response.addHeader( "content-type", streaming.getThumbnailMimeType() );
        InputStream content = streaming.openThumbnail( contentId );
        try
        {
            IOUtils.copy( content, response.getOutputStream() );
            response.getOutputStream().flush();
        }
        finally
        {
            content.close();
        }
    }
}
