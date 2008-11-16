package org.cyclopsgroup.waterview.impl.render;

import org.cyclopsgroup.waterview.Page;
import org.cyclopsgroup.waterview.WebModule;

public class RuntimePage
    extends RuntimeView
{
    public static final String PAGE_NAME = "page";

    private final Page annotation;

    public RuntimePage( String path, WebModule module )
    {
        super( path, module );
        if ( module == null )
        {
            this.annotation = null;
        }
        else
        {
            this.annotation = module.getClass().getAnnotation( Page.class );
        }
    }

    public String getDescription()
    {
        return annotation == null ? null : annotation.description();
    }

    public String[] getKeywords()
    {
        return annotation == null ? null : annotation.keywords();
    }

    public String getTitle()
    {
        return annotation == null ? null : annotation.title();
    }
}
