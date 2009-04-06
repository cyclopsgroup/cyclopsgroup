package org.cyclopsgroup.waterview.impl.servlet;

import org.cyclopsgroup.waterview.impl.assembly.DefaultWebContextProcessor;
import org.cyclopsgroup.waterview.impl.assembly.WebContextProcessor;
import org.cyclopsgroup.waterview.spi.ComponentResolver;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Main servlet for waterview
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class SpringWaterviewServlet
    extends AbstractWaterviewServlet
{
    private static final long serialVersionUID = 1L;

    /**
     * @inheritDoc
     */
    @Override
    protected WebContextProcessor createWebContextProcessor()
    {
        String templatePath = getServletContext().getRealPath( "" );
        ComponentResolver componentResolver =
            new SpringComponentResolver( WebApplicationContextUtils.getWebApplicationContext( getServletContext() ) );
        return new DefaultWebContextProcessor( templatePath, componentResolver );
    }
}
