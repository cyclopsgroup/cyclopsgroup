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
package com.cyclopsgroup.amulets.cyclops;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Utility to group elements with bags
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class BaggedList
{
    private LinkedList bags = new LinkedList();

    private int bagSize = 3;

    /**
     * Add object
     *
     * @param object
     */
    public void add(Object object)
    {
        boolean createNewBag = bags.isEmpty();
        List lastBag = bags.isEmpty() ? Collections.EMPTY_LIST : (List) bags
                .getLast();
        if (lastBag.size() == getBagSize())
        {
            createNewBag = true;
        }
        List currentBag = createNewBag ? new ArrayList(3) : lastBag;
        currentBag.add(object);
        if (createNewBag)
        {
            bags.addLast(currentBag);
        }
    }

    /**
     * Get list of bags
     *
     * @return List of bags
     */
    public List getBags()
    {
        return Collections.unmodifiableList(bags);
    }

    /**
     * Getter method for bagSize
     *
     * @return Returns the bagSize.
     */
    public int getBagSize()
    {
        return bagSize;
    }

    /**
     * Setter method for bagSize
     *
     * @param bagSize The bagSize to set.
     */
    public void setBagSize(int bagSize)
    {
        this.bagSize = bagSize;
    }
}