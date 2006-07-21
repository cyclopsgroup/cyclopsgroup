/* ==========================================================================
 * Copyright 2002-2005 Cyclops Group Community
 *
 * Licensed under the Open Software License, Version 2.1 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://opensource.org/licenses/osl-2.1.php
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * =========================================================================
 */
package com.cyclopsgroup.waterview;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.lang.StringUtils;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Link tool
 */
public class Link
{
    /** Encoding for parameters */
    public static final String ENCODING = "UTF-8";

    public static final String INSTRUCTION_DISPLAY = "display";

    public static final String INSTRUCTION_DO = "do";

    public static final String INSTRUCTION_GET = "get";

    /** Name of this tool */
    private static final String NAME = "link";

    /**
     * Get instance from runtime data
     *
     * @param data Runtime data
     * @return Link object
     */
    public synchronized static Link getInstance( RunData data )
    {
        Link link = (Link) data.getRequestContext().get( Link.NAME );
        if ( link == null )
        {
            link = new Link( data );
            data.getRequestContext().put( Link.NAME, link );
        }
        return link;
    }

    private RunData data;

    private boolean disposed = false;

    private StringBuffer queryString;

    private StringBuffer requestPath = new StringBuffer();

    /**
     * Constructor for type LinkTool
     *
     * @param data Runtime data
     */
    protected Link( RunData data )
    {
        this.data = data;
    }

    /**
     * Add action for current link
     *
     * @param action Action path
     * @return Link itself
     */
    public Link addAction( String action )
    {
        return addPath( INSTRUCTION_DO, action );
    }

    /**
     * Add arbituary path with given instruction
     * 
     * @param instruction Instruction name
     * @param path Path to add
     * @return Link itself
     */
    public Link addPath( String instruction, String path )
    {
        checkDisposed();
        requestPath.append( "/!" ).append( instruction ).append( "!" ).append( path );
        return this;
    }

    /**
     * Add query parameter
     *
     * @param name Name of parameter
     * @param value Value of parameter
     * @return Link itself
     * @throws UnsupportedEncodingException Throw it out
     */
    public Link addQueryData( String name, Object value )
        throws UnsupportedEncodingException
    {
        if ( queryString == null )
        {
            queryString = new StringBuffer();
        }
        else
        {
            queryString.append( '&' );
        }
        String v = value == null ? StringUtils.EMPTY : value.toString();
        queryString.append( name ).append( '=' ).append( URLEncoder.encode( v, ENCODING ) );
        return this;
    }

    /**
     * Add whole query string
     *
     * @param string Whole query string
     * @return Query string
     */
    public Link addQueryString( String string )
    {
        if ( StringUtils.isEmpty( string ) )
        {
            return this;
        }
        if ( queryString == null )
        {
            queryString = new StringBuffer( string );
        }
        else
        {
            queryString.append( '&' ).append( string );
        }
        return this;
    }

    private void checkDisposed()
    {
        if ( disposed )
        {
            queryString = null;
            requestPath = new StringBuffer();
            disposed = false;
        }
    }

    private String getPath( String path )
    {
        if ( StringUtils.isEmpty( path ) )
        {
            return null;
        }
        if ( path.charAt( 0 ) == '/' )
        {
            return path;
        }
        String currentPage = data.getPage().getFullPath();
        int lastSlash = currentPage.lastIndexOf( '/' );
        if ( lastSlash == -1 )
        {
            return '/' + path;
        }
        return currentPage.substring( 0, lastSlash + 1 ) + path;
    }

    /**
     * Get resource path
     *
     * @param path Get resource path
     * @return Full resource path
     */
    public String getResource( String path )
    {
        StringBuffer sb = new StringBuffer( data.getPageBaseUrl() );
        sb.append( "/!" ).append( INSTRUCTION_GET ).append( '!' );
        sb.append( getPath( path ) );
        return sb.toString();
    }

    /**
     * Set page with given page path
     *
     * @param path Path object
     * @return It self
     */
    public Link setPage( Path path )
    {
        setPage( path.getFullPath() );
        return this;
    }

    /**
     * Set page
     *
     * @param path
     * @return Link tool itself
     */
    public Link setPage( String path )
    {
        return addPath( INSTRUCTION_DISPLAY, path );
    }

    /**
     * Override method LinkTool in supper class
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        StringBuffer url = new StringBuffer( data.getPageBaseUrl() ).append( requestPath );
        if ( queryString != null )
        {
            url.append( '?' ).append( queryString );
        }
        disposed = true;
        return url.toString();
    }
}
