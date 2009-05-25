package org.cyclopsgroup.gallerian.serv;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.servlet.ServletContext;

import org.cyclopsgroup.gallerian.ContentId;
import org.cyclopsgroup.gallerian.ContentStreamService;
import org.cyclopsgroup.gallerian.Dimension;
import org.cyclopsgroup.gallerian.spi.FileProvider;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.context.ServletContextAware;

/**
 * Content stream service implementation based on a local listing service
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class LocalContentStreamService
    implements ContentStreamService, ServletContextAware, InitializingBean
{
    private static final String DEFAULT_ICON_EXTENSION = "png";

    private static final int DEFAULT_ICON_HEIGHT = 72;

    private static final int DEFAULT_ICON_WIDTH = 96;

    private String iconDirectory = new File( "./images" ).getAbsolutePath();

    private String iconExtension = DEFAULT_ICON_EXTENSION;

    private int iconHeight = DEFAULT_ICON_HEIGHT;

    private String iconMimeType;

    private int iconWidth = DEFAULT_ICON_WIDTH;

    private final ServerSideContentListingService listing;

    private ServletContext servletContext;

    private long sizeThreshold = 5 * 1024768L;

    private ThumbnailStudio thumbnailStudio;

    private ExecutorService threadPool = Executors.newFixedThreadPool( 5 );

    /**
     * @param listing Server side listing service
     */
    public LocalContentStreamService( ServerSideContentListingService listing )
    {
        this.listing = listing;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void afterPropertiesSet()
        throws Exception
    {
        iconMimeType = servletContext.getMimeType( "x." + iconExtension );
        thumbnailStudio = new ThumbnailStudio( iconWidth, iconHeight );
    }

    /**
     * @return Value of field iconDirectory
     */
    public final String getIconDirectory()
    {
        return iconDirectory;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getIconMimeType()
    {
        return iconMimeType;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Dimension getIconSize()
    {
        return new Dimension( iconWidth, iconHeight );
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
    public String getThumbnailMimeType()
    {
        return "image/jpeg";
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
    public InputStream openIcon( String contentType )
        throws FileNotFoundException
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
     * @inheritDoc
     */
    @Override
    public InputStream openThumbnail( ContentId id )
        throws IOException
    {
        final FileProvider file = listing.getFileProvider( id.getRepositoryName(), id.getPath() );
        Callable<InputStream> task = new Callable<InputStream>()
        {
            @Override
            public InputStream call()
                throws IOException
            {
                InputStream in = file.open();
                try
                {
                    return thumbnailStudio.openThumbnail( in, "jpeg" );
                }
                finally
                {
                    in.close();
                }
            }
        };
        Future<InputStream> future = threadPool.submit( task );
        try
        {
            return future.get();
        }
        catch ( InterruptedException e )
        {
            throw new RuntimeException( "Thread is interrupted", e );
        }
        catch ( ExecutionException e )
        {
            throw new IOException( "Couldn't open thumbnail for " + id, e );
        }
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
     * @param iconHeight Value of field iconHeght to set
     */
    public final void setIconHeight( int iconHeight )
    {
        this.iconHeight = iconHeight;
    }

    /**
     * @param iconWidth Value of field iconWidth to set
     */
    public final void setIconWidth( int iconWidth )
    {
        this.iconWidth = iconWidth;
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
