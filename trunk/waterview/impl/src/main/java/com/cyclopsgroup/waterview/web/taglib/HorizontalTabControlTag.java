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

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.XMLOutput;
import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.waterview.jelly.taglib.BaseJellyControlTag;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Horizontal tab control
 */
public class HorizontalTabControlTag
    extends BaseJellyControlTag
    implements TabTagAware
{
    private String selected = "default";

    private Map tabTags = ListOrderedMap.decorate( new HashMap() );

    /**
     * Constructor for class HorizontalTabControlTag
     */
    public HorizontalTabControlTag()
    {
        setScript( "/waterview/control/HorizontalTabControl.jelly" );
    }

    /**
     * Overwrite or implement method doTabTag()
     *
     * @see com.cyclopsgroup.waterview.web.taglib.TabTagAware#doTabTag(com.cyclopsgroup.waterview.web.taglib.TabTag)
     */
    public void doTabTag( TabTag tag )
    {
        tabTags.put( tag.getName(), tag );
    }

    /**
     * Get unique name
     *
     * @return Name value
     */
    public String getName()
    {
        return getTagId();
    }

    /**
     * Getter method for field selected
     *
     * @return Returns the selected.
     */
    public String getSelected()
    {
        return selected;
    }

    /**
     * Get selected tab
     *
     * @return Selected tab
     */
    public TabTag getSelectedTabTag()
    {
        return (TabTag) tabTags.get( getSelected() );
    }

    /**
     * Get tab tags
     *
     * @return Collection of tab tags
     */
    public Collection getTabTags()
    {
        return tabTags.values();
    }

    /**
     * Overwrite or implement method processTag()
     *
     * @see com.cyclopsgroup.waterview.jelly.taglib.BaseJellyControlTag#processTag(org.apache.commons.jelly.XMLOutput)
     */
    protected void processTag( XMLOutput output )
        throws Exception
    {
        requireAttribute( "name" );
        invokeBody( XMLOutput.createDummyXMLOutput() );
        if ( tabTags.isEmpty() )
        {
            throw new JellyTagException( "At least one tab must be defined" );
        }
        String selectedTabName = (String) getRunData().getSessionContext().get( getUniqueTagId() );
        if ( StringUtils.isNotEmpty( selectedTabName ) )
        {
            setSelected( selectedTabName );
        }
        else if ( StringUtils.isEmpty( getSelected() ) || !tabTags.containsKey( getSelected() ) )
        {
            setSelected( (String) tabTags.keySet().iterator().next() );
        }
        super.processTag( output );
    }

    /**
     * Set unique name
     *
     * @param name Control name
     */
    public void setName( String name )
    {
        setTagId( name );
    }

    /**
     * Setter method for field selected
     *
     * @param selected The selected to set.
     */
    public void setSelected( String selected )
    {
        this.selected = selected;
    }
}
