package org.cyclopsgroup.streammarker;

import java.io.IOException;

public interface MarkerBuilder
{
    Marker build()
        throws IOException;
}
