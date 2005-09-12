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
package com.cyclopsgroup.waterview.web.taglib;

import org.apache.commons.jelly.Script;
import org.apache.commons.jelly.XMLOutput;

import com.cyclopsgroup.waterview.jelly.JellyEngine;
import com.cyclopsgroup.waterview.spi.taglib.TagSupport;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 * 
 * Form image tag
 */
public class FormImageTag
    extends TagSupport
{
    private int height = 16;

    private String url;

    private int width = 16;

    /**
     * Getter method for property height
     *
     * @return Returns the height.
     */
    public int getHeight()
    {
        return height;
    }

    /**
     * Getter method for property url
     *
     * @return Returns the url.
     */
    public String getUrl()
    {
        return url;
    }

    /**
     * Getter method for property width
     *
     * @return Returns the width.
     */
    public int getWidth()
    {
        return width;
    }

    /**
     * Overwrite or implement method processTag()
     *
     * @see com.cyclopsgroup.waterview.utils.TagSupportBase#processTag(org.apache.commons.jelly.XMLOutput)
     */
    protected void processTag( XMLOutput output )
        throws Exception
    {
        requireParent( SubmitTag.class );
        requireAttribute( "url" );
        JellyEngine je = (JellyEngine) getServiceManager().lookup( JellyEngine.ROLE );
        Script script = je.getScript( "fabric", "/widget/FormImage.jelly" );
        getContext().setVariable( "image", this );
        script.run( getContext(), output );
    }

    /**
     * Setter method for property height
     *
     * @param height The height to set.
     */
    public void setHeight( int height )
    {
        this.height = height;
    }

    /**
     * Setter method for property url
     *
     * @param url The url to set.
     */
    public void setUrl( String url )
    {
        this.url = url;
    }

    /**
     * Setter method for property width
     *
     * @param width The width to set.
     */
    public void setWidth( int width )
    {
        this.width = width;
    }
}
