package org.cyclopsgroup.waterview.impl.module;

import java.util.Arrays;
import java.util.List;

import org.cyclopsgroup.waterview.Page;

/**
 * Adapter for {@link PageModule}
 *
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
class PageModuleAdapter
    extends WebModuleAdapter
    implements PageModule
{
    private final Page page;

    PageModuleAdapter( Object module )
    {
        super( module );
        page = module.getClass().getAnnotation( Page.class );
        if ( page == null )
        {
            throw new IllegalArgumentException( "Module " + module + " is not annotated with " + Page.class );
        }
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
