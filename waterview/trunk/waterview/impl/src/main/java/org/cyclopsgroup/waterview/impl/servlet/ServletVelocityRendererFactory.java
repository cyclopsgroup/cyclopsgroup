package org.cyclopsgroup.waterview.impl.servlet;

import javax.servlet.ServletContext;

import org.cyclopsgroup.waterview.impl.velocity.VelocityRendererFactory;
import org.springframework.web.context.ServletContextAware;

/**
 * Velocity engine that takes servlet root path as template path
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class ServletVelocityRendererFactory
    extends VelocityRendererFactory implements ServletContextAware
{
    /**
     * @inheritDoc
     */
    @Override
    public void setServletContext( ServletContext context )
    {
        setTemplatePath( context.getRealPath( "" ));
    }
}
