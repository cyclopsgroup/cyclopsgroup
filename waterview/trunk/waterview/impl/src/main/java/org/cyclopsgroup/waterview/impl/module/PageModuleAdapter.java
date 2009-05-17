package org.cyclopsgroup.waterview.impl.module;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import org.cyclopsgroup.waterview.Page;

/**
 * Adapter for {@link PageModule}. It takes a POJO annotated with {@link Page} annotation and expose {@link PageModule}
 * interface.
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
class PageModuleAdapter
    extends WebModuleAdapter
    implements PageModule
{
    private final Page page;

    /**
     * @param module User defined module
     * @param method Method to invoke upon
     */
    PageModuleAdapter( Object module, Method method )
    {
        super( module, method );
        page = method.getAnnotation( Page.class );
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getDescription()
    {
        return page.description();
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<String> getKeywords()
    {
        return Arrays.asList( page.keywords() );
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getLayout()
    {
        return page.layout();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getTitle()
    {
        return page.title();
    }
}
