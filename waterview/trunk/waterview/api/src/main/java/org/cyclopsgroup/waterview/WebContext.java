package org.cyclopsgroup.waterview;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Context of {@link RenderableModule}
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public interface WebContext
{
    /**
     * @return Http servlet request
     */
    HttpServletRequest getServletRequest();

    /**
     * @return Http servlet response
     */
    HttpServletResponse getServletResponse();

    /**
     * Get value of a variable
     * 
     * @param name Name of variable
     * @return Value of variable
     */
    Object getVariable( String name );

    /**
     * Set variable for module context
     * 
     * @param name Name of variable
     * @param value Value of variable
     * @return Previous value of variable if there is a previous value
     */
    Object setVariable( String name, Object value );
}
