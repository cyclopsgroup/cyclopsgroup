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
package com.cyclopsgroup.lloyd.spi;

import com.cyclopsgroup.lloyd.SecurityEntity;

/**
 * TODO Add javadoc for this class
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class DefaultSecurityEntity implements SecurityEntity
{
    private boolean disabled = false;

    private long id;

    private String name;

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.lloyd.SecurityEntity#getId()
     */
    public long getId()
    {
        return id;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.lloyd.SecurityEntity#getName()
     */
    public String getName()
    {
        return name;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.lloyd.SecurityEntity#isDisabled()
     */
    public boolean isDisabled()
    {
        return disabled;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.lloyd.SecurityEntity#setDisabled(boolean)
     */
    public void setDisabled(boolean disabled)
    {
        this.disabled = disabled;
    }

    /**
     * Setter method for id
     *
     * @param id The id to set.
     */
    public void setId(long id)
    {
        this.id = id;
    }

    /**
     * Setter method for name
     *
     * @param name The name to set.
     */
    public void setName(String name)
    {
        this.name = name;
    }
}
