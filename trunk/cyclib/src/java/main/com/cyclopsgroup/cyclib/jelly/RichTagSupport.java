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
package com.cyclopsgroup.cyclib.jelly;

import org.apache.commons.jelly.DynaTagSupport;
import org.apache.commons.jelly.JellyTagException;

import com.cyclopsgroup.cyclib.DefaultValueParser;
import com.cyclopsgroup.cyclib.ValueParser;

/**
 * TagSupport with attribute support
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public abstract class RichTagSupport extends DynaTagSupport
{
    private ValueParser attributes = new DefaultValueParser();

    /**
     * Get attributes
     *
     * @return Attributes object
     */
    public ValueParser getAttributes()
    {
        return attributes;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.commons.jelly.DynaTag#setAttribute(java.lang.String, java.lang.Object)
     */
    public void setAttribute(String name, Object value)
            throws JellyTagException
    {
        if (value != null)
        {
            attributes.set(name, value.toString());
        }
    }
}