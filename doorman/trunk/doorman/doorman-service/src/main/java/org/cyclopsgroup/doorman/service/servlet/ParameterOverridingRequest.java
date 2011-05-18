package org.cyclopsgroup.doorman.service.servlet;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.collections.iterators.IteratorEnumeration;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

class ParameterOverridingRequest
    extends HttpServletRequestWrapper
{
    private Map<String, String> parameters = new HashMap<String, String>();

    ParameterOverridingRequest( HttpServletRequest request )
    {
        super( request );
        String requestedPath =
            StringUtils.trimToEmpty( request.getServletPath() ) + StringUtils.trimToEmpty( request.getPathInfo() );
        setAttribute( "requestedPath", requestedPath );
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getParameter( String name )
    {
        return parameters.get( name );
    }

    /**
     * @inheritDoc
     */
    @SuppressWarnings( "rawtypes" )
    @Override
    public Map getParameterMap()
    {
        return Collections.unmodifiableMap( parameters );
    }

    /**
     * @inheritDoc
     */
    @SuppressWarnings( "rawtypes" )
    @Override
    public Enumeration getParameterNames()
    {
        return new IteratorEnumeration( parameters.keySet().iterator() );
    }

    /**
     * @inheritDoc
     */
    @Override
    public String[] getParameterValues( String name )
    {
        String value = parameters.get( name );
        if ( value == null )
        {
            return ArrayUtils.EMPTY_STRING_ARRAY;
        }
        return new String[] { value };
    }

    public void setParameter( String name, String value )
    {
        parameters.put( name, value );
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString( this );
    }
}
