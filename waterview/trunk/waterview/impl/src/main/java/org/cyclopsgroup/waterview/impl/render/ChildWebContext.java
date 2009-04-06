package org.cyclopsgroup.waterview.impl.render;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.ToStringBuilder;
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
    private final WebContext parent;

    private final String path;

    private final Map<String, Object> variables = new HashMap<String, Object>();

    /**
     * @param parent Parent context
     */
    public ChildWebContext( WebContext parent, String path )
    {
        Validate.notNull( parent, "Parent context can't be NULL" );
        this.parent = parent;
        this.path = path;
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
    public Set<String> getVariableNames()
    {
        Set<String> names = new HashSet<String>( variables.keySet() );
        names.addAll( parent.getVariableNames() );
        return names;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setRedirection( Redirection redirection )
    {
        parent.setRedirection( redirection );
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
    public String toString()
    {
        return new ToStringBuilder( this ).append( "path", path ).append( "parent", parent ).toString();
    }

}
