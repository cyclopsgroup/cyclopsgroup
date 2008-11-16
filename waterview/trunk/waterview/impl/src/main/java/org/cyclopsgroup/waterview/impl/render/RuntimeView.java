package org.cyclopsgroup.waterview.impl.render;

import org.cyclopsgroup.waterview.WebModule;

public class RuntimeView
{
    private final WebModule module;

    private final String path;

    public RuntimeView( String path, WebModule module )
    {
        this.path = path;
        this.module = module;
    }

    public WebModule getModule()
    {
        return module;
    }

    public String getPath()
    {
        return path;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString()
    {
        return getPath();
    }
}
