package org.cyclopsgroup.jcli.impl;

import org.cyclopsgroup.jcli.annotation.Option;

class AnnotationOption
    implements org.cyclopsgroup.jcli.spi.Option
{
    private final boolean flag;

    private final Option option;

    AnnotationOption( Option option, boolean flag )
    {
        this.option = option;
        this.flag = flag;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getDefaultValue()
    {
        return option.defaultValue();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getDescription()
    {
        return option.description();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getDisplayName()
    {
        return option.displayName();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getLongName()
    {
        return option.longName();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getName()
    {
        return option.name();
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isFlag()
    {
        return flag;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isRequired()
    {
        return option.required();
    }
}
