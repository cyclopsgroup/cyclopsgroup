package org.cyclopsgroup.waterview.impl.render;

import org.cyclopsgroup.waterview.Page;
import org.cyclopsgroup.waterview.WebModule;

/**
 * Page pojo used at runtime for rendering
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class RuntimePage
    extends RuntimeView
{
    /**
     * Variable name of this runtime page
     */
    public static final String PAGE_NAME = "page";

    private final Page annotation;

    /**
     * @param path Requested path
     * @param module Module attached to it
     */
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

    /**
     * @return Page description
     */
    public String getDescription()
    {
        return annotation == null ? null : annotation.description();
    }

    /**
     * @return Page keywords
     */
    public String[] getKeywords()
    {
        return annotation == null ? null : annotation.keywords();
    }

    /**
     * @return Page title
     */
    public String getTitle()
    {
        return annotation == null ? null : annotation.title();
    }
}
