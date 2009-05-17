package org.cyclopsgroup.waterview.impl.render;

import java.util.List;

import org.cyclopsgroup.waterview.impl.module.PageModule;
import org.cyclopsgroup.waterview.spi.WebContext;

/**
 * Implementation of {@link PageModule} without any value
 *
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
class NullPageModule
    implements PageModule
{
    /**
     * @inheritDoc
     */
    @Override
    public String getDescription()
    {
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<String> getKeywords()
    {
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getLayout()
    {
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getTitle()
    {
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getPath()
    {
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void render( WebContext context )
    {
    }
}
