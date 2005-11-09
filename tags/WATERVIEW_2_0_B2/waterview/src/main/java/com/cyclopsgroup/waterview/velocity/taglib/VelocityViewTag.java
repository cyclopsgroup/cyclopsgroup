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
package com.cyclopsgroup.waterview.velocity.taglib;

import com.cyclopsgroup.waterview.Path;
import com.cyclopsgroup.waterview.spi.ModuleService;
import com.cyclopsgroup.waterview.spi.View;
import com.cyclopsgroup.waterview.spi.taglib.BaseViewTag;
import com.cyclopsgroup.waterview.velocity.VelocityEngine;
import com.cyclopsgroup.waterview.velocity.VelocityView;

/**
 * Velocity view tag
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class VelocityViewTag
    extends BaseViewTag
{
    private String template;

    /**
     * Overwrite or implement method createView()
     *
     * @see com.cyclopsgroup.waterview.spi.taglib.BaseViewTag#createView()
     */
    protected View createView()
        throws Exception
    {
        requireAttribute( "template" );
        VelocityEngine ve = (VelocityEngine) getServiceManager().lookup( VelocityEngine.ROLE );

        ModuleService mm = (ModuleService) getServiceManager().lookup( ModuleService.ROLE );
        Path path = mm.parsePath( getTemplate() );
        return new VelocityView( ve.getTemplate( path.getPackage(), path.getPath() ), getTemplate() );
    }

    /**
     * Getter method for template
     *
     * @return Returns the template.
     */
    public String getTemplate()
    {
        return template;
    }

    /**
     * Setter method for template
     *
     * @param template The template to set.
     */
    public void setTemplate( String template )
    {
        this.template = template;
    }
}
