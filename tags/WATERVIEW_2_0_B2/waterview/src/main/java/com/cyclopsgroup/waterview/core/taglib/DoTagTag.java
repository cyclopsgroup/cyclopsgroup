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
package com.cyclopsgroup.waterview.core.taglib;

import org.apache.commons.jelly.JellyContext;
import org.apache.commons.jelly.Tag;
import org.apache.commons.jelly.XMLOutput;

import com.cyclopsgroup.waterview.utils.TagSupport;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Tag to do tag
 */
public class DoTagTag extends TagSupport
{
    private Tag tag;

    /**
     * Getter method for property tag
     *
     * @return Returns the tag.
     */
    public Tag getTag()
    {
        return tag;
    }

    /**
     * Overwrite or implement method processTag()
     *
     * @see com.cyclopsgroup.waterview.utils.TagSupportBase#processTag(org.apache.commons.jelly.XMLOutput)
     */
    protected void processTag(XMLOutput output) throws Exception
    {
        requireAttribute("tag");
        JellyContext myContext = getTag().getContext();
        getTag().setContext(getContext());
        getTag().doTag(output);
        getTag().setContext(myContext);
    }

    /**
     * Setter method for property tag
     *
     * @param tag The tag to set.
     */
    public void setTag(Tag tag)
    {
        this.tag = tag;
    }
}
