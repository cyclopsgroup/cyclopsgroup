package org.cyclopsgroup.jcli.impl;

import org.cyclopsgroup.jcli.annotation.Cli;

/**
 * Annotation based Cli implementation
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
class AnnotationCli
    implements org.cyclopsgroup.jcli.spi.Cli
{
    private final Cli cli;

    /**
     * @param cli Annotation cli
     */
    AnnotationCli( Cli cli )
    {
        this.cli = cli;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getDescription()
    {
        return cli.description();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getName()
    {
        return cli.name();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getNote()
    {
        return cli.note();
    }
}
