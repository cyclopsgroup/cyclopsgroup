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
package com.cyclopsgroup.courselist;

import java.io.Serializable;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 * 
 * Course entity
 */
public class Course implements Serializable
{
    /** Empty array */
    public static final Course[] EMPTY_ARRAY = new Course[0];

    private String coRequisite;

    private String description;

    private String number;

    private String prefix;

    private String prerequisite;

    private String title;

    /**
     * Getter method for field coRequisite
     *
     * @return Returns the coRequisite.
     */
    public String getCoRequisite()
    {
        return coRequisite;
    }

    /**
     * Getter method for field description
     *
     * @return Returns the description.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Getter method for field number
     *
     * @return Returns the number.
     */
    public String getNumber()
    {
        return number;
    }

    /**
     * Getter method for field prefix
     *
     * @return Returns the prefix.
     */
    public String getPrefix()
    {
        return prefix;
    }

    /**
     * Getter method for field prerequisite
     *
     * @return Returns the prerequisite.
     */
    public String getPrerequisite()
    {
        return prerequisite;
    }

    /**
     * Getter method for field title
     *
     * @return Returns the title.
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * Setter method for field coRequisite
     *
     * @param coRequisite The coRequisite to set.
     */
    public void setCoRequisite(String coRequisite)
    {
        this.coRequisite = coRequisite;
    }

    /**
     * Setter method for field description
     *
     * @param description The description to set.
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * Setter method for field number
     *
     * @param number The number to set.
     */
    public void setNumber(String number)
    {
        this.number = number;
    }

    /**
     * Setter method for field prefix
     *
     * @param prefix The prefix to set.
     */
    public void setPrefix(String prefix)
    {
        this.prefix = prefix;
    }

    /**
     * Setter method for field prerequisite
     *
     * @param prerequisite The prerequisite to set.
     */
    public void setPrerequisite(String prerequisite)
    {
        this.prerequisite = prerequisite;
    }

    /**
     * Setter method for field title
     *
     * @param title The title to set.
     */
    public void setTitle(String title)
    {
        this.title = title;
    }
}
