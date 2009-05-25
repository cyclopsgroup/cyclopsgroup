package org.cyclopsgroup.gallerian.serv;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;

import org.cyclopsgroup.gallerian.ContentId;
import org.cyclopsgroup.gallerian.ContentStreamService;
import org.cyclopsgroup.gallerian.spi.FileProvider;
import org.springframework.web.context.ServletContextAware;

/**
 * Content stream service implementation based on a local listing service
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class LocalContentStreamService
    implements ContentStreamService, ServletContextAware
{
    /**
     * @inheritDoc
     */
    @Override
    public String getIconMimeType( ContentId id )
    {
        FileProvider file = listing.getFileProvider( id.getRepositoryName(), id.getPath() );
        String contentMimeType = servletContext.getMimeType( id.getPath() );
        if ( contentMimeType.equals( "image/jpeg" ) && file.getSize() < sizeThreshold )
        {
            return contentMimeType;
        }
        return servletContext.getMimeType( "x." + iconExtension );
    }

    private static final String DEFAULT_ICON_EXTENSION = "png";

    private String iconDirectory = new File( "./images" ).getAbsolutePath();

    private String iconExtension = DEFAULT_ICON_EXTENSION;

    private final ServerSideContentListingService listing;

    private ServletContext servletContext;

    private long sizeThreshold = 5 * 1024768L;

    /**
     * @param listing Server side listing service
     */
    public LocalContentStreamService( ServerSideContentListingService listing )
    {
        this.listing = listing;
    }

    /**
     * @return Value of field iconDirectory
     */
    public final String getIconDirectory()
    {
        return iconDirectory;
    }

    /**
     * @return Value of field sizeThreshold
     */
    public final long getSizeThreshold()
    {
        return sizeThreshold;
    }

    /**
     * @inheritDoc
     */
    @Override
    public InputStream openContent( ContentId id )
        throws IOException
    {
        FileProvider file = listing.getFileProvider( id.getRepositoryName(), id.getPath() );
        if ( file.getSize() >= sizeThreshold )
        {
            throw new IOException( "File size " + file.getSize() + "b is too big" );
        }
        return file.open();
    }

    /**
     * @inheritDoc
     */
    @Override
    public InputStream openIcon( ContentId id )
        throws IOException
    {
        FileProvider file = listing.getFileProvider( id.getRepositoryName(), id.getPath() );
        String contentType = servletContext.getMimeType( id.getPath() );
        if ( contentType.equals( "image/jpeg" ) && file.getSize() < sizeThreshold )
        {
            return openThumbnailIcon( file );
        }
        return openMappedIcon( contentType );
    }

    private InputStream openThumbnailIcon( FileProvider file )
        throws IOException
    {
        ThumbnailStudio s = new ThumbnailStudio();
        InputStream in = file.open();
        try
        {
            return s.openThumbnail( in, "jpeg" );
        }
        finally
        {
            in.close();
        }
    }

    private InputStream openMappedIcon( String contentType )
        throws IOException
    {
        File iconDir = new File( iconDirectory );
        File icon;
        if ( contentType == null )
        {
            icon = new File( iconDir, "unknown." + iconExtension );
        }
        else
        {
            icon = new File( iconDir, contentType.replace( '/', '-' ) + "." + iconExtension );
            if ( !icon.exists() )
            {
                icon = new File( iconDir, "unknown." + iconExtension );
            }
        }
        return new FileInputStream( icon );
    }

    /**
     * @param iconDirectory Value of field iconDirectory to set
     */
    public final void setIconDirectory( String iconDirectory )
    {
        this.iconDirectory = iconDirectory;
    }

    /**
     * @param iconType Value of field iconType to set
     */
    public final void setIconExtension( String iconType )
    {
        this.iconExtension = iconType;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setServletContext( ServletContext servletContext )
    {
        this.servletContext = servletContext;
    }

    /**
     * @param sizeThreshold Value of field sizeThreshold to set
     */
    public final void setSizeThreshold( long sizeThreshold )
    {
        this.sizeThreshold = sizeThreshold;
    }
}
