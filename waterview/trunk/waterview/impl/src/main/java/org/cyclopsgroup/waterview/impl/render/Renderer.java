package org.cyclopsgroup.waterview.impl.render;

import java.io.IOException;
import java.io.Writer;

public interface Renderer
{
    void render( Writer output, String template )
        throws IOException;
}
