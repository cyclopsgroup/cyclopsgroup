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

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.commons.jelly.JellyContext;
import org.apache.commons.jelly.JellyException;
import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.Script;
import org.apache.commons.jelly.XMLOutput;

import com.cyclopsgroup.waterview.jelly.JellyEngine;
import com.cyclopsgroup.waterview.utils.TagSupport;
import com.cyclopsgroup.waterview.utils.TypeUtils;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Select tag
 */
public class SelectTag extends TagSupport
{
    private Object items;

    private Map options;

    /**
     * Add option tag
     *
     * @param option Option tag
     */
    public void addOption(OptionTag option)
    {
        options.put(option.getValue(), option);
    }

    /**
     * Getter method for property items
     *
     * @return Returns the items.
     */
    public Object getItems()
    {
        return items;
    }

    /**
     * Get collection of all options
     *
     * @return Collection of options
     */
    public Collection getOptions()
    {
        return options.values();
    }

    /**
     * Overwrite or implement method processTag()
     *
     * @see com.cyclopsgroup.waterview.utils.TagSupportBase#processTag(org.apache.commons.jelly.XMLOutput)
     */
    protected void processTag(XMLOutput output) throws Exception
    {
        FieldTag fieldTag = (FieldTag) requireParent(FieldTag.class);
        options = ListOrderedMap.decorate(new HashMap());

        if (getItems() != null)
        {
            Iterator i = Collections.EMPTY_LIST.iterator();
            if (TypeUtils.isIteratable(getItems()))
            {
                i = TypeUtils.iterate(getItems());
            }
            else if (getItems() instanceof Map)
            {
                i = ((Map) getItems()).entrySet().iterator();
            }
            while (i.hasNext())
            {
                Object item = i.next();
                String name = null;
                Option option = null;
                if (item instanceof Map.Entry)
                {
                    Map.Entry e = (Map.Entry) item;
                    option = new Option(e.getKey(), e.getValue());
                    name = TypeUtils.toString(e.getKey());
                }
                else
                {
                    name = TypeUtils.toString(item);
                    option = new Option(name, item);
                }
                options.put(name, option);
            }
        }

        invokeBody(output);

        JellyEngine je = (JellyEngine) getServiceManager().lookup(
                JellyEngine.ROLE);
        final Script script = je.getScript("/waterview/FormSelectInput.jelly");
        Script s = new Script()
        {

            public Script compile() throws JellyException
            {
                return this;
            }

            public void run(JellyContext context, XMLOutput output)
                    throws JellyTagException
            {
                context.setVariable("selectTag", SelectTag.this);
                script.run(context, output);
            }
        };
        fieldTag.setBodyScript(s);
    }

    /**
     * Setter method for property items
     *
     * @param items The items to set.
     */
    public void setItems(Object items)
    {
        this.items = items;
    }
}
