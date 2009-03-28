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
package com.cyclopsgroup.levistone.torque;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import org.apache.torque.om.ComboKey;
import org.apache.torque.om.DateKey;
import org.apache.torque.om.NumberKey;
import org.apache.torque.om.ObjectKey;
import org.apache.torque.om.SimpleKey;
import org.apache.torque.om.StringKey;

/**
 * Utilities for torque
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public final class TorqueUtils
{

    /**
     * Convert object to torque key
     *
     * @param object Key object
     * @return Torque object key
     */
    public static ObjectKey object2TorqueKey(Object object)
    {
        if (object == null)
        {
            return null;
        }
        if (object instanceof String)
        {
            return new StringKey((String) object);
        }
        else if (object instanceof Number)
        {
            return new NumberKey((Number) object);
        }
        else if (object instanceof Date)
        {
            return new DateKey((Date) object);
        }
        else if (object instanceof Object[])
        {
            Object[] objects = (Object[]) object;
            SimpleKey[] keys = new SimpleKey[objects.length];
            for (int i = 0; i < objects.length; i++)
            {
                Object keyObject = objects[i];
                keys[i] = (SimpleKey) object2TorqueKey(keyObject);
            }
            return new ComboKey(keys);
        }
        else if (object instanceof Collection)
        {
            Collection c = (Collection) object;
            SimpleKey[] keys = new SimpleKey[c.size()];
            Iterator it = c.iterator();
            for (int i = 0; it.hasNext(); i++)
            {
                Object keyObject = it.next();
                keys[i] = (SimpleKey) object2TorqueKey(keyObject);
            }
            return new ComboKey(keys);
        }
        throw new IllegalArgumentException("Key object " + object
                + " is not regonizable by torque");
    }

    /**
     * Convert torque key to primitive object
     *
     * @param ok Torque object key
     * @return Primitive java object or object array
     */
    public static Object torqueKey2Object(ObjectKey ok)
    {
        if (ok == null)
        {
            return null;
        }
        if (ok instanceof ComboKey)
        {
            SimpleKey[] keys = (SimpleKey[]) ((ComboKey) ok).getValue();
            Object[] ret = new Object[keys.length];
            for (int i = 0; i < keys.length; i++)
            {
                SimpleKey sk = keys[i];
                ret[i] = torqueKey2Object(sk);
            }
            return ret;
        }
        return ok.getValue();
    }
}