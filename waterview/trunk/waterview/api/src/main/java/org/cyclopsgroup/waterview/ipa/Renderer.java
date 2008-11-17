package org.cyclopsgroup.waterview.ipa;

import java.io.IOException;
import java.io.Writer;

import org.cyclopsgroup.waterview.WebContext;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public interface Renderer
{
    /**
     * Merge context and template into given output
     * 
     * @param context Web context to merge
     * @param template Template to merge
     * @param output Output result
     * @throws IOException
     * @throws TemplateNotFoundException Thrown when template doesn't exist
     */
    void render( WebContext context, String template, Writer output )
        throws IOException, TemplateNotFoundException;
}
