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
package com.cyclopsgroup.tornado.ui;

import java.util.ListResourceBundle;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Chinese resource bundle
 */
public class ResourceBundle_zh
    extends ListResourceBundle
{
    private static final Object[][] CONTENTS = {
        { "tornado.navigation.admin", "系统管理" },
        { "tornado.navigation.security", "安全配置" },
        { "tornado.navigation.users", "用户管理" },
        { "tornado.navigation.createuser", "创建新用户" },
        { "tornado.navigation.userprofile", "用户详细信息" },
        { "tornado.navigation.groups", "组管理" },
        { "tornado.navigation.roles", "安全角色管理" },
        { "tornado.navigation.portal", "站点管理" },
        { "tornado.navigation.lookandfeel", "网站外观" },
        { "tornado.navigation.myinfo", "我的地盘" },
        { "tornado.navigation.myprofile", "我的个人信息" },
        { "tornado.p.notlogin", "您还没有登录" },
        { "document.text.password", "口令" },
        { "document.text.timeout", "超时" },
        { "document.text.minute", "分钟" },
        { "document.text.hour", "小时" },
        { "document.text.second", "秒" },
        { "document.text.login", "登录" },
        { "document.text.logout", "离开" },
        { "document.text.hello", "您好" } };

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
