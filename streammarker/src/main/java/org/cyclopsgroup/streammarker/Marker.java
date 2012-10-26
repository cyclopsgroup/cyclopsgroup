package org.cyclopsgroup.streammarker;

public interface Marker
{
    void draw( String bucket, Mark... marks );

    void draw( String bucket, Iterable<Mark> marks );
}
