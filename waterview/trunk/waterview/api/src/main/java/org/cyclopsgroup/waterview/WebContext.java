package org.cyclopsgroup.waterview;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Context of {@link WebModule}
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public interface WebContext
{
    /**
     * @return Redirecion that redirects result to somewhere else
     */
    Redirection getRedirection();

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
     * @param redirection Redirecte result to somewhere else
     */
    void setRedirection( Redirection redirection );

    /**
     * Set variable for module context
     * 
     * @param name Name of variable
     * @param value Value of variable
     * @return Previous value of variable if there is a previous value
     */
    Object setVariable( String name, Object value );
}
