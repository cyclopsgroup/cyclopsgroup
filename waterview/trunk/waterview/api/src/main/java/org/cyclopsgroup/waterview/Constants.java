package org.cyclopsgroup.waterview;

/**
 * Constants used in Waterview cross API and implementation
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public final class Constants
{
    /**
     * Name of web context itself
     */
    public static final String CONTEXT = "context";
    
    /**
     * Default output MIME type filled in content type header
     */
    public static final String DEFAULT_CONTENT_TYPE = "text/html;charset=UTF-8";
    
    /**
     * Name of HTTP servlet config
     */
    public static final String SERVLET_CONFIG = "servletConfig";

    /**
     * Name of HTTP servlet context variable
     */
    public static final String SERVLET_CONTEXT = "servletContext";
    
    /**
     * Name of HTTP request variable
     */
    public static final String SERVLET_REQUEST = "servletRequest";
    
    /**
     * Name of HTTP response variable
     */
    public static final String SERVLET_RESPONSE = "servletResponse";
    
    private Constants() {}
}
