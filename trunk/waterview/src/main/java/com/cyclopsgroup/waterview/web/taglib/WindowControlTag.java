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

import com.cyclopsgroup.waterview.jelly.taglib.JellyControlTag;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 */
public class WindowControlTag
    extends JellyControlTag
{
    private String title;

    private boolean maximizable;

    private boolean editable;

    /**
     * Getter method for editable
     *
     * @return Returns the editable.
     */
    public boolean isEditable()
    {
        return editable;
    }

    /**
     * Setter method for editable
     *
     * @param editable The editable to set.
     */
    public void setEditable( boolean editable )
    {
        this.editable = editable;
    }

    /**
     * Getter method for maximizable
     *
     * @return Returns the maximizable.
     */
    public boolean isMaximizable()
    {
        return maximizable;
    }

    /**
     * Setter method for maximizable
     *
     * @param maximizable The maximizable to set.
     */
    public void setMaximizable( boolean maximizable )
    {
        this.maximizable = maximizable;
    }

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
     * Constructor for class WindowControlTag
     *
     */
    public WindowControlTag()
    {
        setScript( "/waterview/control/WindowControl.jelly" );
    }
}
