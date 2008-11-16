/* ==========================================================================
 * Copyright 2002-2004 Cyclops Group Community
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
package com.cyclopsgroup.waterview.servlet;

import javax.servlet.http.HttpSession;

import com.cyclopsgroup.cyclib.Context;
import com.cyclopsgroup.waterview.UIRuntimeUser;

/**
 * Adapter user class
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class SessionUserAdapter implements UIRuntimeUser
{

    private Context context;

    private HttpSession session;

    SessionUserAdapter(Context context, HttpSession session)
    {
        this.context = context;
        this.session = session;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.UIRuntimeUser#getContext()
     */
    public Context getContext()
    {
        return context;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.UIRuntimeUser#getId()
     */
    public String getId()
    {
        return session.getId();
    }
}
