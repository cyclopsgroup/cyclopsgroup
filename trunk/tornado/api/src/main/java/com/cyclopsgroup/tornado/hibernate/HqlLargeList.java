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

import com.cyclopsgroup.waterview.Attributes;
import com.cyclopsgroup.waterview.LargeList;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 */
public class HqlLargeList implements LargeList
{
    private class Parameter
    {
        private String name;

        private Type type;

        private Object value;

        private Parameter(String name, Type type, Object value)
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

    private static final String HQL_AND = " AND ";

    private static final String HQL_LIKE = " LIKE :";

    private String dataSource;

    private HibernateService hibernate;

    private String hql;

    private Map parameters = new HashMap();

    /**
     * Constructor for class HQLTabularData
     *
     * @param hql HQL language
     * @param hibernate Hibernate servcie
     */
    public HqlLargeList(String hql, HibernateService hibernate)
    {
        this(hql, hibernate, HibernateService.DEFAULT_DATASOURCE);
    }

    /**
     * Constructor for type QueryTableData
     *
     * @param hibernate
     * @param hql Hibernate query language
     * @param dataSource Data source name
     */
    public HqlLargeList(String hql, HibernateService hibernate,
            String dataSource)
    {
        this.hql = hql;
        this.hibernate = hibernate;
        this.dataSource = dataSource;
    }

    /**return
     * Add a parameter
     *
     * @param name Parameter name
     * @param type Parameter type
     * @param value Parameter value
     * @throws Exception Throw it out
     */
    public void addParameter(String name, String type, Object value)
            throws Exception
    {
        Type hibernateType = (Type) Hibernate.class
                .getField(type.toUpperCase()).get(null);
        addParameter(name, hibernateType, value);
    }

    /**
     * Add parameter
     *
     * @param name Parameter name
     * @param type Parameter type
     * @param value Parameter value
     */
    public void addParameter(String name, Type type, Object value)
    {
        Parameter p = new Parameter(name, type, value);
        parameters.put(name, p);
    }

    /**
     * Conveniet method to add a string attribute
     *
     * @param attributes Attributes parser
     * @param fieldName Field name
     */
    public void addStringCriterion(Attributes attributes, String fieldName)
    {
        String value = attributes.getString(fieldName);
        if (StringUtils.isEmpty(value))
        {
            return;
        }
        hql = new StringBuffer(hql).append(HQL_AND).append(fieldName).append(
                HQL_LIKE).append(fieldName).toString();
        addParameter(fieldName, Hibernate.STRING, '%' + value + '%');
    }

    /**
     * Overwrite or implement method getSize()
     *
     * @see com.cyclopsgroup.waterview.LargeList#getSize()
     */
    public int getSize() throws Exception
    {
        String countQuery = "SELECT COUNT(*) " + hql;
        Session s = hibernate.getSession(dataSource);
        Query query = s.createQuery(countQuery);
        HashSet parameterNames = new HashSet();
        CollectionUtils.addAll(parameterNames, query.getNamedParameters());
        for (Iterator i = parameters.values().iterator(); i.hasNext();)
        {
            Parameter p = (Parameter) i.next();
            if (parameterNames.contains(p.getName()))
            {
                query.setParameter(p.getName(), p.getValue(), p.getType());
            }
        }
        List result = query.list();
        if (result == null || result.isEmpty())
        {
            return -1;
        }
        Integer i = (Integer) result.get(0);
        return i.intValue();
    }

    /**
     * Overwrite or implement method iterate()
     *
     * @see com.cyclopsgroup.waterview.LargeList#iterate(int, int, com.cyclopsgroup.waterview.LargeList.Sorting[])
     */
    public Iterator iterate(int startPosition, int maxRecords,
            Sorting[] sortings) throws Exception
    {
        if (StringUtils.isEmpty(hql))
        {
            throw new IllegalStateException("query is still emtpy");
        }
        Session s = hibernate.getSession(dataSource);
        StringBuffer sb = new StringBuffer(hql);

        boolean first = true;
        for (int i = 0; i < sortings.length; i++)
        {
            Sorting sorting = sortings[i];
            if (first)
            {
                sb.append(" ORDER BY ");
                first = false;
            }
            else
            {
                sb.append(", ");
            }
            sb.append(sorting.getName());
            if (sorting.isDescending())
            {
                sb.append(" DESC");
            }
        }

        Query q = s.createQuery(sb.toString());
        HashSet parameterNames = new HashSet();
        CollectionUtils.addAll(parameterNames, q.getNamedParameters());
        for (Iterator i = parameters.values().iterator(); i.hasNext();)
        {
            Parameter p = (Parameter) i.next();
            if (parameterNames.contains(p.getName()))
            {
                q.setParameter(p.getName(), p.getValue(), p.getType());
            }
        }
        q.setFirstResult(startPosition);
        if (maxRecords > 0)
        {
            q.setMaxResults(maxRecords);
        }
        return q.iterate();
    }

    /**
     * Setter method for property hql
     *
     * @param hql The hql to set.
     */
    public void setHql(String hql)
    {
        this.hql = hql;
    }
}
