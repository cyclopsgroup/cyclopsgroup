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
package com.cyclopsgroup.waterview;

import org.apache.commons.lang.enums.Enum;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Resource object
 */
public class Resource
{
    /** Type of resource */
    public static final class ResourceType
        extends Enum
    {
        private ResourceType( String name )
        {
            super( name );
        }
    }

    /** external type */
    public static final ResourceType EXTERNAL = new ResourceType( "external" );

    /** Internal type */
    public static final ResourceType INTERNAL = new ResourceType( "internal" );

    /** Web resource type */
    public static final ResourceType WEB = new ResourceType( "web" );

    /**
     * get resource type
     *
     * @param type Type name
     * @return Type instance
     */
    public static ResourceType getResourceType( String type )
    {
        if ( type.equals( "internal" ) )
        {
            return INTERNAL;
        }
        else if ( type.equals( "web" ) )
        {
            return WEB;
        }
        else if ( type.equals( "external" ) )
        {
            return EXTERNAL;
        }
        else
        {
            throw new IllegalArgumentException( "type must be internal|web|external" );
        }
    }

    private String description;

    private String path;

    private ResourceType type;

    /**
     * Constructor for class Resource
     *
     * @param type Type
     * @param path Path
     */
    public Resource( ResourceType type, String path )
    {
        this.type = type;
        this.path = path;
    }

    /**
     * Getter method for property description
     *
     * @return Returns the description.
     */
    public final String getDescription()
    {
        return description;
    }

    /**
     * Getter method for property path
     *
     * @return Returns the path.
     */
    protected String getPath()
    {
        return path;
    }

    /**
     * Getter method for property type
     *
     * @return Returns the type.
     */
    public final ResourceType getType()
    {
        return type;
    }

    /**
     * Setter method for property description
     *
     * @param description The description to set.
     */
    public final void setDescription( String description )
    {
        this.description = description;
    }

    /**
     * convert resource to url string
     *
     * @param data Runtime data
     * @return String of url
     */
    public String toURL( RuntimeData data )
    {
        return toURL( data, path );
    }

    /**
     * Convert to url with given path
     *
     * @param data Runtime data
     * @param resourcePath Given path
     * @return URL of string
     */
    protected String toURL( RuntimeData data, String resourcePath )
    {
        if ( type == INTERNAL )
        {
            Link link = (Link) data.getRequestContext().get( Link.NAME );
            return link.getResource( resourcePath );
        }
        else if ( type == WEB )
        {
            return data.getApplicationBaseUrl() + resourcePath;
        }
        else if ( type == EXTERNAL )
        {
            return resourcePath;
        }
        else
        {
            throw new IllegalStateException( "Invalid resource type " + type );
        }
    }
}