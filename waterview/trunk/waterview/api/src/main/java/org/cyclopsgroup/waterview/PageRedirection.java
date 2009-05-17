package org.cyclopsgroup.waterview;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Result of action
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public abstract class PageRedirection
{
    /**
     * Internally redirect HTTP call. This method is called in the end of pipeline
     * 
     * @param request Http request
     * @param response Http response
     * @throws IOException If IO communication fails
     */
    public abstract void redirect(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
