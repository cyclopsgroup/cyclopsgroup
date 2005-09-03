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
package com.cyclopsgroup.waterview.navigator;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 * 
 * Base abstract implementation of navigator node
 */
public abstract class BaseNavigatorNode implements NavigatorNode
{
    /**
     * Getter method for field end
     *
     * @return Returns the end.
     */
    public boolean isEnd()
    {
        return getChildrenNodes().length == 0;
    }

    /**
     * Overwrite or implement method isParent()
     *
     * @see com.cyclopsgroup.waterview.navigator.NavigatorNode#isParent(java.lang.String)
     */
    public boolean isParent(String page)
    {
        NavigatorNode[] parents = getParentNodes();
        for (int i = 0; i < parents.length; i++)
        {
            NavigatorNode parent = parents[i];
            if (parent.getPage().equals(page))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Overwrite or implement method toString()
     *
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        return "node:" + getPage();
    }
}
