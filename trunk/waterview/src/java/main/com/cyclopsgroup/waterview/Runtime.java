/* ==========================================================================
 * Copyright 2002-2004 Cyclops Group Community
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
package com.cyclopsgroup.waterview;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Runtime objects
 * 
 * @author <a href="mailto:jiiaqi@yahoo.com">Jiaqi Guo </a>
 */
public interface Runtime
{
    /**
     * Get original servlet request object
     * 
     * @return HttpServletRequest object
     */
    HttpServletRequest getHttpServletRequest();

    /**
     * Method getHttpServletResponse() in class Runtime
     * 
     * @return HttpServletResponse object
     */
    HttpServletResponse getHttpServletResponse();

    /**
     * Method getHttpSession() in class Runtime
     * 
     * @return HttpSession object
     */
    HttpSession getHttpSession();
}