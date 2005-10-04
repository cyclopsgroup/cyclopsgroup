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
package com.cyclopsgroup.tornado.ui.view.user;

import java.util.Locale;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.tornado.persist.PersistenceManager;
import com.cyclopsgroup.tornado.security.RuntimeUser;
import com.cyclopsgroup.tornado.security.entity.User;
import com.cyclopsgroup.waterview.BaseServiceable;
import com.cyclopsgroup.waterview.Context;
import com.cyclopsgroup.waterview.DefaultSelectOption;
import com.cyclopsgroup.waterview.Module;
import com.cyclopsgroup.waterview.RuntimeData;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 */
public class UserProfile
    extends BaseServiceable
    implements Module
{

    /**
     * Override method execute in class UserProfile
     *
     * @see com.cyclopsgroup.waterview.Module#execute(com.cyclopsgroup.waterview.RuntimeData, com.cyclopsgroup.waterview.Context)
     */
    public void execute( RuntimeData data, Context context )
        throws Exception
    {
        RuntimeUser user = RuntimeUser.getInstance( data );
        PersistenceManager persist = (PersistenceManager) lookupComponent( PersistenceManager.ROLE );
        User u = (User) persist.load( User.class, user.getId() );
        context.put( "userObject", u );

        TreeMap languages = new TreeMap();
        TreeMap countries = new TreeMap();
        Locale[] availableLocales = Locale.getAvailableLocales();
        for ( int i = 0; i < availableLocales.length; i++ )
        {
            Locale locale = availableLocales[i];
            DefaultSelectOption lang = new DefaultSelectOption( locale.getLanguage(), locale.getDisplayLanguage( data
                .getLocale() )
                + '(' + locale.getLanguage() + ')' );
            languages.put( locale.getLanguage(), lang );

            if ( StringUtils.isNotEmpty( locale.getCountry() ) )
            {
                DefaultSelectOption cout = new DefaultSelectOption( locale.getCountry(), locale.getDisplayCountry( data
                    .getLocale() )
                    + '(' + locale.getCountry() + ')' );
                countries.put( locale.getCountry(), cout );
            }
        }
        context.put( "countries", countries.values() );
        context.put( "languages", languages.values() );
    }
}
