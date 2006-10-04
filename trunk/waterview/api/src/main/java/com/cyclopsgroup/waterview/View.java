package com.cyclopsgroup.waterview;

import java.io.IOException;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public interface View
{
    void render( ViewContext viewContext )
        throws IOException;
}
