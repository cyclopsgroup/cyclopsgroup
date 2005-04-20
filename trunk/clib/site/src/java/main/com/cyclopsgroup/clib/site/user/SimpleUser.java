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
package com.cyclopsgroup.clib.site.user;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/**
 * TODO Add javadoc for this class
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class SimpleUser implements User
{
    private String emailAddress;

    private String fullName;

    private String name;

    private Map otherAttributes = new HashMap();

    private Locale preferedLocale = Locale.getDefault();

    private TimeZone timeZone = TimeZone.getDefault();

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.clib.site.user.User#getEmailAddress()
     */
    public String getEmailAddress()
    {
        return emailAddress;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.clib.site.user.User#getFullName()
     */
    public String getFullName()
    {
        return fullName;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.clib.site.user.User#getName()
     */
    public String getName()
    {
        return name;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.clib.site.user.User#getOtherAttributes()
     */
    public Map getOtherAttributes()
    {
        return otherAttributes;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.clib.site.user.User#getPreferedLocale()
     */
    public Locale getPreferedLocale()
    {
        return preferedLocale;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.clib.site.user.User#getTimeZone()
     */
    public TimeZone getTimeZone()
    {
        return timeZone;
    }

    /**
     * Setter method for emailAddress
     *
     * @param emailAddress The emailAddress to set.
     */
    public void setEmailAddress(String emailAddress)
    {
        this.emailAddress = emailAddress;
    }

    /**
     * Setter method for fullName
     *
     * @param fullName The fullName to set.
     */
    public void setFullName(String fullName)
    {
        this.fullName = fullName;
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

    /**
     * Setter method for preferedLocale
     *
     * @param preferedLocale The preferedLocale to set.
     */
    public void setPreferedLocale(Locale preferedLocale)
    {
        this.preferedLocale = preferedLocale;
    }

    /**
     * Setter method for timeZone
     *
     * @param timeZone The timeZone to set.
     */
    public void setTimeZone(TimeZone timeZone)
    {
        this.timeZone = timeZone;
    }
}
