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

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Default implementation of select option
 */
public final class DefaultSelectOption
    implements SelectOption
{
    private String name;

    private String title;

    /**
     * Constructor for class DefaultSelectOption
     *
     * @param name Name to display
     */
    public DefaultSelectOption( String name )
    {
        this( name, name );
    }

    /**
     * Constructor for class DefaultSelectOption
     *
     * @param name Name
     * @param title Title
     */
    public DefaultSelectOption( String name, String title )
    {
        this.name = name;
        this.title = title;
    }

    /**
     * Overwrite or implement method getName()
     *
     * @see com.cyclopsgroup.waterview.SelectOption#getName()
     */
    public String getName()
    {
        return name;
    }

    /**
     * Overwrite or implement method getTilte()
     *
     * @see com.cyclopsgroup.waterview.SelectOption#getTitle()
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * Overwrite or implement method toString()
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return getName() + ":" + getTitle();
    }
}
