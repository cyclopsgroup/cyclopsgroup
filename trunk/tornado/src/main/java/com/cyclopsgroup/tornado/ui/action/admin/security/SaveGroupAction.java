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
package com.cyclopsgroup.tornado.ui.action.admin.security;

import com.cyclopsgroup.tornado.persist.PersistenceManager;
import com.cyclopsgroup.tornado.security.entity.Group;
import com.cyclopsgroup.waterview.Action;
import com.cyclopsgroup.waterview.ActionContext;
import com.cyclopsgroup.waterview.BaseServiceable;
import com.cyclopsgroup.waterview.RuntimeData;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Action to save group changes
 */
public class SaveGroupAction
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
        String[] groupIds = data.getParams().getStrings( "group_id" );
        if ( groupIds.length == 0 )
        {
            return;
        }
        PersistenceManager persist = (PersistenceManager) lookupComponent( PersistenceManager.ROLE );
        for ( int i = 0; i < groupIds.length; i++ )
        {
            String groupId = groupIds[i];
            Group group = (Group) persist.load( Group.class, groupId );
            String newDescription = data.getParams().getString( "description_" + groupId );
            group.setDescription( newDescription );
            persist.update( group );
        }
        context.addMessage( groupIds.length + " groups are updated" );
    }
}