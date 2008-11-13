package org.cyclopsgroup.waterview;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Context of {@link Module}
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public interface ModuleContext
{
    /**
     * @return Http servlet request
     */
    HttpServletRequest getServletRequest();

    /**
     * @return Http servlet response
     */
    HttpServletResponse getServletResponse();
}
