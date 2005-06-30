/* ==========================================================================
 * Copyright 2002-2005 Cyclops Group Community
 * 
 * Licensed under the Open Software License, Version 2.1 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://opensource.org/licenses/osl-2.1.php
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * =========================================================================
 */
package com.cyclopsgroup.tornado.components.security.impl;

import org.apache.avalon.framework.logger.AbstractLogEnabled;

import com.cyclopsgroup.tornado.core.security.RuntimeUser;
import com.cyclopsgroup.tornado.core.security.SecurityManager;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Default implementation of security manager
 */
public class DefaultSecurityManager extends AbstractLogEnabled implements
        SecurityManager
{

    /**
     * Overwrite or implement method getRuntimeUser()
     * @see com.cyclopsgroup.tornado.core.security.SecurityManager#getRuntimeUser(java.lang.String)
     */
    public RuntimeUser getRuntimeUser(String sessionId) {
        // TODO Auto-generated method stub
        return null;
    }
}