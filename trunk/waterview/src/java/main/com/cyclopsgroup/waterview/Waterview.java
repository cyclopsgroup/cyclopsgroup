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

import java.util.Properties;

/**
 * Waterview component
 * 
 * @author <a href="mailto:jiiaqi@yahoo.com">Jiaqi Guo </a>
 */
public interface Waterview
{
    /** Role name of this component in container */
    String ROLE = Waterview.class.getName();

    Resolver getDefaultResolver();

    /**
     * Method getProperties() in class Waterview
     * 
     * @return
     */
    Properties getProperties();

    Resolver getResolver(String extension);

    /**
     * Process request
     * 
     * @param runtime Runtime info
     * @throws Exception Throw it out
     */
    void process(UIRuntime runtime) throws Exception;
}