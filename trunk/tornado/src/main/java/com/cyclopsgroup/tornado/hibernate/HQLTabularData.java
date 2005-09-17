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
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.Type;

import com.cyclopsgroup.waterview.web.Column;
import com.cyclopsgroup.waterview.web.ColumnSort;
import com.cyclopsgroup.waterview.web.Table;
import com.cyclopsgroup.waterview.web.TabularData;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 */
public class HQLTabularData
    implements TabularData
{
    private class Parameter
    {
        private String name;

        private Type type;

        private Object value;

        private Parameter( String name, Type type, Object value )
        {
            this.name = name;
            this.type = type;
            this.value = value;
        }

        /**
         * Getter method for property name
         *
         * @return Returns the name.
         */
        public String getName()
        {
            return name;
        }

        /**
         * Getter method for property type
         *
         * @return Returns the type.
         */
        public Type getType()
        {
            return type;
        }

        /**
         * Getter method for property value
         *
         * @return Returns the value.
         */
        public Object getValue()
        {
            return value;
        }
    }

    private String dataSource;

    private HibernateService hibernate;

    private Map parameters = new HashMap();

    private String hql;

    /**
     * Constructor for type QueryTableData
     *
     * @param hibernate
     * @param hql Hibernate query language
     * @param dataSource Data source name
     */
    public HQLTabularData( String hql, HibernateService hibernate, String dataSource )
    {
        this.hql = hql;
        this.hibernate = hibernate;
        this.dataSource = dataSource;
    }

    /**
     * Constructor for class HQLTabularData
     *
     * @param hql HQL language
     * @param hibernate Hibernate servcie
     */
    public HQLTabularData( String hql, HibernateService hibernate )
    {
        this( hql, hibernate, HibernateService.DEFAULT_DATASOURCE );
    }

    /**return
     * Add a parameter
     *
     * @param name Parameter name
     * @param type Parameter type
     * @param value Parameter value
     * @throws Exception Throw it out
     */
    public void addParameter( String name, String type, Object value )
        throws Exception
    {
        Type hibernateType = (Type) Hibernate.class.getField( type.toUpperCase() ).get( null );
        Parameter p = new Parameter( name, hibernateType, value );
        parameters.put( name, p );
    }

    /**
     * Override method getSize in class HQLTabularData
     *
     * @see com.cyclopsgroup.waterview.web.TabularData#getSize()
     */
    public int getSize()
        throws Exception
    {
        String countQuery = "SELECT COUNT(*) " + hql;
        Session s = hibernate.getSession( dataSource );
        Query query = s.createQuery( countQuery );
        HashSet parameterNames = new HashSet();
        CollectionUtils.addAll( parameterNames, query.getNamedParameters() );
        for ( Iterator i = parameters.values().iterator(); i.hasNext(); )
        {
            Parameter p = (Parameter) i.next();
            if ( parameterNames.contains( p.getName() ) )
            {
                query.setParameter( p.getName(), p.getValue(), p.getType() );
            }
        }
        List result = query.list();
        if ( result == null || result.isEmpty() )
        {
            return -1;
        }
        Integer i = (Integer) result.get( 0 );
        return i.intValue();
    }

    /**
     * Override method isCountable in class HQLTabularData
     *
     * @see com.cyclopsgroup.waterview.web.TabularData#isCountable()
     */
    public boolean isCountable()
    {
        return true;
    }

    /**
     * Override method openIterator in class HQLTabularData
     *
     * @see com.cyclopsgroup.waterview.web.TabularData#openIterator(com.cyclopsgroup.waterview.web.Table)
     */
    public Iterator openIterator( Table table )
        throws Exception
    {
        if ( StringUtils.isEmpty( hql ) )
        {
            throw new IllegalStateException( "query is still emtpy" );
        }
        Session s = hibernate.getSession( dataSource );
        StringBuffer sb = new StringBuffer( hql );

        String[] sortedColumns = table.getSortedColumns();
        boolean first = true;
        for ( int i = 0; i < sortedColumns.length; i++ )
        {
            String columnName = sortedColumns[i];
            Column column = table.getColumn( columnName );
            if ( column.getSort() == ColumnSort.ASC || column.getSort() == ColumnSort.DESC )
            {
                if ( first )
                {
                    sb.append( " ORDER BY " );
                    first = false;
                }
                else
                {
                    sb.append( ", " );
                }
                sb.append( columnName );
                if ( column.getSort() == ColumnSort.DESC )
                {
                    sb.append( " DESC" );
                }
            }
        }

        Query q = s.createQuery( sb.toString() );
        HashSet parameterNames = new HashSet();
        CollectionUtils.addAll( parameterNames, q.getNamedParameters() );
        for ( Iterator i = parameters.values().iterator(); i.hasNext(); )
        {
            Parameter p = (Parameter) i.next();
            if ( parameterNames.contains( p.getName() ) )
            {
                q.setParameter( p.getName(), p.getValue(), p.getType() );
            }
        }
        if ( table.getPageSize() > 0 )
        {
            q.setMaxResults( table.getPageSize() );
            q.setFirstResult( table.getPageSize() * table.getPageIndex() );
        }
        return q.iterate();
    }
}
