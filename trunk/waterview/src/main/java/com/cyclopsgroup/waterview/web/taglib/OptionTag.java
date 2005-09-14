/* ==========================================================================
 * Copyright 2002-2005 Cyclops Group Community
 * 
 * Licensed under the COMMON DEVELOPMENT AND DISTRIBUTION LICENSE
 * (CDDL) Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.opensource.org/licenses/cddl1.txt
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * =========================================================================
 */
package com.cyclopsgroup.waterview.web.taglib;

import org.apache.commons.jelly.JellyException;
import org.apache.commons.jelly.XMLOutput;
import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.waterview.utils.TagSupport;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Select option tag
 */
public class OptionTag extends TagSupport
{
    private String value;

    /**
     * Get text of this tag
     *
     * @return Text content
     * @throws JellyException Throw it out
     */
    public String getText() throws JellyException
    {
        String text = getBodyText();
        if (StringUtils.isEmpty(text))
        {
            getValue();
        }
        return text;
    }

    /**
     * Getter method for property value
     *
     * @return Returns the value.
     */
    public String getValue()
    {
        return value;
    }

    /**
     * Overwrite or implement method processTag()
     *
     * @see com.cyclopsgroup.waterview.utils.TagSupportBase#processTag(org.apache.commons.jelly.XMLOutput)
     */
    protected void processTag(XMLOutput output) throws Exception
    {
        requireAttribute("value");
        SelectTag select = (SelectTag) requireParent(SelectTag.class);
        select.addOption(this);
    }

    /**
     * Setter method for property value
     *
     * @param value The value to set.
     */
    public void setValue(String value)
    {
        this.value = value;
    }
}
