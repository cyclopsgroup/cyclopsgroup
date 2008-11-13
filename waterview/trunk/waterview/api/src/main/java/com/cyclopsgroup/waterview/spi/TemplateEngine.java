package com.cyclopsgroup.waterview.spi;

import java.io.IOException;
import java.io.Writer;

import com.cyclopsgroup.waterview.Context;

public interface TemplateEngine
{
    void mergeTemplate( String templatePath, Context context, Writer output )
        throws TemplateNotFoundException, RenderTemplateException, IOException;

    boolean templateExists( String templatePath );
}