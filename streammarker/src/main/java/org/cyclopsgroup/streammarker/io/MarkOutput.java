package org.cyclopsgroup.streammarker.io;

import java.io.IOException;

import org.cyclopsgroup.streammarker.Application;
import org.cyclopsgroup.streammarker.Mark;

public interface MarkOutput
{
    void writeHeader( String fileId, Application application )
        throws IOException;

    void writeBody( String bucket, Iterable<Mark> marks, long timestamp, Application application )
        throws IOException;
}
