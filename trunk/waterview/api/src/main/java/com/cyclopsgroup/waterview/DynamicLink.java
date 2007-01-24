package com.cyclopsgroup.waterview;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class DynamicLink
{
    private final String applicationBase;

    private String encoding = "UTF-8";

    private StringBuffer queryString;

    public DynamicLink( final String applicationBase )
    {
        this.applicationBase = applicationBase;
    }

    public DynamicLink addAction( String action )
    {
        return this;
    }

    public DynamicLink addQueryData( String name, Object value )
        throws UnsupportedEncodingException
    {
        String valueString = value == null ? "" : URLEncoder.encode( value.toString(), encoding );
        if ( queryString == null )
        {
            queryString = new StringBuffer( name ).append( '=' ).append( valueString );
        }
        else
        {
            queryString.append( '&' ).append( name ).append( '=' ).append( valueString );
        }
        return this;
    }

    private void clear()
    {
        queryString = null;
    }

    public String getApplicationBase()
    {
        return applicationBase;
    }

    public StringBuffer getQueryString()
    {
        return queryString;
    }

    public void getResourceUrl( String resourcePath )
    {

    }

    public DynamicLink setPage( String pagePath )
    {
        return this;
    }

    @Override
    public String toString()
    {
        StringBuffer sb = new StringBuffer( applicationBase );
        clear();
        return sb.toString();
    }
}
