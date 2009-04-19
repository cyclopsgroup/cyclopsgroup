package org.cyclopsgroup.waterview.impl.servlet;

import javax.servlet.ServletContext;

import org.cyclopsgroup.waterview.impl.velocity.VelocityRendererFactory;
import org.springframework.web.context.ServletContextAware;

/**
 * This special {@link VelocityRendererFactory} sets template path to be the root of web application, the value returned
 * by {@link ServletContext#getRealPath(String)}.
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class ServletVelocityRendererFactory
    extends VelocityRendererFactory
    implements ServletContextAware
{
    /**
     * @inheritDoc
     */
    @Override
    public void setServletContext( ServletContext context )
    {
        setTemplatePath( context.getRealPath( "" ) );
    }
}
