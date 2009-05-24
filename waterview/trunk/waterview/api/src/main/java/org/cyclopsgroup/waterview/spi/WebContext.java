package org.cyclopsgroup.waterview.spi;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cyclopsgroup.waterview.Module;
import org.cyclopsgroup.waterview.PageRedirection;

/**
 * Context of {@link Module}
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public interface WebContext
{
    /**
     * @return Path of current servlet context
     */
    String getContextPath();

    /**
     * @return Redirecion that redirects result to somewhere else
     */
    PageRedirection getRedirection();

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
     * @return Set of variable names
     */
    Set<String> getVariableNames();

    /**
     * @param redirection Redirecte result to somewhere else
     */
    void setRedirection( PageRedirection redirection );
    
    /**
     * Set variable for module context
     * 
     * @param name Name of variable
     * @param value Value of variable
     * @return Previous value of variable if there is a previous value
     */
    Object setVariable( String name, Object value );
}
