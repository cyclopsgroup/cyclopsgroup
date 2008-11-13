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

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.map.ListOrderedMap;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 * 
 * Form object
 */
public class Form
{
    private Map<String, Field> fields = ListOrderedMap.decorate( new HashMap<String, Field>() );

    private String id;

    /**
     * Constructor for class Form
     *
     * @param id Unique form id
     */
    public Form( String id )
    {
        this.id = id;
    }

    public Map<String, Field> getFields()
    {
        return fields;
    }

    public void addField( Field field )
    {
        fields.put( field.getName(), field );
    }

    /**
     * Get form id
     *
     * @return Form unique id
     */
    public String getId()
    {
        return id;
    }
}