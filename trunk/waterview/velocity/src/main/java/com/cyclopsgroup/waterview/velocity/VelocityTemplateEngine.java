package com.cyclopsgroup.waterview.velocity;

import java.io.Writer;

import org.apache.velocity.app.VelocityEngine;

import com.cyclopsgroup.waterview.Context;
import com.cyclopsgroup.waterview.spi.TemplateEngine;

public class VelocityTemplateEngine
    implements TemplateEngine
{
    private VelocityEngine velocityEngine;

    public VelocityTemplateEngine( VelocityEngine velocity )
    {
        velocityEngine = velocity;
    }

    public void mergeTemplate( String templatePath, Context context, Writer output )
        throws Exception
    {
        VelocityContextAdapter velocityContext = new VelocityContextAdapter( context );
        velocityEngine.mergeTemplate( templatePath, velocityContext, output );
    }
}