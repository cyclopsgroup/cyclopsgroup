/* ==========================================================================
 * Copyright 2002-2004 Cyclops Group Community
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
package com.cyclopsgroup.waterview.navigator.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Map;

import org.apache.avalon.framework.activity.Initializable;
import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.commons.collections.MultiHashMap;

import com.cyclopsgroup.waterview.navigator.NavigatorHome;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Default implementation of navigator home
 */
public class DefaultNavigatorHome extends AbstractLogEnabled implements
        NavigatorHome, Initializable
{
    private Map children;

    private Map nodes;

    Collection getChildren(String path)
    {
        Collection c = (Collection) children.get(path);
        return c == null ? Collections.EMPTY_SET : c;
    }

    DefaultNavigatorNode getNode(String path)
    {
        return (DefaultNavigatorNode) nodes.get(path);
    }

    public void initialize() throws Exception
    {
        nodes = new Hashtable();
        children = new MultiHashMap();

    }

}
