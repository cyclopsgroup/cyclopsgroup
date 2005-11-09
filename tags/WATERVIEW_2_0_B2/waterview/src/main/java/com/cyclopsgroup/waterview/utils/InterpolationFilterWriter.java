/* ==========================================================================
 * Copyright 2002-2005 Cyclops Group Community
 *
 * Licensed under the COMMON DEVELOPMENT AND DISTRIBUTION LICENSE
 * (CDDL) Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.opensource.org/licenses/cddl1.txt
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * =========================================================================
 */
package com.cyclopsgroup.waterview.utils;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Wirter that interpolate some values
 */
public abstract class InterpolationFilterWriter
    extends FilterWriter
{
    private static final Pattern NAME_PATTERN = Pattern.compile( "^[a-zA-Z][\\w\\.\\-]*$" );

    private Writer output;

    private StringBuffer tokenName;

    private char tokenStart;

    /**
     * Constructor for class InterpolationFilterWriter
     *
     * @param output Output writer
     * @param tokenStart Start token
     */
    public InterpolationFilterWriter( Writer output, char tokenStart )
    {
        super( output );
        this.output = output;
        this.tokenStart = tokenStart;
    }

    private void exportWord()
        throws IOException
    {
        if ( tokenName == null )
        {
            return;
        }
        String name = tokenName.toString();
        tokenName = null;
        if ( StringUtils.isEmpty( name ) )
        {
            output.write( tokenStart );
        }
        else
        {
            try
            {
                String value = interpolate( name );
                output.write( value );
            }
            catch ( Exception e )
            {
                output.write( tokenStart );
                output.write( name );
            }
        }
    }

    private int indexOf( char[] chars, char c, int off )
    {
        if ( off >= chars.length )
        {
            return -1;
        }
        int n = -1;
        for ( int i = off; i < chars.length; i++ )
        {
            if ( chars[i] == c )
            {
                n = i;
                break;
            }
        }
        return n;
    }

    /**
     * Do the interpolation
     *
     * @param name Variable name
     * @return Variable value
     * @throws Exception Throw it out
     */
    protected abstract String interpolate( String name )
        throws Exception;

    /**
     * Overwrite or implement method write()
     *
     * @see java.io.FilterWriter#write(char[], int, int)
     */
    public void write( char[] cbuf, int off, int len )
        throws IOException
    {
        int startPosition = indexOf( cbuf, tokenStart, off );
        int outBound = off + len;
        if ( tokenName == null && ( startPosition == -1 || startPosition >= outBound ) )
        {
            output.write( cbuf, off, len );
        }
        else
        {
            char[] buf = new char[len];
            System.arraycopy( cbuf, off, buf, 0, len );
            writeChars( buf );
        }
    }

    /**
     * Overwrite or implement method write()
     *
     * @see java.io.FilterWriter#write(int)
     */
    public void write( int c )
        throws IOException
    {
        if ( tokenName == null )
        {
            if ( c == tokenStart )
            {
                tokenName = new StringBuffer();
            }
            else
            {
                super.write( c );
            }
        }
        else
        {
            String potentialName = tokenName.toString() + (char) c;
            if ( NAME_PATTERN.matcher( potentialName ).matches() )
            {
                tokenName.append( (char) c );
            }
            else
            {
                exportWord();
                if ( c == tokenStart )
                {
                    tokenName = new StringBuffer();
                }
                else
                {
                    output.write( c );
                }
            }
        }
    }

    /**
     * Overwrite or implement method write()
     *
     * @see java.io.FilterWriter#write(java.lang.String, int, int)
     */
    public void write( String str, int off, int len )
        throws IOException
    {
        int startPosition = str.indexOf( tokenStart, off );
        int outBound = off + len;
        if ( tokenName == null && ( startPosition == -1 || startPosition >= outBound ) )
        {
            output.write( str, off, len );
        }
        else
        {
            String s = str.substring( off, off + len );
            writeChars( s.toCharArray() );
        }
    }

    private void writeChars( char[] chars )
        throws IOException
    {
        for ( int i = 0; i < chars.length; i++ )
        {
            write( (int) chars[i] );
        }
    }
}
