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
package com.cyclopsgroup.courselist.ui.action.teacher;

import com.cyclopsgroup.courselist.entity.StudentCourse;
import com.cyclopsgroup.tornado.persist.PersistenceManager;
import com.cyclopsgroup.waterview.Action;
import com.cyclopsgroup.waterview.ActionContext;
import com.cyclopsgroup.waterview.BaseServiceable;
import com.cyclopsgroup.waterview.RuntimeData;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Remove student record
 */
public class SaveScoreAction
    extends BaseServiceable
    implements Action
{
    /**
     * Overwrite or implement method execute()
     *
     * @see com.cyclopsgroup.waterview.Action#execute(com.cyclopsgroup.waterview.RuntimeData, com.cyclopsgroup.waterview.ActionContext)
     */
    public void execute( RuntimeData data, ActionContext context )
        throws Exception
    {
        String[] ids = data.getParameters().getStrings( "sc_id" );
        PersistenceManager persist = (PersistenceManager) lookup( PersistenceManager.ROLE );
        for ( int i = 0; i < ids.length; i++ )
        {
            String id = ids[i];
            StudentCourse sc = (StudentCourse) persist.load( StudentCourse.class, id );
            float score = data.getParameters().getFloat( "score_" + id );
            sc.setScore( score );
            persist.update( sc );
        }
        context.addMessage( ids.length + " scores are updated" );
    }
}
