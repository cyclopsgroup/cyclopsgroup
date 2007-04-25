package com.cyclopsgroup.laputa.portal.ui.my;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.cyclopsgroup.laputa.portal.Constants;

public class MyPages
    extends Action
{
    @Override
    public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response )
        throws Exception
    {
        long userId = -1;

        return mapping.findForward( Constants.FORWARD_OK );
    }
}
