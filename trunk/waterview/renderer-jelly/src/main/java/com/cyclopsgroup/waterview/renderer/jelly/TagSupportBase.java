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
package com.cyclopsgroup.waterview.renderer.jelly;

import java.net.URL;
import java.security.MessageDigest;
import java.util.LinkedList;
import java.util.List;

import org.apache.avalon.framework.service.ServiceManager;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.jelly.JellyContext;
import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.MissingAttributeException;
import org.apache.commons.jelly.Tag;
import org.apache.commons.jelly.TagSupport;
import org.apache.commons.jelly.XMLOutput;
import org.apache.commons.lang.StringUtils;

/**
 * Base jelly tag
 *
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public abstract class TagSupportBase
    extends TagSupport
{
    private static final String DIGEST_ALGORITHM = "SHA";

    private static final URL[] EMPTY_URL_ARRAY = new URL[0];

    private static final String SCRIPT_RESOURCE_NAME = TagSupportBase.class.getName() + "/scriptresource";

    /**
     * Set the resource for script
     *
     * @param resource Resource of script
     * @param context Context to set
     */
    @SuppressWarnings("unchecked")
    public static final void addScriptResource( URL resource, JellyContext context )
    {
        synchronized ( context )
        {
            LinkedList<URL> scriptResources = (LinkedList<URL>) context.getVariable( SCRIPT_RESOURCE_NAME );
            if ( scriptResources == null )
            {
                scriptResources = new LinkedList<URL>();
                context.setVariable( SCRIPT_RESOURCE_NAME, scriptResources );
            }
            scriptResources.add( resource );
        }
    }

    /**
     * Remove last script resource
     *
     * @param resource Resource to remove
     * @param context Jelly context
     */
    @SuppressWarnings("unchecked")
    public static final void removeScriptResource( URL resource, JellyContext context )
    {
        synchronized ( context )
        {
            LinkedList<URL> scriptResources = (LinkedList<URL>) context.getVariable( SCRIPT_RESOURCE_NAME );
            if ( scriptResources == null )
            {
                return;
            }
            URL last = scriptResources.getLast();
            if ( last.sameFile( resource ) )
            {
                scriptResources.removeLast();
            }
            else
            {
                throw new IllegalStateException( "The resource to remove is not the last resource" );
            }
        }
    }

    private String tagId;

    private String uniqueTagId;

    /**
     * Overwrite or implement method doTag()
     *
     * @see org.apache.commons.jelly.Tag#doTag(org.apache.commons.jelly.XMLOutput)
     */
    public void doTag( XMLOutput output )
        throws MissingAttributeException, JellyTagException
    {
        try
        {
            processTag( output );
        }
        catch ( Exception e )
        {
            JellyTagException ex = null;
            if ( e instanceof JellyTagException )
            {
                ex = (JellyTagException) e;
            }
            else
            {
                ex = new JellyTagException( e );
            }
            URL[] scriptResources = getScriptResources();
            StringBuffer sb = new StringBuffer();
            for ( int i = 0; i < scriptResources.length; i++ )
            {
                URL url = scriptResources[i];
                sb.append( ">" ).append( url.toString() );
            }
            ex.setFileName( sb.toString() );
            throw ex;
        }
    }

    /**
     * Get the resource containing this script
     *
     * @return URL of script resource
     */
    @SuppressWarnings("unchecked")
    protected URL[] getScriptResources()
    {
        List<URL> scriptResources = (List<URL>) getContext().getVariable( SCRIPT_RESOURCE_NAME );
        if ( scriptResources == null )
        {
            return EMPTY_URL_ARRAY;
        }
        return scriptResources.toArray( EMPTY_URL_ARRAY );
    }

    /**
     * Get service manager
     *
     * @return Service manager object
     */
    protected ServiceManager getServiceManager()
    {
        return (ServiceManager) getContext().getVariable( ServiceManager.class.getName() );
    }

    /**
     * @return Returns the id.
     */
    public final String getTagId()
    {
        return tagId;
    }

    /**
     * Get internal tag id
     *
     * @return Tag id
     * @throws Exception
     */
    public synchronized String getUniqueTagId()
        throws Exception
    {
        if ( StringUtils.isNotEmpty( uniqueTagId ) )
        {
            return uniqueTagId;
        }
        if ( StringUtils.isEmpty( getTagId() ) )
        {
            throw new IllegalArgumentException( "tagId attribute is required for " + this + " to get unique ID" );
        }
        StringBuffer s = new StringBuffer( getTagId() ).append( '@' );
        URL[] scriptResources = getScriptResources();
        for ( int i = 0; i < scriptResources.length; i++ )
        {
            URL resource = scriptResources[i];
            s.append( "->" ).append( resource );
        }
        MessageDigest digest = MessageDigest.getInstance( DIGEST_ALGORITHM );
        String d = new String( Hex.encodeHex( digest.digest( s.toString().getBytes() ) ) );
        uniqueTagId = getTagId() + d;
        return uniqueTagId;
    }

    /**
     * process the tag
     *
     * @param output Output
     * @throws Exception Throw it out
     */
    protected abstract void processTag( XMLOutput output )
        throws Exception;

    /**
     * Require body of a tag
     *
     * @throws JellyTagException If the body is empty, throw it out
     */
    protected final void requireBody()
        throws JellyTagException
    {
        if ( StringUtils.isEmpty( getBodyText() ) )
        {
            throw new JellyTagException( "Body text is required" );
        }
    }

    /**
     * Require this tag to be inside given tag class
     *
     * @param parentTagClass Given tag class
     * @return Parent tag
     * @throws JellyTagException Throw it if requirement is not met
     */
    protected final Tag requireInside( Class<? extends Tag> parentTagClass )
        throws JellyTagException
    {
        Tag parent = findAncestorWithClass( parentTagClass );
        if ( parent == null )
        {
            throw new JellyTagException( "Tag must be inside " + parentTagClass.getName() );
        }
        return parent;
    }

    /**
     * Require parent to be a class
     *
     * @param parentTagClass Parent class
     * @throws JellyTagException Throw it out if not matched
     * @return Parent tag
     */
    protected final Tag requireParent( Class<? extends Tag> parentTagClass )
        throws JellyTagException
    {
        if ( !parentTagClass.isAssignableFrom( getParent().getClass() ) )
        {
            throw new JellyTagException( "Tag's parent must be " + parentTagClass.getName() );
        }
        return getParent();
    }

    /**
     * @param id The id to set.
     */
    public final void setTagId( String id )
    {
        this.tagId = id;
    }
}