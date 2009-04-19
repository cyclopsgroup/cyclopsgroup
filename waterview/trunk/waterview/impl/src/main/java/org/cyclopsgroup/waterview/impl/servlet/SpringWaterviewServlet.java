package org.cyclopsgroup.waterview.impl.servlet;

import org.cyclopsgroup.waterview.impl.pipeline.WebContextProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Main servlet for waterview
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class SpringWaterviewServlet
    extends AbstractWaterviewServlet
{
    private static final String BEAN_NAME_PARAM = "contextProcessorBean";

    private static final String DEFAULT_BEAN_NAME = "waterview.ContextProcessor";

    private static final long serialVersionUID = 1L;

    /**
     * @inheritDoc
     */
    @Override
    protected WebContextProcessor createWebContextProcessor()
    {
        String beanName = this.getServletConfig().getInitParameter( BEAN_NAME_PARAM);
        if(beanName == null) {
            beanName = DEFAULT_BEAN_NAME;
        }
        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext( getServletContext() );
        return (WebContextProcessor) context.getBean( beanName );
    }
}
