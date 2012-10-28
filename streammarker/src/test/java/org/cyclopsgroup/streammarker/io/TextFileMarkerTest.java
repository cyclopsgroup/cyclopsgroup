package org.cyclopsgroup.streammarker.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.SystemUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cyclopsgroup.streammarker.Application;
import org.cyclopsgroup.streammarker.ConstantProvider;
import org.cyclopsgroup.streammarker.Mark;
import org.cyclopsgroup.streammarker.Marker;
import org.cyclopsgroup.streammarker.Provider;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TextFileMarkerTest
{
    private static final Log LOG = LogFactory.getLog( TextFileMarkerTest.class );

    private File workingDirectory;

    private void drawAndVerify( boolean blocking )
        throws IOException
    {
        Application app = new Application();
        app.setApplicationName( "UnitTest" );

        File outputFile = new File( workingDirectory, "test.log" );
        Marker marker = new TextFileMarker( ConstantProvider.of( outputFile ), app, blocking );
        marker.draw( "aMetric", Mark.count( "n1" ), Mark.count( "n2", 10 ) );

        ( (Closeable) marker ).close();

        assertTrue( "Expected result file " + outputFile + " didn't appear", outputFile.exists() );
        assertEquals( "Expect 2 lines in result file " + outputFile, 2, FileUtils.readLines( outputFile ).size() );
    }

    @Before
    public void setUpWorkingDirectory()
    {
        workingDirectory =
            new File( SystemUtils.JAVA_IO_TMPDIR + "/" + getClass().getSimpleName() + "-" + UUID.randomUUID() );
        workingDirectory.mkdirs();
        LOG.info( "Directory " + workingDirectory + " is created" );
    }

    @After
    public void tearDownWorkingDirectory()
        throws IOException
    {
        FileUtils.deleteDirectory( workingDirectory );
        LOG.info( "Directory " + workingDirectory + " is deleted" );
    }

    @Test
    public void testBlockingDraw()
        throws IOException
    {
        drawAndVerify( true );
    }

    @Test
    public void testFileRotation()
        throws IOException, InterruptedException
    {
        Application app = new Application();
        app.setApplicationName( "UnitTest" );

        final AtomicReference<String> fileName = new AtomicReference<String>();
        Marker marker = new TextFileMarker( new Provider<File>()
        {
            public File provide()
            {
                return new File( workingDirectory, fileName.get() );
            }
        }, app, true );

        fileName.set( "f1" );
        marker.draw( "aMetric", Mark.count( "n1" ) );
        fileName.set( "f2" );

        Thread.sleep( 1100L ); // Cause changes to flush and realize rotation
        marker.draw( "aMetric", Mark.count( "n1" ) );
        ( (Closeable) marker ).close();

        File f1 = new File( workingDirectory, "f1" );
        File f2 = new File( workingDirectory, "f2" );
        assertTrue( "f1 should exist", f1.exists() );
        assertTrue( "f2 should exist", f2.exists() );

        assertEquals( "Expect 2 lines in result file " + f1, 2, FileUtils.readLines( f1 ).size() );
        assertEquals( "Expect 2 lines in result file " + f2, 2, FileUtils.readLines( f2 ).size() );
    }

    @Test
    public void testNonBlockingDraw()
        throws IOException
    {
        drawAndVerify( false );
    }
}
