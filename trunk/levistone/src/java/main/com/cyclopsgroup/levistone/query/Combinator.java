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

import org.apache.commons.lang.enum.Enum;

/**
 * Combinator of condition
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public final class Combinator extends Enum
{
    /** And combinator */
    public static final Combinator AND = new Combinator("and");

    /** Or combinator */
    public static final Combinator OR = new Combinator("or");

    /**
     * Get instance of combinator with given value
     *
     * @param value Given value
     * @return Combinator object
     */
    public static Combinator valueOf(String value)
    {
        return (Combinator) Enum.getEnum(Combinator.class, value);
    }

    /**
     * Constructor for class Combinator
     *
     * @param value
     */
    public Combinator(String value)
    {
        super(value);
    }
}