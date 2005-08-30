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

import com.cyclopsgroup.waterview.RuntimeData;
import com.cyclopsgroup.waterview.web.RuntimeTreeNode;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 * 
 * Home of the navigator
 */
public interface NavigatorHome
{
    /** Role name of component */
    String ROLE = NavigatorHome.class.getName();

    /**
     * Get node path to current page
     *
     * @param data Runtime data
     * @return Path list
     */
    NavigatorNode[] getPath(RuntimeData data);

    /**
     * Get root node
     *
     * @return Root node
     */
    NavigatorNode getRootNode();

    /**
     * Get runtime node of root
     *
     * @param data Runtime data
     * @return Runtime tree node
     * @throws Exception Throw it out
     */
    RuntimeTreeNode getRuntimeRootNode(RuntimeData data) throws Exception;

    /**
     * If given node is currently opened
     *
     * @param node Node object
     * @param data Runtime data object
     * @return True if node is opened
     */
    boolean isCurrent(NavigatorNode node, RuntimeData data);
}
