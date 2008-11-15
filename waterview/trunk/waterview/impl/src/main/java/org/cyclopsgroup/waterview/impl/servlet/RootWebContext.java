package org.cyclopsgroup.waterview.impl.servlet;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.Validate;
import org.cyclopsgroup.waterview.WebContext;

/**
 * Class is not thread safe
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class RootWebContext
    implements WebContext
{
    private final HttpServletRequest servletRequest;

    private final HttpServletResponse servletResponse;

    private final Map<String, Object> variables = new HashMap<String, Object>();

    public RootWebContext( HttpServletRequest servletRequest, HttpServletResponse servletResponse )
    {
        Validate.notNull( servletRequest, "Servlet request can't be NULL" );
        Validate.notNull( servletResponse, "Servlet response can't be NULL" );
        this.servletRequest = servletRequest;
        this.servletResponse = servletResponse;
    }

    /**
     * @inheritDoc
     */
    @Override
    public HttpServletRequest getServletRequest()
    {
        return servletRequest;
    }

    /**
     * @inheritDoc
     */
    @Override
    public HttpServletResponse getServletResponse()
    {
        return servletResponse;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Object getVariable( String name )
    {
        return variables.get( name );
    }

    /**
     * @inheritDoc
     */
    @Override
    public Object setVariable( String name, Object value )
    {
        if ( value == null )
        {
            return variables.remove( name );
        }
        else
        {
            return variables.put( name, value );
        }
    }
}
