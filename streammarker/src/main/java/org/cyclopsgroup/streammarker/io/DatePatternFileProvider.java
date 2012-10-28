package org.cyclopsgroup.streammarker.io;

import java.io.File;

import org.cyclopsgroup.streammarker.Provider;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DatePatternFileProvider
    implements Provider<File>
{
    private final File directory;

    private final String fileBasename;

    private final DateTimeFormatter suffixFormat;

    public DatePatternFileProvider( File fileBase, DateTimeFormatter format )
    {
        File base = fileBase.getAbsoluteFile();
        directory = base.getParentFile();
        if ( !directory.isDirectory() )
        {
            throw new IllegalArgumentException( "Directory " + directory + " isn't valid" );
        }
        this.fileBasename = base.getName();
        this.suffixFormat = format;
    }

    public DatePatternFileProvider( File fileBase, String format )
    {
        this( fileBase, DateTimeFormat.forPattern( format ) );
    }

    /**
     * @inheritDoc
     */
    @Override
    public File provide()
    {
        String suffix = new DateTime().toString( suffixFormat );
        return new File( directory, fileBasename + "." + suffix );
    }
}
