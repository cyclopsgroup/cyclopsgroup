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
package com.cyclopsgroup.waterview.ui.action;

import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.waterview.Action;
import com.cyclopsgroup.waterview.ActionContext;
import com.cyclopsgroup.waterview.RunData;
import com.cyclopsgroup.waterview.utils.TypeUtils;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Action to change the variable in session
 */
public class ChangeSessionAttribute
    extends Action
{
    @Override
    public void execute( RunData data, ActionContext context )
        throws Exception
    {
        String name = data.getParameters().getString( "attribute_name" );
        String value = data.getParameters().getString( "attribute_value" );
        String type = data.getParameters().getString( "attribute_type", "string" );
        Object v = value;
        if ( StringUtils.isNotEmpty( type ) )
        {
            v = TypeUtils.convert( value, type );
        }
        data.getSessionContext().put( name, v );
        //context.addMessage( "Attribute is changed in session" );
    }
}