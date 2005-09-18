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
package com.cyclopsgroup.waterview.core.taglib;

import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.waterview.Context;
import com.cyclopsgroup.waterview.RuntimeData;
import com.cyclopsgroup.waterview.spi.ModuleManager;
import com.cyclopsgroup.waterview.spi.View;
import com.cyclopsgroup.waterview.spi.taglib.BaseViewTag;

/**
 * Simple view tag
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class SimpleViewTag
    extends BaseViewTag
{
    private String path;

    private String title;

    /**
     * Getter method for title
     *
     * @return Returns the title.
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * Setter method for title
     *
     * @param title The title to set.
     */
    public void setTitle( String title )
    {
        this.title = title;
    }

    /**
     * Overwrite or implement method createView()
     *
     * @see com.cyclopsgroup.waterview.spi.taglib.BaseViewTag#createView()
     */
    protected View createView()
        throws Exception
    {
        requireAttribute( "path" );
        ModuleManager mm = (ModuleManager) getServiceManager().lookup( ModuleManager.ROLE );
        final View view = mm.createDynaView( getPath() );
        if ( StringUtils.isEmpty( getTitle() ) )
        {
            return view;
        }
        return new View()
        {

            public String getName()
            {
                return getTitle();
            }

            public void render( RuntimeData data, Context viewContext )
                throws Exception
            {
                view.render( data, viewContext );
            }
        };
    }

    /**
     * Getter method for path
     *
     * @return Returns the path.
     */
    public String getPath()
    {
        return path;
    }

    /**
     * Setter method for path
     *
     * @param path The path to set.
     */
    public void setPath( String path )
    {
        this.path = path;
    }
}
