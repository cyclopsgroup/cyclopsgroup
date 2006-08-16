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
package com.cyclopsgroup.waterview.jelly.deftaglib;

import org.apache.commons.jelly.XMLOutput;

import com.cyclopsgroup.waterview.spi.ModuleService;
import com.cyclopsgroup.waterview.spi.taglib.TagSupport;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Tag to instroduce a tag package
 */
public class ModulePackageTag
    extends TagSupport
{
    private String alias;

    private String name;

    /**
     * Overwrite or implement method processTag()
     *
     * @see com.cyclopsgroup.waterview.utils.TagSupportBase#processTag(org.apache.commons.jelly.XMLOutput)
     */
    @Override
    protected void processTag( XMLOutput output )
        throws Exception
    {
        requireAttribute( "alias" );
        requireAttribute( "name" );

        ModuleService moduleManager = (ModuleService) getServiceManager().lookup( ModuleService.ROLE );
        moduleManager.registerPackage( getAlias(), getName() );
    }

    /**
     * @return Returns the alias.
     */
    public String getAlias()
    {
        return alias;
    }

    /**
     * @return Returns the name.
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param alias The alias to set.
     */
    public void setAlias( String alias )
    {
        this.alias = alias;
    }

    /**
     * @param name The name to set.
     */
    public void setName( String name )
    {
        this.name = name;
    }
}
