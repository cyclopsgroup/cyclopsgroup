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
package com.cyclopsgroup.tornado.portal.impl;

import com.cyclopsgroup.waterview.RuntimeData;
import com.cyclopsgroup.waterview.navigator.impl.DefaultNavigatorService;
import com.cyclopsgroup.waterview.web.RuntimeTreeNode;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Secure navigator service
 */
public class SecureNavigatorService
    extends DefaultNavigatorService
{
    /**
     * Overwrite or implement method doCreateRuntimeRoot()
     *
     * @see com.cyclopsgroup.waterview.navigator.impl.DefaultNavigatorService#doCreateRuntimeRoot(com.cyclopsgroup.waterview.RuntimeData)
     */
    protected RuntimeTreeNode doCreateRuntimeRoot( RuntimeData data )
    {
        return new SecureRuntimeNavigatorNode( null, getRootNode() );
    }
}
