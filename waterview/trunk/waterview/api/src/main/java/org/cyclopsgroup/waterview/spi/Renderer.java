package org.cyclopsgroup.waterview.spi;

import java.io.IOException;
import java.io.Writer;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public interface Renderer
{
    /**
     * Verify if template path is accepted by this renderer
     * 
     * @param template Path of template
     * @return True if template is acceptable
     */
    boolean acceptTemplate(String template);
    
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
