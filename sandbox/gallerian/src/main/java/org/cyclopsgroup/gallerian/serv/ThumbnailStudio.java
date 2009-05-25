package org.cyclopsgroup.gallerian.serv;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.CountDownLatch;

import javax.imageio.ImageIO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

class ThumbnailStudio
{
    private static final Log LOG = LogFactory.getLog( ThumbnailStudio.class );

    private static class Obs
        implements ImageObserver
    {
        private final CountDownLatch latch;

        private Obs( CountDownLatch latch )
        {
            this.latch = latch;
        }

        /**
         * @inheritDoc
         */
        @Override
        public boolean imageUpdate( Image img, int infoflags, int x, int y, int width, int height )
        {
            boolean finished = ( ( infoflags & FRAMEBITS ) > 0 );
            if ( finished )
            {
                latch.countDown();
            }
            return true;
        }
    }

    private final int maxHeight = 60;

    private final int maxWidth = 60;

    InputStream openThumbnail( InputStream sourceFile, String type )
        throws IOException
    {
        LOG.info( "DISPLAY=" + System.getenv( "DISPLAY" ) );

        BufferedImage source = ImageIO.read( sourceFile );
        int width = maxWidth;
        int height = maxHeight;
        Image image = source.getScaledInstance( width, height, Image.SCALE_FAST );
        BufferedImage target = new BufferedImage( width, height, BufferedImage.TYPE_INT_RGB );
        CountDownLatch latch = new CountDownLatch( 1 );
        target.getGraphics().drawImage( image, 0, 0, new Obs( latch ) );
        try
        {
            latch.await();
        }
        catch ( InterruptedException e )
        {
            throw new RuntimeException( "Thread is interrupted", e );
        }
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        ImageIO.write( target, type, output );
        output.flush();
        return new ByteArrayInputStream( output.toByteArray() );
    }
}
