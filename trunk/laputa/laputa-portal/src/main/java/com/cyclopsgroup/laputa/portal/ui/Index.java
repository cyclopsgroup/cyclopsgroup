package com.cyclopsgroup.laputa.portal.ui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class Index
    extends Action
{
    private String name;

    public Index( String name )
    {
        this.name = name;
    }

    @Override
    public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response )
        throws Exception
    {
        request.setAttribute( "magicName", name );
        System.out.println( name );
        return mapping.findForward( "success" );
    }
}
