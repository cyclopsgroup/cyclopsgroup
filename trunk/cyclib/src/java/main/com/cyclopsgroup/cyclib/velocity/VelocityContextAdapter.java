/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.cyclib.velocity;

import java.util.HashSet;
import java.util.Iterator;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.cyclib.Context;

/**
 * @author <a href="mailto:jiaqi@evavi.com">Jiaqi Guo</a>
 *
 * Velocity context adapter for evavi context
 */
public class VelocityContextAdapter implements
        org.apache.velocity.context.Context
{
    private Context context;

    /**
     * Constructor for class VelocityContextAdapter
     *
     * @param context Evavi context object
     */
    public VelocityContextAdapter(Context context)
    {
        this.context = context;
    }

    /**
     * Override method containsKey()
     *
     * @see org.apache.velocity.context.Context#containsKey(java.lang.Object)
     */
    public boolean containsKey(Object key)
    {
        for (Iterator i = context.keys(); i.hasNext();)
        {
            String variableName = (String) i.next();
            if (StringUtils.equals((String) key, variableName))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Override method get()
     *
     * @see org.apache.velocity.context.Context#get(java.lang.String)
     */
    public Object get(String name)
    {
        return context.get(name);
    }

    /**
     * Override method getKeys()
     *
     * @see org.apache.velocity.context.Context#getKeys()
     */
    public Object[] getKeys()
    {
        HashSet keys = new HashSet();
        CollectionUtils.addAll(keys, context.keys());
        return keys.toArray();
    }

    /**
     * Override method put()
     *
     * @see org.apache.velocity.context.Context#put(java.lang.String, java.lang.Object)
     */
    public Object put(String name, Object value)
    {
        context.put(name, value);
        return value;
    }

    /**
     * Override method remove()
     *
     * @see org.apache.velocity.context.Context#remove(java.lang.Object)
     */
    public Object remove(Object name)
    {
        if (name == null)
        {
            return null;
        }
        String key = (String) name;
        Object ret = get(key);
        context.remove(name.toString());
        return ret;
    }
}