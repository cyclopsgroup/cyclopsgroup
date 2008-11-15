package org.cyclopsgroup.waterview.impl.render;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.Validate;
import org.cyclopsgroup.waterview.Redirection;
import org.cyclopsgroup.waterview.WebContext;

/**
 * Child context that wraps given parent
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class ChildWebContext
    implements WebContext
{
    private final Map<String, Object> variables = new HashMap<String, Object>();

    private final WebContext parent;

    /**
     * @param parent Parent context
     */
    public ChildWebContext( WebContext parent )
    {
        Validate.notNull( parent, "Parent context can't be NULL" );
        this.parent = parent;
    }

    /**
     * @inheritDoc
     */
    @Override
    public HttpServletRequest getServletRequest()
    {
        return parent.getServletRequest();
    }

    /**
     * @inheritDoc
     */
    @Override
    public HttpServletResponse getServletResponse()
    {
        return parent.getServletResponse();
    }

    /**
     * @inheritDoc
     */
    @Override
    public Object getVariable( String name )
    {
        Object value = variables.get( name );
        return value == null ? parent.getVariable( name ) : value;
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

    /**
     * @inheritDoc
     */
    @Override
    public Redirection getRedirection()
    {
        return parent.getRedirection();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setRedirection( Redirection redirection )
    {
        parent.setRedirection( redirection );
    }

}
