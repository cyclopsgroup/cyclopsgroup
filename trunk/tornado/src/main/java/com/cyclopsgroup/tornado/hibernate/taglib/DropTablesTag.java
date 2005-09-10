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

import org.hibernate.dialect.Dialect;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Tag to drop all tables
 */
public class DropTablesTag extends ExecuteSqlsTagBase
{
    /**
     * Override method getSqls in class DropTablesTag
     *
     * @see com.cyclopsgroup.tornado.hibernate.taglib.ExecuteSqlsTagBase#getSqls(com.cyclopsgroup.tornado.hibernate.taglib.HibernateTag)
     */
    protected String[] getSqls(HibernateTag hibernate) throws Exception
    {
        Dialect dialect = Dialect.getDialect(hibernate
                .getHibernateConfiguration().getProperties());
        return hibernate.getHibernateConfiguration().generateDropSchemaScript(
                dialect);
    }
}
