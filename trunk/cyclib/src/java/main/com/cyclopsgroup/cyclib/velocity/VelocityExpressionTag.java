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
package com.cyclopsgroup.cyclib.velocity;

import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.MissingAttributeException;
import org.apache.commons.jelly.TagSupport;
import org.apache.commons.jelly.XMLOutput;
import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.cyclib.ExpressionHandler;
import com.cyclopsgroup.cyclib.jelly.TagUtils;

/**
 * velocity expression tag
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class VelocityExpressionTag extends TagSupport
{
    private String logtag;

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.commons.jelly.Tag#doTag(org.apache.commons.jelly.XMLOutput)
     */
    public void doTag(XMLOutput output) throws MissingAttributeException,
            JellyTagException
    {
        VelocityExpression ve = null;
        if (StringUtils.isEmpty(getLogtag()))
        {
            ve = new VelocityExpression(getBodyText());
        }
        else
        {
            ve = new VelocityExpression(getBodyText(), getLogtag());
        }
        TagUtils.requireParent(this, ExpressionHandler.class);
        ((ExpressionHandler) parent).handle(ve);
    }

    /**
     * Getter method for logtag
     *
     * @return Returns the logtag.
     */
    public String getLogtag()
    {
        return logtag;
    }

    /**
     * Setter method for logtag
     *
     * @param logtag The logtag to set.
     */
    public void setLogtag(String logtag)
    {
        this.logtag = logtag;
    }
}