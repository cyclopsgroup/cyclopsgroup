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
    private String defaultTab;

    private String selectedTabName;

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
     * Getter method for property defaultTab
     *
     * @return Returns the defaultTab.
     */
    public String getDefaultTab()
    {
        return defaultTab;
    }

    /**selectedTabName = getDefaultTab();
     * Getter method for property name
     *
     * @return Returns the name.
     */
    public String getName()
    {
        return getTagId();
    }

    /**
     * Get selected tab tag
     *
     * @return Tab tag object
     */
    public TabTag getSelectedTabTag()
    {
        return (TabTag) tabTags.get( selectedTabName );
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

        String key = getUniqueTagId() + "/tabName";
        String tabName = (String) getRuntimeData().getSessionContext().get( key );
        if ( StringUtils.isEmpty( tabName ) )
        {
            tabName = getDefaultTab();
        }

        if ( StringUtils.isEmpty( tabName ) )
        {
            tabName = ( (TabTag) tabTags.values().iterator().next() ).getName();
        }
        selectedTabName = tabName;
        getContext().setVariable( "controlTabNameKey", key );
        super.processTag( output );
    }

    /**
     * Setter method for property defaultTab
     *
     * @param defaultTab The defaultTab to set.
     */
    public void setDefaultTab( String defaultTab )
    {
        this.defaultTab = defaultTab;
    }

    /**
     * Setter method for property name
     *
     * @param name The name to set.
     */
    public void setName( String name )
    {
        setTagId( name );
    }
}
