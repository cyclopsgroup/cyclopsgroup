package org.cyclopsgroup.waterview.impl.velocity;

import java.io.IOException;
import java.io.Writer;
import java.nio.charset.Charset;

import org.apache.commons.lang.Validate;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.cyclopsgroup.waterview.WebContext;
import org.cyclopsgroup.waterview.ipa.Renderer;
import org.cyclopsgroup.waterview.ipa.TemplateNotFoundException;

/**
 * Velocity implementation of renderer
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class VelocityRenderer
    implements Renderer
{
    private final VelocityEngine velocityEngine;

    private final String encoding;

    /**
     * @param velocityEngine Velocity engine
     * @param encoding Output encoding
     */
    public VelocityRenderer( VelocityEngine velocityEngine, Charset encoding )
    {
        Validate.notNull( encoding, "Encoding can't be NULL" );
        Validate.notNull( velocityEngine, "Velocity engine can't be NULL" );
        this.encoding = encoding.name();
        this.velocityEngine = velocityEngine;
    }

    /**
     * @param velocityEngine VelocityEngine instance
     */
    public VelocityRenderer( VelocityEngine velocityEngine )
    {
        this( velocityEngine, Charset.forName( "UTF-8" ) );
    }

    /**
     * @inheritDoc
     */
    @Override
    public void render( WebContext context, String template, Writer output )
        throws IOException
    {
        try
        {
            velocityEngine.mergeTemplate( template, encoding, new VelocityContextAdapter( context ), output );
        }
        catch ( ResourceNotFoundException e )
        {
            throw new TemplateNotFoundException( "Template not found", e, template );
        }
        catch ( IOException e )
        {
            throw e;
        }
        catch ( RuntimeException e )
        {
            throw e;
        }
        catch ( Exception e )
        {
            throw new RuntimeException( "Velocity template merging failed", e );
        }
    }
}
