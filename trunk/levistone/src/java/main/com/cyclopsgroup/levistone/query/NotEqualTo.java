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

/**
 * Equal comparison
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class NotEqualTo extends Comparison
{
    /**
     * Constructor for class NotEqual
     *
     * @param field Field object
     * @param value Value object
     */
    public NotEqualTo(Field field, Object value)
    {
        super(field, value, Comparator.NOT_EQUAL);
    }

    /**
     * Constructor for class NotEqual
     *
     * @param fieldName Field name
     * @param value Comparison value
     */
    public NotEqualTo(String fieldName, Object value)
    {
        super(fieldName, value, Comparator.NOT_EQUAL);
    }

    /**
     * Constructor for class NotEqual
     *
     * @param fieldName Field name
     * @param alias Alias name
     * @param value Comparison value
     */
    public NotEqualTo(String fieldName, String alias, Object value)
    {
        super(fieldName, alias, value, Comparator.NOT_EQUAL);
    }
}