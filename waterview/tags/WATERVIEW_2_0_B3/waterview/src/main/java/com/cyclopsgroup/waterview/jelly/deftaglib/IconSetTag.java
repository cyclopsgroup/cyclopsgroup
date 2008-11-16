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
package com.cyclopsgroup.waterview.jelly.deftaglib;

import org.apache.commons.jelly.XMLOutput;

import com.cyclopsgroup.waterview.Link;
import com.cyclopsgroup.waterview.RuntimeData;
import com.cyclopsgroup.waterview.spi.LookAndFeelService;
import com.cyclopsgroup.waterview.spi.LookAndFeelService.IconSet;
import com.cyclopsgroup.waterview.utils.TagSupport;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Tag to define icon set
 */
public class IconSetTag
    extends TagSupport
    implements IconSet
{
    private String description;

    private String name;

    private String path;

    private String title;

    /**
     * Getter method for property description
     *
     * @return Returns the description.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Getter method for property name
     *
     * @return Returns the name.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Getter method for property path
     *
     * @return Returns the path.
     */
    public String getPath()
    {
        return path;
    }

    /**
     * Getter method for property title
     *
     * @return Returns the title.
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * Overwrite or implement method getUrl()
     *
     * @see com.cyclopsgroup.waterview.spi.LookAndFeelService.IconSet#getUrl(com.cyclopsgroup.waterview.RuntimeData, java.lang.String, int)
     */
    public String getUrl( RuntimeData data, String file, int size )
    {
        Link link = Link.getInstance( data );
        StringBuffer sb = new StringBuffer( link.getResource( getPath() ) );
        sb.append( '/' ).append( size ).append( '/' ).append( file );
        return sb.toString();
    }

    /**
     * Overwrite or implement method processTag()
     *
     * @see com.cyclopsgroup.waterview.utils.TagSupportBase#processTag(org.apache.commons.jelly.XMLOutput)
     */
    protected void processTag( XMLOutput output )
        throws Exception
    {
        requireAttribute( "name" );
        requireAttribute( "path" );
        LookAndFeelService laf = (LookAndFeelService) getServiceManager().lookup( LookAndFeelService.ROLE );
        laf.registerIconSet( getName(), this );
    }

    /**
     * Setter method for property description
     *
     * @param description The description to set.
     */
    public void setDescription( String description )
    {
        this.description = description;
    }

    /**
     * Setter method for property name
     *
     * @param name The name to set.
     */
    public void setName( String name )
    {
        this.name = name;
    }

    /**
     * Setter method for property path
     *
     * @param path The path to set.
     */
    public void setPath( String path )
    {
        this.path = path;
    }

    /**
     * Setter method for property title
     *
     * @param title The title to set.
     */
    public void setTitle( String title )
    {
        this.title = title;
    }
}
