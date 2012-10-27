package org.cyclopsgroup.streammarker.io;

import java.io.IOException;

import org.cyclopsgroup.streammarker.Mark;
import org.cyclopsgroup.streammarker.plain.Application;

public interface MarkOutput
{
    void writeHeader( Application application )
        throws IOException;

    void writeFooter( int lines, Application application )
        throws IOException;

    void writeBody( String bucket, Iterable<Mark> marks, long timestamp, int index, Application application )
        throws IOException;
}
