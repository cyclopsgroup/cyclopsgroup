package org.cyclopsgroup.streammarker.plain;

import java.io.Closeable;
import java.io.IOException;

import org.cyclopsgroup.streammarker.Mark;

interface StreamMarksWriter
    extends Closeable
{
    void write( String bucket, long timestamp, Iterable<Mark> marks );

    void close()
        throws IOException;
}
