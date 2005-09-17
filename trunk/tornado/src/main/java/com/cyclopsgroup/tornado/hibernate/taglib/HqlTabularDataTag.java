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

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.XMLOutput;
import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.tornado.hibernate.HibernateService;
import com.cyclopsgroup.tornado.hibernate.HqlTabularData;
import com.cyclopsgroup.waterview.utils.TagSupport;
import com.cyclopsgroup.waterview.web.taglib.TableControlTag;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 */
public class HqlTabularDataTag
    extends TagSupport
{
    private String hql;

    private Set parameterTags = new HashSet();

    private String dataSource = HibernateService.DEFAULT_DATASOURCE;

    /**
     * Getter method for dataSource
     *
     * @return Returns the dataSource.
     */
    public String getDataSource()
    {
        return dataSource;
    }

    /**
     * Setter method for dataSource
     *
     * @param dataSource The dataSource to set.
     */
    public void setDataSource( String dataSource )
    {
        this.dataSource = dataSource;
    }

    void setHql( String hql )
    {
        this.hql = hql;
    }

    void addParameter( HqlParameterTag tag )
    {
        parameterTags.add( tag );
    }

    /**
     * Override method processTag in class HQLTabularDataTag
     *
     * @see com.cyclopsgroup.waterview.utils.TagSupportBase#processTag(org.apache.commons.jelly.XMLOutput)
     */
    protected void processTag( XMLOutput output )
        throws Exception
    {
        requireAttribute( "dataSource" );
        TableControlTag tableControl = (TableControlTag) requireParent( TableControlTag.class );
        invokeBody( output );
        if ( StringUtils.isEmpty( hql ) && parameterTags.isEmpty() )
        {
            hql = getBodyText();
        }
        if ( StringUtils.isEmpty( hql ) )
        {
            throw new JellyTagException( "HQL must be defined" );
        }
        HibernateService hibernate = (HibernateService) getServiceManager().lookup( HibernateService.ROLE );
        HqlTabularData data = new HqlTabularData( hql, hibernate, getDataSource() );
        for ( Iterator i = parameterTags.iterator(); i.hasNext(); )
        {
            HqlParameterTag param = (HqlParameterTag) i.next();
            String type = "char";
            if ( StringUtils.isNotEmpty( param.getType() ) )
            {
                type = param.getType();
            }
            data.addParameter( param.getName(), type, param.getValue() );
        }
        tableControl.setTabularData( data );
    }
}