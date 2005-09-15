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
package com.cyclopsgroup.tornado.hibernate;

import java.util.HashMap;
import java.util.Iterator;

import com.cyclopsgroup.waterview.web.Table;
import com.cyclopsgroup.waterview.web.TabularData;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 */
public class HQLTabularData
    implements TabularData
{
    private String hql;

    private HashMap parameters = new HashMap();

    /**
     * Override method getSize in class HQLTabularData
     *
     * @see com.cyclopsgroup.waterview.web.TabularData#getSize()
     */
    public int getSize()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    /**
     * Override method isCountable in class HQLTabularData
     *
     * @see com.cyclopsgroup.waterview.web.TabularData#isCountable()
     */
    public boolean isCountable()
    {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * Override method openIterator in class HQLTabularData
     *
     * @see com.cyclopsgroup.waterview.web.TabularData#openIterator(com.cyclopsgroup.waterview.web.Table)
     */
    public Iterator openIterator( Table table )
        throws Exception
    {
        // TODO Auto-generated method stub
        return null;
    }
}
