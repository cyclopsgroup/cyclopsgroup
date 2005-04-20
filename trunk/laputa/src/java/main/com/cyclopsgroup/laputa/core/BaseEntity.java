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
package com.cyclopsgroup.laputa.core;

/**
 * Entity with a unique string id;
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class BaseEntity implements Entity
{

    /**
     * 
     * @uml.property name="disabled" multiplicity="(0 1)"
     */
    private boolean disabled;

    /**
     * 
     * @uml.property name="id" multiplicity="(0 1)"
     */
    private String id;


    /**
     * Get type of this entity
     *
     * @return Type name
     */
    public String getEntityType()
    {
        return getClass().getName();
    }

    /**
     * Getter method for id
     * 
     * @return Returns the id.
     * 
     * @uml.property name="id"
     */
    public String getId() {
        return id;
    }


    /**
     * Getter method for disabled
     *
     * @return Returns the disabled.
     */
    public boolean isDisabled()
    {
        return disabled;
    }

    /**
     * Setter method for disabled
     * 
     * @param disabled The disabled to set.
     * 
     * @uml.property name="disabled"
     */
    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    /**
     * Setter method for id
     * 
     * @param id The id to set.
     * 
     * @uml.property name="id"
     */
    public void setId(String id) {
        this.id = id;
    }

}
