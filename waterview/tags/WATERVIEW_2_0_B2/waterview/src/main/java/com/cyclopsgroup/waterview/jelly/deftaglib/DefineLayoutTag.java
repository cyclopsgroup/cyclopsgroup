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

import org.apache.commons.jelly.XMLOutput;

import com.cyclopsgroup.waterview.spi.LookAndFeelService;
import com.cyclopsgroup.waterview.utils.TagSupport;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 * 
 * Tag to define layout
 */
public class DefineLayoutTag extends TagSupport
{
    private String layout;

    private String name;

    /**
     * Getter method for field layout
     *
     * @return Returns the layout.
     */
    public String getLayout()
    {
        return layout;
    }

    /**
     * Getter method for field name
     *
     * @return Returns the name.
     */
    public String getName()
    {
        return name;
    }

    protected void processTag(XMLOutput output) throws Exception
    {
        requireAttribute("name");
        requireAttribute("layout");

        ThemeTag theme = (ThemeTag) requireParent(ThemeTag.class);
        LookAndFeelService laf = (LookAndFeelService) getServiceManager()
                .lookup(LookAndFeelService.ROLE);
        theme.getTheme().setLayout(getName(),
                new PredefinedLayoutProxy(laf, getLayout()));
    }

    /**
     * Setter method for field layout
     *
     * @param layout The layout to set.
     */
    public void setLayout(String layout)
    {
        this.layout = layout;
    }

    /**
     * Setter method for field name
     *
     * @param name The name to set.
     */
    public void setName(String name)
    {
        this.name = name;
    }

}
