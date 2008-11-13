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
package com.cyclopsgroup.waterview;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 * 
 * Dummy fake context
 */

public class DummyContext
    implements Context
{
    /** static instance */
    public static final DummyContext INSTANCE = new DummyContext();

    private static final List<String> EMPTY_STRING_LIST = Collections.emptyList();

    /**
     * Overwrite or implement method get()
     *
     * @see com.cyclopsgroup.waterview.Context#get(java.lang.String)
     */
    public Object get( String name )
    {
        return null;
    }

    /**
     * Overwrite or implement method keys()
     *
     * @see com.cyclopsgroup.waterview.Context#keys()
     */
    public Iterator<String> keys()
    {
        return EMPTY_STRING_LIST.iterator();
    }

    /**
     * Overwrite or implement method put()
     *
     * @see com.cyclopsgroup.waterview.Context#put(java.lang.String, java.lang.Object)
     */
    public void put( String name, Object variable )
    {
        //do nothing
    }

    /**
     * Overwrite or implement method remove()
     *
     * @see com.cyclopsgroup.waterview.Context#remove(java.lang.String)
     */
    public void remove( String name )
    {
        //do nothing
    }
}
