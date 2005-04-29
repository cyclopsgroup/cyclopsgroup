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
package com.cyclopsgroup.waterview.jelly.taglib;

import com.cyclopsgroup.clib.lang.xml.TagPackage;

/**
 * Package of UI tags
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class JellyTagPackage extends TagPackage
{
    /**
     * Constructor for class UITagPackage
     *
     */
    public JellyTagPackage()
    {
        addTag("Frame", FrameTag.class);
        addTag("Layout", LayoutTag.class);
        addTag("Page", PageTag.class);
        addTag("Panel", PanelTag.class);
        addTag("JellyLayout", JellyLayoutTag.class);
        addTag("SystemLayout", SystemLayoutTag.class);
        addTag("PanelContent", PanelContentTag.class);
        addTag("JellyView", JellyViewTag.class);
        addTag("SimpleView", SimpleViewTag.class);
        addTag("RenderLayout", RenderLayoutTag.class);
    }
}
