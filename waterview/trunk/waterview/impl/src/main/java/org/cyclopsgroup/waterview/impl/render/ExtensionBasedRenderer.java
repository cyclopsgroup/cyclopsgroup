package org.cyclopsgroup.waterview.impl.render;

import java.io.IOException;
import java.io.Writer;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.Validate;
import org.cyclopsgroup.waterview.WebContext;
import org.cyclopsgroup.waterview.ipa.Renderer;

/**
 * Renderer based on extension
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class ExtensionBasedRenderer
    implements Renderer
{
    private final Map<String, Renderer> rendererMap;

    /**
     * @param rendererMap Map of renderers
     */
    public ExtensionBasedRenderer( Map<String, Renderer> rendererMap )
    {
        Validate.notNull( rendererMap, "Map can't be NULL" );
        Validate.isTrue( !rendererMap.isEmpty(), "Map can't be empty" );
        this.rendererMap = Collections.unmodifiableMap( new HashMap<String, Renderer>( rendererMap ) );
    }

    /**
     * @inheritDoc
     */
    @Override
    public void render( WebContext context, String template, Writer output )
        throws IOException
    {
        for ( Map.Entry<String, Renderer> entry : rendererMap.entrySet() )
        {
            if ( template.endsWith( "." + entry.getKey() ) )
            {
                entry.getValue().render( context, template, output );
                break;
            }
        }
        throw new UnresolvableTemplateException( "Template " + template + " doesn't isn't recogonized by extensions "
            + rendererMap.keySet() );
    }
}
