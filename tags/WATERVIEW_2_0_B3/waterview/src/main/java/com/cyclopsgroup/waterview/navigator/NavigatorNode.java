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

import com.cyclopsgroup.waterview.web.StaticNode;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 * 
 * Navigator node
 */
public interface NavigatorNode
    extends StaticNode
{
    /** Empty nodes */
    NavigatorNode[] EMPTY_ARRAY = new NavigatorNode[0];

    /**
     * Get description of node
     *
     * @return Description of node
     */
    String getDescription();

    /**
     * Get linked page of node
     *
     * @return Linked page of node
     */
    String getPage();

    /**
     * Get array of parent nodes
     *
     * @return Array of parent nodes
     */
    NavigatorNode[] getParentNodes();

    /**
     * Get title of node
     *
     * @return Title of node
     */
    String getTitle();

    /**
     * Is the node hidden
     *
     * @return True if node is not in menu
     */
    boolean isHidden();

    /**
     * Check if given page is parent of this node
     *
     * @param page Page path
     * @return True if it is
     */
    boolean isParent( String page );
}
