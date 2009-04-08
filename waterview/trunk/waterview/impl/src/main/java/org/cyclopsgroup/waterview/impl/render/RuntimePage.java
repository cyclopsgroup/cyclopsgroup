package org.cyclopsgroup.waterview.impl.render;

import java.util.List;

import org.cyclopsgroup.waterview.impl.module.PageModule;
import org.cyclopsgroup.waterview.impl.module.WebModule;

/**
 * Page pojo used at runtime for rendering
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class RuntimePage
    extends RuntimeView
{
    private static final PageModule NULL = new NullPageModule();
    /**
     * Variable name of this runtime page
     */
    public static final String PAGE_NAME = "page";

    private final PageModule page;

    /**
     * @param path Requested path
     * @param module Module attached to it
     */
    public RuntimePage( String path, WebModule module )
    {
        super( path, module );
        page = ( module instanceof PageModule) ? (PageModule)module: NULL;
    }

    /**
     * @return Page description
     */
    public String getDescription()
    {
        return page.getDescription();
    }

    /**
     * @return Page keywords
     */
    public List<String> getKeywords()
    {
        return page.getKeywords();
    }

    /**
     * @return Page title
     */
    public String getTitle()
    {
        return page.getTitle();
    }
}
