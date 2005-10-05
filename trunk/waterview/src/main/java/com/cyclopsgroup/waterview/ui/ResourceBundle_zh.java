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
package com.cyclopsgroup.waterview.ui;

import java.util.ListResourceBundle;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Resource bundle
 */
public class ResourceBundle_zh
    extends ListResourceBundle
{
    private static final Object[][] CONTENTS = {
        { "application.title", "waterview在线系统" },
        { "application.description", "Waterview在线系统框架" },
        { "waterview.navigation.home", "首页" },
        { "waterview.navigation.system", "系统" },
        { "waterview.navigation.help", "帮助" },
        { "waterview.navigation.abouts", "关于" },
        { "waterview.navigation.status", "当前状态" },
        { "waterview.view.setlocale", "设置地区和语言" },
        { "waterview.view.sessiondump", "查看Session内容" },
        { "waterview.view.waterviewoverview", "Water简介" } };

    /**
     * Overwrite or implement method getContents()
     *
     * @see java.util.ListResourceBundle#getContents()
     */
    protected Object[][] getContents()
    {
        return CONTENTS;
    }
}
