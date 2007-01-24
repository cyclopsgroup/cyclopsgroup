package com.cyclopsgroup.waterview;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface WebUIToolManager
{
    String ROLE = WebUIToolManager.class.getName();

    /**
     * Get instance of a tool
     * 
     * @param toolName Name of tool
     * @param servlet Current servlet
     * @param req Http request
     * @param reps Http response
     * @return Tool instance
     * @throws Exception
     */
    Object getTool( String toolName, HttpServlet servlet, HttpServletRequest req, HttpServletResponse reps );
}
