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
package com.cyclopsgroup.waterview.web.taglib;

import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.XMLOutput;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Form control with tiled template
 */
public class TiledFormControlTag
    extends BaseJellyFormControlTag
{
    private int columns = 2;

    private boolean vertical;

    /**
     * Constructor for class TiledFormControlTag
     */
    public TiledFormControlTag()
    {
        setScript( "/waterview/control/TiledFormControl.jelly" );
    }

    /**
     * Getter method for property columns
     *
     * @return Returns the columns.
     */
    public int getColumns()
    {
        return columns;
    }

    /**
     * Getter method for property vertical
     *
     * @return Returns the vertical.
     */
    public boolean isVertical()
    {
        return vertical;
    }

    /**
     * Overwrite or implement method processTag()
     *
     * @see com.cyclopsgroup.waterview.web.taglib.BaseJellyFormControlTag#processTag(org.apache.commons.jelly.XMLOutput)
     */
    protected void processTag( XMLOutput output )
        throws Exception
    {
        if ( getColumns() < 1 )
        {
            throw new JellyTagException( "Columns must be bigger than 1" );
        }
        super.processTag( output );
    }

    /**
     * Setter method for property columns
     *
     * @param columns The columns to set.
     */
    public void setColumns( int columns )
    {
        this.columns = columns;
    }

    /**
     * Setter method for property vertical
     *
     * @param vertical The vertical to set.
     */
    public void setVertical( boolean vertical )
    {
        this.vertical = vertical;
    }
}
