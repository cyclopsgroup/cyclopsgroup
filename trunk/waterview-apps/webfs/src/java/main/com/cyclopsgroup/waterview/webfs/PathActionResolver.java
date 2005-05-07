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
package com.cyclopsgroup.waterview.webfs;

import java.io.File;
import java.io.FileNotFoundException;

import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.waterview.ActionResolver;
import com.cyclopsgroup.waterview.PageRuntime;

/**
 * Path action resolver
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class PathActionResolver extends AbstractLogEnabled implements
        ActionResolver
{

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.ActionResolver#resolveAction(java.lang.String, com.cyclopsgroup.waterview.PageRuntime)
     */
    public void resolveAction(String actionName, PageRuntime runtime)
            throws Exception
    {
        runtime.getPageContext().put("pathAction", actionName);
        WebFileSystem webfs = (WebFileSystem) runtime.getServiceManager()
                .lookup(WebFileSystem.ROLE);
        String rootId = actionName;
        String path = "";
        if (actionName.indexOf('/') != -1)
        {
            String[] parts = StringUtils.split(actionName, '/');
            rootId = parts[0];
            path = actionName.substring(rootId.length() + 1);
        }
        FileTreeRoot root = webfs.getRoot(rootId);
        if (root == null)
        {
            throw new UnknownRootException(actionName);
        }
        runtime.getPageContext().put("currentRoot", root);
        runtime.getPageContext().put("currentPath", path);

        File file = new File(root.getFile(), path);
        if (!file.exists())
        {
            throw new FileNotFoundException(path
                    + " doesn't exist in filesystem");
        }
        runtime.getPageContext().put("currentFile", file);
    }
}
