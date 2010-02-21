package org.cyclopsgroup.jcli.impl;

import org.cyclopsgroup.jcli.annotation.Option;

class AnnotationOption
    implements org.cyclopsgroup.jcli.spi.Option
{
    private final Option option;

    private final boolean flag;

    AnnotationOption( Option option, boolean flag )
    {
        this.option = option;
        this.flag = flag;
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
}
