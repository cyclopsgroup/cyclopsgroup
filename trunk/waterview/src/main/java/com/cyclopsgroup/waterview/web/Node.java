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
package com.cyclopsgroup.waterview.web;

import com.cyclopsgroup.waterview.Attributes;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 * 
 * Generic tree node interface
 */
public interface Node
{
    /** Empty array */
    Node[] EMPTY_ARRAY = new Node[0];

    /**
     * Get attributes of it
     *
     * @return Attributes object
     */
    Attributes getAttributes();

    /**
     * Get unique ID of this node
     *
     * @return Unique node id
     */
    String getNodeId();

    /**
     * Get parent node
     *
     * @return Parent node
     */
    Node getParentNode();

    /**
     * If node is end
     *
     * @return True if it's end
     */
    boolean isEnd();
}
