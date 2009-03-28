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
package com.cyclopsgroup.levistone.query;

import java.util.ArrayList;
import java.util.List;


/**
 * Combo condition
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class ComboCondition extends Condition
{
    /**
     * 
     * @uml.property name="combinator" 
     */
    private Combinator combinator;

    /**
     * 
     * @uml.property name="conditions" 
     */
    private List conditions = new ArrayList();

    /**
     * Constructor for class ComboCondition
     *
     * @param combinator Combinator object
     */
    public ComboCondition(Combinator combinator)
    {
        this.combinator = combinator;
    }

    /**
     * Add condition definition
     *
     * @param condition Condition object
     */
    public void addCondition(Condition condition)
    {
        conditions.add(condition);
    }

    /**
     * Getter method for combinator
     * 
     * @return Returns the combinator.
     * 
     * @uml.property name="combinator"
     */
    public Combinator getCombinator()
    {
        return combinator;
    }

    /**
     * Getter method for conditions
     * 
     * @return Returns the conditions.
     * 
     * @uml.property name="conditions"
     */
    public Condition[] getConditions()
    {
        return (Condition[]) conditions.toArray(Condition.EMPTY_ARRAY);
    }

}