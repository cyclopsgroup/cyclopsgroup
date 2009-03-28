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
import java.sql.Statement;

import org.apache.commons.jelly.XMLOutput;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cyclopsgroup.waterview.utils.TagSupport;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Tag to drop all tables
 */
public abstract class ExecuteSqlsTagBase
    extends TagSupport
{
    private boolean batched;

    private Log logger = LogFactory.getLog( ExecuteSqlsTagBase.class );

    /**
     * Getter method for batched
     *
     * @return Returns the batched.
     */
    public boolean isBatched()
    {
        return batched;
    }

    /**
     * Setter method for batched
     *
     * @param batched The batched to set.
     */
    public void setBatched( boolean batched )
    {
        this.batched = batched;
    }

    /**
     * Get sqls to execute
     * 
     * @param hibernate HibernateTag parent
     * @return Sqls to execute
     * @throws Exception Throw it out
     */
    protected abstract String[] getSqls( HibernateTag hibernate )
        throws Exception;

    /**
     * Override method processTag in class CreateTablesTag
     *
     * @see com.cyclopsgroup.waterview.utils.TagSupportBase#processTag(org.apache.commons.jelly.XMLOutput)
     */
    protected void processTag( XMLOutput output )
        throws Exception
    {
        HibernateTag hibernate = (HibernateTag) requireInside( HibernateTag.class );
        String[] sqls = getSqls( hibernate );
        Connection dbcon = hibernate.getConnection();
        Statement s = dbcon.createStatement();
        if ( isBatched() )
        {
            for ( int i = 0; i < sqls.length; i++ )
            {
                s.addBatch( sqls[i] );
            }
            try
            {
                s.executeBatch();
            }
            catch ( Exception e )
            {
                logger.debug( "Dropping table error", e );
            }
        }
        else
        {
            for ( int i = 0; i < sqls.length; i++ )
            {
                try
                {
                    s.execute( sqls[i] );
                }
                catch ( Exception e )
                {
                    logger.debug( "Dropping table error", e );
                }
            }
        }
        s.close();
    }
}
