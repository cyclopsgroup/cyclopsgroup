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

import java.sql.Connection;

import org.hibernate.dialect.Dialect;
import org.hibernate.tool.hbm2ddl.DatabaseMetadata;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 */
public class UpdateSchemaTag
    extends ExecuteSqlsTagBase
{
    /**
     * Override method getSqls in class UpdateSchemaTag
     *
     * @see com.cyclopsgroup.tornado.hibernate.taglib.ExecuteSqlsTagBase#getSqls(com.cyclopsgroup.tornado.hibernate.taglib.HibernateTag)
     */
    protected String[] getSqls( HibernateTag hibernate )
        throws Exception
    {
        Dialect dialect = Dialect.getDialect( hibernate.getHibernateConfiguration().getProperties() );
        Connection dbcon = hibernate.getConnection();
        DatabaseMetadata dm = new DatabaseMetadata( dbcon, dialect );
        return hibernate.getHibernateConfiguration().generateSchemaUpdateScript( dialect, dm );
    }
}
