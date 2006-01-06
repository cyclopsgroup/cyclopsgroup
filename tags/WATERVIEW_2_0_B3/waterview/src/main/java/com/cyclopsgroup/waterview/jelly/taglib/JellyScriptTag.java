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
package com.cyclopsgroup.waterview.jelly.taglib;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;

import org.apache.commons.jelly.JellyContext;
import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.Script;
import org.apache.commons.jelly.XMLOutput;
import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.waterview.jelly.JellyEngine;
import com.cyclopsgroup.waterview.spi.taglib.TagSupport;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Tag to run a given script
 */
public class JellyScriptTag
    extends TagSupport
{
    private String path;

    private String type = "system";

    /**
     * Overwrite or implement method processTag()
     *
     * @see com.cyclopsgroup.waterview.utils.TagSupportBase#processTag(org.apache.commons.jelly.XMLOutput)
     */
    protected void processTag( XMLOutput output )
        throws Exception
    {
        requireAttribute( "type" );
        requireAttribute( "path" );
        Script script = null;
        if ( StringUtils.equals( getType(), "system" ) )
        {
            JellyEngine je = (JellyEngine) getServiceManager().lookup( JellyEngine.ROLE );
            script = je.getScript( getPath() );
        }
        else if ( StringUtils.equals( getType(), "classpath" ) )
        {
            URL resource = getClass().getClassLoader().getResource( getPath() );
            if ( resource != null )
            {
                script = context.compileScript( resource );
            }
        }
        else if ( StringUtils.equals( getType(), "file" ) )
        {
            File file = new File( getPath() );
            if ( file.exists() )
            {
                script = context.compileScript( file.toURL() );
            }
        }
        else
        {
            throw new JellyTagException( "Type must be system|classpath|file, default value is system" );
        }
        if ( script == null )
        {
            throw new FileNotFoundException( "Resource " + getPath() + " is not found in " + getType() );
        }
        JellyContext jc = new JellyContext( getContext() );
        if ( script != null )
        {
            script.run( jc, output );
            output.flush();
        }
    }

    /**
     * @return Returns the path.
     */
    public String getPath()
    {
        return path;
    }

    /**
     * @return Returns the type.
     */
    public String getType()
    {
        return type;
    }

    /**
     * @param path The path to set.
     */
    public void setPath( String path )
    {
        this.path = path;
    }

    /**
     * @param type The type to set.
     */
    public void setType( String type )
    {
        this.type = type;
    }
}
