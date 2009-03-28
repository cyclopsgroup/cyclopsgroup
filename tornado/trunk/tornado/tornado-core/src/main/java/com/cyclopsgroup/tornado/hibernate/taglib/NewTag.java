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
package com.cyclopsgroup.tornado.hibernate.taglib;

import java.beans.PropertyDescriptor;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.XMLOutput;
import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.waterview.utils.DynaTagSupport;
import com.cyclopsgroup.waterview.utils.TypeUtils;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Tag to create new datium
 */
public class NewTag
    extends DynaTagSupport
{
    private PropertyDescriptor[] descriptors;

    /**
     * Overwrite or implement method getAttributeType()
     *
     * @see com.cyclopsgroup.waterview.utils.DynaTagSupport#getAttributeType(java.lang.String)
     */
    public Class getAttributeType( String attributeName )
        throws JellyTagException
    {
        if ( attributeName.equals( "var" ) )
        {
            return String.class;
        }
        if ( descriptors == null )
        {
            ClassTag classTag = (ClassTag) requireParent( ClassTag.class );
            descriptors = PropertyUtils.getPropertyDescriptors( classTag.getEntityClass() );
        }
        for ( int i = 0; i < descriptors.length; i++ )
        {
            PropertyDescriptor descriptor = descriptors[i];
            if ( descriptor.getName().equals( attributeName ) )
            {
                return descriptor.getPropertyType();
            }
        }
        throw new JellyTagException( "Attribute " + attributeName + " is not supported" );
    }

    /**
     * Overwrite or implement method processTag()
     *
     * @see com.cyclopsgroup.waterview.utils.TagSupportBase#processTag(org.apache.commons.jelly.XMLOutput)
     */
    protected void processTag( XMLOutput output )
        throws Exception
    {
        ClassTag classTag = (ClassTag) requireParent( ClassTag.class );
        HibernateTag hibernateTag = (HibernateTag) requireInside( HibernateTag.class );

        Object entity = classTag.getEntityClass().newInstance();
        TypeUtils.getBeanUtils().populate( entity, getAttributeMap() );
        hibernateTag.getSession().save( entity );
        String var = (String) getAttributeMap().get( "var" );
        if ( StringUtils.isNotEmpty( var ) )
        {
            context.setVariable( var, entity );
            context.setVariable( var, "parent", entity );
        }
    }
}
