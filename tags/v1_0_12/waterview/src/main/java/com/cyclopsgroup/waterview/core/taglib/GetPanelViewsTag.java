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
package com.cyclopsgroup.waterview.core.taglib;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.set.ListOrderedSet;
import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.XMLOutput;

import com.cyclopsgroup.waterview.spi.Page;
import com.cyclopsgroup.waterview.spi.PanelContent;
import com.cyclopsgroup.waterview.spi.View;
import com.cyclopsgroup.waterview.spi.taglib.TagSupport;
import com.cyclopsgroup.waterview.spi.taglib.ViewAware;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 */
public class GetPanelViewsTag
    extends TagSupport
    implements ViewAware
{
    private String var = "views";

    private String panel;

    private Set defaultViews = ListOrderedSet.decorate( new HashSet() );

    /**
     * Getter method for panel
     *
     * @return Returns the panel.
     */
    public String getPanel()
    {
        return panel;
    }

    /**
     * Setter method for panel
     *
     * @param panel The panel to set.
     */
    public void setPanel( String panel )
    {
        this.panel = panel;
    }

    /**
     * Getter method for var
     *
     * @return Returns the var.
     */
    public String getVar()
    {
        return var;
    }

    /**
     * Setter method for var
     *
     * @param var The var to set.
     */
    public void setVar( String var )
    {
        this.var = var;
    }

    /**
     * Override method processTag in class GetPanelComponentsTag
     *
     * @see com.cyclopsgroup.waterview.utils.TagSupportBase#processTag(org.apache.commons.jelly.XMLOutput)
     */
    protected void processTag( XMLOutput output )
        throws Exception
    {
        requireAttribute( "var" );
        requireAttribute( "panel" );
        Page page = (Page) getContext().getVariable( Page.NAME );
        if ( page == null )
        {
            throw new JellyTagException( "Page is not found" );
        }
        PanelContent content = page.getPanelContent( getPanel() );

        List views;
        if ( content == null )
        {
            invokeBody( XMLOutput.createDummyXMLOutput() );
            views = new ArrayList( defaultViews );
        }
        else if ( content.isAppend() )
        {
            invokeBody( XMLOutput.createDummyXMLOutput() );
            views = new ArrayList( defaultViews );
            CollectionUtils.addAll( views, content.getViews() );
        }
        else
        {
            views = new ArrayList();
            CollectionUtils.addAll( views, content.getViews() );
        }
        getContext().setVariable( getVar(), views );
    }

    /**
     * Override method doView in class GetPanelViewsTag
     *
     * @see com.cyclopsgroup.waterview.spi.taglib.ViewAware#doView(com.cyclopsgroup.waterview.spi.View)
     */
    public void doView( View view )
    {
        defaultViews.add( view );
    }
}
