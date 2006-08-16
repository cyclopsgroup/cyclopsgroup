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
package com.cyclopsgroup.waterview.jelly.deftaglib;

import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.XMLOutput;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cyclopsgroup.waterview.jelly.JellyEngine;
import com.cyclopsgroup.waterview.spi.taglib.TagSupport;
import com.cyclopsgroup.waterview.utils.TagPackage;

/**
 * Tag for tag package definition
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class TagPackageTag
    extends TagSupport
{

    private String className;

    private Log logger = LogFactory.getLog( getClass() );

    /**
     * Overwrite or implement method processTag()
     *
     * @see com.cyclopsgroup.waterview.utils.TagSupportBase#processTag(org.apache.commons.jelly.XMLOutput)
     */
    @Override
    public void processTag( XMLOutput output )
        throws Exception
    {
        requireAttribute( "className" );
        requireParent( TagLibraryTag.class );
        try
        {
            TagPackage pkg = (TagPackage) Class.forName( getClassName() ).newInstance();
            TagLibraryTag tagLibraryTag = (TagLibraryTag) getParent();
            JellyEngine jellyEngine = (JellyEngine) getContext().getVariable( JellyEngine.ROLE );
            jellyEngine.registerTagPackage( tagLibraryTag.getUri(), pkg );
            logger.debug( "Tag package " + pkg + " is registered in waterview" );
        }
        catch ( Exception e )
        {
            throw new JellyTagException( e );
        }
    }

    /**
     * Getter method for className
     *
     * @return Returns the className.
     */
    public String getClassName()
    {
        return className;
    }

    /**
     * Setter method for className
     *
     * @param className The className to set.
     */
    public void setClassName( String className )
    {
        this.className = className;
    }
}
