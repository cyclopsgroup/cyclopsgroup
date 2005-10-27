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
package com.cyclopsgroup.waterview.ui.sysview;

import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.waterview.Context;
import com.cyclopsgroup.waterview.Module;
import com.cyclopsgroup.waterview.RuntimeData;
import com.cyclopsgroup.waterview.web.Field;
import com.cyclopsgroup.waterview.web.Form;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * View to display form validation result
 */
public class FormValidation
    implements Module
{
    /**
     * Overwrite or implement method execute()
     *
     * @see com.cyclopsgroup.waterview.Module#execute(com.cyclopsgroup.waterview.RuntimeData, com.cyclopsgroup.waterview.Context)
     */
    public void execute( RuntimeData data, Context context )
        throws Exception
    {
        String formId = data.getParameters().getString( "form_id" );
        Form form = (Form) data.getSessionContext().get( formId );
        Field[] fiels = form.getFields();
        for ( int i = 0; i < fiels.length; i++ )
        {
            Field field = fiels[i];
            String value = data.getParameters().getString( field.getName() );
            field.setValue( value );
            field.validate();
            StringBuffer sb = new StringBuffer( field.getName() ).append( ':' );
            if ( StringUtils.isEmpty( field.getTitle() ) )
            {
                sb.append( field.getName() );
            }
            else
            {
                sb.append( field.getTitle() );
            }
            sb.append( ':' );
            if ( field.isInvalid() )
            {
                sb.append( field.getErrorMessage() );
            }
            else
            {
                sb.append( "ok" );
            }
            sb.append( '|' );
            data.getOutput().print( sb.toString() );
        }
        data.getOutput().flush();
    }
}