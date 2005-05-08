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
package com.cyclopsgroup.waterview.apps.webfs.ui.view;

import java.io.File;

import com.cyclopsgroup.clib.lang.Context;
import com.cyclopsgroup.waterview.Module;
import com.cyclopsgroup.waterview.PageRuntime;
import com.cyclopsgroup.waterview.apps.webfs.FileTreeRoot;
import com.cyclopsgroup.waterview.apps.webfs.WebFileSystem;

/**
 * Navigator bar
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class WebFSNavigator implements Module
{
    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.Module#execute(com.cyclopsgroup.waterview.PageRuntime, com.cyclopsgroup.clib.lang.Context)
     */
    public void execute(PageRuntime runtime, Context context) throws Exception
    {
        WebFileSystem webfs = (WebFileSystem) runtime.getServiceManager()
                .lookup(WebFileSystem.ROLE);
        FileTreeRoot[] roots = webfs.getRoots();
        context.put("fsRoots", roots);

        File file = (File) context.get("currentFile");
        if (file != null && file.isDirectory())
        {
            File[] files = file.listFiles(webfs.getFileFilter());
            context.put("files", files);
        }
    }
}
