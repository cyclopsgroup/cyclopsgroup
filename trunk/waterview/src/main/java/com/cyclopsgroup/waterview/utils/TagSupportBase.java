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
package com.cyclopsgroup.waterview.utils;

import java.security.MessageDigest;

import org.apache.avalon.framework.service.ServiceManager;
import org.apache.commons.codec.binary.Hex;
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
    private String tagId;

    private static final String DIGEST_ALGORITHM = "SHA";

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
        catch ( JellyTagException e )
        {
            throw e;
        }
        catch ( Exception e )
        {
            throw new JellyTagException( e );
        }
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
    public String getUniqueTagId()
        throws Exception
    {
        if ( StringUtils.isEmpty( getTagId() ) )
        {
            throw new IllegalArgumentException( "tagId attribute is required for " + this + " to get unique ID" );
        }
        String s = new StringBuffer( getContext().getCurrentURL().toString() ).append( ':' ).append( getTagId() )
            .toString();
        MessageDigest digest = MessageDigest.getInstance( DIGEST_ALGORITHM );
        String d = new String( Hex.encodeHex( digest.digest( s.getBytes() ) ) );
        return d + '/' + getTagId();
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
     * Require parent to be a class
     *
     * @param parentTagClass Parent class
     * @throws JellyTagException Throw it out if not matched
     * @return Parent tag
     */
    protected final Tag requireParent( Class parentTagClass )
        throws JellyTagException
    {
        if ( !parentTagClass.isAssignableFrom( getParent().getClass() ) )
        {
            throw new JellyTagException( "Tag's parent must be " + parentTagClass.getName() );
        }
        return getParent();
    }

    /**
     * Require this tag to be inside given tag class
     * 
     * @param parentTagClass Given tag class
     * @return Parent tag
     * @throws JellyTagException Throw it if requirement is not met
     */
    protected final Tag requireInside( Class parentTagClass )
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
     * @param id The id to set.
     */
    public final void setTagId( String id )
    {
        this.tagId = id;
    }
}