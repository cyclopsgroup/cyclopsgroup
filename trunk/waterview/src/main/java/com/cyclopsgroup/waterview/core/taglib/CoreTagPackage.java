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

import com.cyclopsgroup.waterview.util.TagPackage;

/**
 * Core tag package
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class CoreTagPackage extends TagPackage
{
    /**
     * Constructor for class CoreTagPackage
     */
    public CoreTagPackage()
    {
        addTag("Page", PageTag.class);
        addTag("SystemLayout", SystemLayoutTag.class);
        addTag("PanelContent", PanelContentTag.class);
        addTag("SimpleView", SimpleViewTag.class);
        addTag("DisplayView", DisplayViewTag.class);
        addTag("LocalizedText", LocalizedTextTag.class);
        addTag("LocalizedParagraph", LocalizedParagraphTag.class);
        addTag("GetComponent", GetComponentTag.class);
        addTag("t", LocalizedTextTag.class);
        addTag("p", LocalizedParagraphTag.class);
        addTag("Dummy", DummyTag.class);
        addTag("RunScript", RunScriptTag.class);
    }
}
