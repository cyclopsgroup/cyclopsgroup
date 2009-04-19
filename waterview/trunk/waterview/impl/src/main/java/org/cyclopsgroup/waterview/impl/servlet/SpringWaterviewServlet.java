package org.cyclopsgroup.waterview.impl.servlet;

import org.cyclopsgroup.waterview.impl.pipeline.WebContextProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * This concrete implementation of waterview servlet assume spring {@link ApplicationContext} is initialized by spring
 * servlet listener. With the context, it finds an implementation of {@link WebContextProcessor} with configured bean
 * name. If bean name is not configured, defualt name is {@value #DEFAULT_BEAN_NAME}. The name of init parameter of
 * servlet is {@value #BEAN_NAME_PARAM}. As an example, following segment of web.xml defines a waterview servlet with
 * build-in simple processor.
 * 
 * <pre>
 *   &lt;listener&gt;
 *       &lt;display-name&gt;SpringContextListener&lt;/display-name&gt;
 *       &lt;listener-class&gt;
 *           org.springframework.web.context.ContextLoaderListener&lt;/listener-class&gt;
 *   &lt;/listener&gt;
 *   &lt;servlet&gt;
 *       &lt;servlet-name&gt;waterview&lt;/servlet-name&gt;
 *       &lt;servlet-class&gt;
 *           org.cyclopsgroup.waterview.impl.servlet.SpringWaterviewServlet&lt;/servlet-class&gt;
 *       &lt;init-param&gt;
 *           &lt;param-name&gt;contextProcessorBean&lt;/param-name&gt;
 *           &lt;param-value&gt;waterview.SimpleContextProcessor&lt;/param-value&gt;
 *       &lt;/init-param&gt;
 *       &lt;load-on-startup&gt;1&lt;/load-on-startup&gt;
 *   &lt;/servlet&gt;
 * </pre>
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
        String beanName = this.getServletConfig().getInitParameter( BEAN_NAME_PARAM );
        if ( beanName == null )
        {
            beanName = DEFAULT_BEAN_NAME;
        }
        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext( getServletContext() );
        return (WebContextProcessor) context.getBean( beanName );
    }
}
