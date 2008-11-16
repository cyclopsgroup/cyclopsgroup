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
        { "waterview.navigation.start", "开始" },
        { "waterview.navigation.home", "首页" },
        { "waterview.navigation.system", "系统" },
        { "waterview.navigation.help", "帮助" },
        { "waterview.navigation.abouts", "关于" },
        { "waterview.navigation.status", "当前状态" },
        { "waterview.view.setlocale", "设置地区和语言" },
        { "waterview.view.sessiondump", "查看Session内容" },
        { "waterview.view.waterviewoverview", "Waterview简介" },
        { "waterview.view.systemnavigation", "系统导航" },
        { "waterview.view.links", "链接" },
        { "waterview.view.references", "相关信息" },
        { "waterview.view.samples", "例子" },
        { "waterview.view.sampleform", "表单例子" },
        { "waterview.view.sampletable", "表格例子" },
        { "waterview.view.tiledform", "平铺的表单例子" },
        { "waterview.view.velocityview", "Velocity的视图" },
        { "waterview.view.horizontaltab", "水平Tab面板" },
        { "waterview.view.aboutwaterview", "关于Waterview" },
        { "waterview.view.globalvariables", "全局变量列表" },
        { "waterview.text.footer.allrights", "所有版权归Cyclops Group所有，2005" },
        { "waterview.text.footer.poweredby", "由Cyclops Group Waterview大力支持" },
        { "document.text.pushme", "点这里" },
        { "document.text.id", "编号" },
        { "document.text.name", "名称" },
        { "document.text.title", "标题" },
        { "document.text.description", "说明" },
        { "document.text.select", "选中" },
        { "document.text.deleteselected", "删除选中的" },
        { "document.text.savechange", "保存改动" },
        { "document.text.disabled", "已作废" },
        { "document.text.createddate", "创建时间" },
        { "document.text.type", "类型" },
        { "document.text.record", "记录" },
        { "document.text.edit", "编辑" },
        { "document.text.row", "行" },
        { "document.text.configure", "配置" },
        { "document.text.info", "信息" },
        { "document.text.reload", "重新载入" },
        { "document.text.page", "页" },
        { "document.text.system", "系统" },
        { "document.text.true", "是" },
        { "document.text.false", "否" },
        { "document.text.any", "任何" },
        { "document.text.time", "时间" },
        { "document.text.date", "日期" },
        { "document.text.search", "搜索" },
        { "document.text.save", "保存" } };

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
