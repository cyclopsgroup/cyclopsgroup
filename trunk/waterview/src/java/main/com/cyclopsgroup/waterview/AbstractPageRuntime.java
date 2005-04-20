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
package com.cyclopsgroup.waterview;

import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

/**
 * Abstract base runtime class
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public abstract class AbstractPageRuntime implements PageRuntime
{
    private List actions = new LinkedList();

    private String page;

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.PageRuntime#getActions()
     */
    public List getActions()
    {
        return actions;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.PageRuntime#getOutput()
     */
    public PrintWriter getOutput()
    {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.PageRuntime#getPage()
     */
    public String getPage()
    {
        return page;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.PageRuntime#setPage(java.lang.String)
     */
    public void setPage(String page)
    {
        this.page = page;
    }
}
