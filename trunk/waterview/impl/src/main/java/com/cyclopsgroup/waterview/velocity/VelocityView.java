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
package com.cyclopsgroup.waterview.velocity;

import org.apache.velocity.Template;

import com.cyclopsgroup.waterview.Context;
import com.cyclopsgroup.waterview.RunData;
import com.cyclopsgroup.waterview.spi.BaseModuleRunnable;
import com.cyclopsgroup.waterview.spi.View;

/**
 * Velocity view
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class VelocityView
    extends BaseModuleRunnable
    implements View
{
    private Template template;

    /**
     * Constructor for class VelocityView
     *
     * @param template Velocity template
     * @param modulePath Module path
     */
    public VelocityView( Template template, String modulePath )
    {
        super( modulePath );
        this.template = template;
    }

    /**
     * Overwrite or implement method render()
     * @see com.cyclopsgroup.waterview.spi.View#render(com.cyclopsgroup.waterview.RunData, com.cyclopsgroup.waterview.Context)
     */
    public void render( RunData runtime, Context viewContext )
        throws Exception
    {
        runModule( runtime, viewContext );
        if ( template == null )
        {
            return;
        }
        VelocityContextAdapter vca = new VelocityContextAdapter( viewContext );
        template.merge( vca, runtime.getOutput() );
    }
}
