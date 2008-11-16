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
package com.cyclopsgroup.waterview.ui.view.system.status;

import java.util.Locale;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.waterview.BaseServiceable;
import com.cyclopsgroup.waterview.Context;
import com.cyclopsgroup.waterview.Module;
import com.cyclopsgroup.waterview.RuntimeData;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * View to set locale
 */
public class SetLocale
    extends BaseServiceable
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
        Locale[] locales = Locale.getAvailableLocales();
        TreeMap availableLocales = new TreeMap();
        for ( int i = 0; i < locales.length; i++ )
        {
            Locale locale = locales[i];
            if ( StringUtils.isEmpty( locale.getCountry() ) )
            {
                continue;
            }
            String key = locale.getCountry() + '|' + locale.getLanguage();
            availableLocales.put( key, locale );
        }
        context.put( "availableLocales", availableLocales.values() );
        context.put( "currentLocale", data.getSessionContext().get( RuntimeData.LOCALE_NAME ) );
    }
}
