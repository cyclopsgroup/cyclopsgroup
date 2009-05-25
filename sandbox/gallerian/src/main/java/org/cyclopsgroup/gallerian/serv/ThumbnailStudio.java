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

class ThumbnailStudio
{
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

    private final int maxHeight;

    private final int maxWidth;

    ThumbnailStudio( int maxHeight, int maxWidth )
    {
        this.maxHeight = maxHeight;
        this.maxWidth = maxWidth;
    }

    InputStream openThumbnail( InputStream sourceFile, String type )
        throws IOException
    {
        BufferedImage source = ImageIO.read( sourceFile );
        int width = maxWidth;
        int height = maxHeight;
        Image image = source.getScaledInstance( width, height, Image.SCALE_SMOOTH );
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
        byte[] bytes = output.toByteArray();
        return new ByteArrayInputStream( bytes );
    }
}
