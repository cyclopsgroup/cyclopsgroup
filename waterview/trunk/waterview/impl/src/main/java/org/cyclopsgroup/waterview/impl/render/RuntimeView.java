package org.cyclopsgroup.waterview.impl.render;

import org.cyclopsgroup.waterview.WebModule;

/**
 * Runtime view POJO used for view rendering
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class RuntimeView
{
    private final WebModule module;

    private final String path;

    /**
     * @param path Path of view
     * @param module Attached module
     */
    public RuntimeView( String path, WebModule module )
    {
        this.path = path;
        this.module = module;
    }

    /**
     * @return Attached module
     */
    public WebModule getModule()
    {
        return module;
    }

    /**
     * @return Path of view
     */
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
