package org.cyclopsgroup.waterview.impl.valve;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.Validate;
import org.cyclopsgroup.waterview.WebContext;

public class ChildWebContext
    implements WebContext
{
    private final Map<String, Object> variables = new HashMap<String, Object>();

    private final WebContext parent;

    public ChildWebContext( WebContext parent )
    {
        Validate.notNull( parent, "Parent context can't be NULL" );
        this.parent = parent;
    }

    @Override
    public HttpServletRequest getServletRequest()
    {
        return parent.getServletRequest();
    }

    @Override
    public HttpServletResponse getServletResponse()
    {
        return parent.getServletResponse();
    }

    @Override
    public Object getVariable( String name )
    {
        Object value = variables.get( name );
        return value == null ? parent.getVariable( name ) : value;
    }

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
