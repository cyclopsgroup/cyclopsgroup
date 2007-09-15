package com.cyclopsgroup.laputa.portal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.Template;
import org.apache.velocity.tools.view.ServletUtils;
import org.apache.velocity.tools.view.VelocityLayoutServlet;

public class VelocityTemplateLayoutServlet
    extends VelocityLayoutServlet
{
    private static final long serialVersionUID = 1L;

    @Override
    protected Template getTemplate( HttpServletRequest request, HttpServletResponse response )
    {
        String path = ServletUtils.getPath( request );
        return getTemplate( "view" + path );
    }
}
