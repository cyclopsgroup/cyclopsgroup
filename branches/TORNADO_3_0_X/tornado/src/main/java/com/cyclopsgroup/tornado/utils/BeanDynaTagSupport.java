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
package com.cyclopsgroup.tornado.utils;

import java.beans.PropertyDescriptor;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.jelly.JellyTagException;

import com.cyclopsgroup.waterview.utils.DynaTagSupport;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Dyna tag support which is sensitive to a bean class
 */
public abstract class BeanDynaTagSupport
    extends DynaTagSupport
{
    private Class beanClass;

    /**
     * Overwrite or implement method getAttributeType()
     *
     * @see com.cyclopsgroup.waterview.utils.DynaTagSupport#getAttributeType(java.lang.String)
     */
    public Class getAttributeType( String attributeName )
        throws JellyTagException
    {
        if ( beanClass == null )
        {
            return super.getAttributeType( attributeName );
        }
        PropertyDescriptor[] descriptors = PropertyUtils.getPropertyDescriptors( beanClass );
        for ( int i = 0; i < descriptors.length; i++ )
        {
            PropertyDescriptor descriptor = descriptors[i];
            if ( descriptor.getName().equals( attributeName ) )
            {
                return descriptor.getPropertyType();
            }
        }
        return super.getAttributeType( attributeName );
    }

    /**
     * Getter method for property beanClass
     *
     * @return Returns the beanClass.
     */
    public Class getBeanClass()
    {
        return beanClass;
    }

    /**
     * Setter method for property beanClass
     *
     * @param beanClass The beanClass to set.
     */
    public void setBeanClass( Class beanClass )
    {
        this.beanClass = beanClass;
    }
}
