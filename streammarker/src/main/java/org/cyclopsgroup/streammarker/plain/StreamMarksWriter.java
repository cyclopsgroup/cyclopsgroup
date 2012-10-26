package org.cyclopsgroup.streammarker.plain;

import java.io.IOException;

import org.cyclopsgroup.streammarker.Mark;

interface StreamMarksWriter
{
    void write( String bucket, long timestamp, Iterable<Mark> marks )
        throws IOException;
}
