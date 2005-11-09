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

import org.apache.commons.jelly.XMLOutput;

import com.cyclopsgroup.waterview.utils.TagSupport;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Tag to create a unique ID
 */
public class CreateUniqueIdTag
    extends TagSupport
{
    private static int rollingNumber = 0;

    private String var;

    /**
     * Getter method for property var
     *
     * @return Returns the var.
     */
    public String getVar()
    {
        return var;
    }

    protected void processTag( XMLOutput output )
        throws Exception
    {
        requireAttribute( "var" );
        synchronized ( CreateUniqueIdTag.class )
        {
            rollingNumber = ( rollingNumber + 1 ) % 1024768;
        }
        long number = System.currentTimeMillis() * 1024768 + rollingNumber;
        String id = "t" + number;
        getContext().setVariable( getVar(), id );
    }

    /**
     * Setter method for property var
     *
     * @param var The var to set.
     */
    public void setVar( String var )
    {
        this.var = var;
    }
}
