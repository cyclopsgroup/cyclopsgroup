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
package com.cyclopsgroup.waterview.core;

import java.util.Map;

import org.apache.commons.collections.MultiHashMap;

import com.cyclopsgroup.waterview.ActionContext;
import com.cyclopsgroup.waterview.Link;
import com.cyclopsgroup.waterview.RuntimeData;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Internal implementation of action context
 */
final class DefaultActionContext implements ActionContext
{
    private Map errorInputMessages = new MultiHashMap();

    private Throwable failCause;

    private boolean failed = false;

    private String failMessage;

    private Link link;

    private String targetUrl;

    /**
     * Constructor for class DefaultActionContext
     *
     * @param data Current data
     */
    DefaultActionContext(RuntimeData data)
    {
        Link link = (Link) data.getRequestContext().get(Link.NAME);
        String url = link.setPage(data.getPage()).addQueryString(
                data.getQueryString()).toString();
        setTargetUrl(url);
    }

    /**
     * Overwrite or implement method error()
     *
     * @see com.cyclopsgroup.waterview.ActionContext#error(java.lang.String, java.lang.String)
     */
    public void error(String inputName, String errorMessage)
    {
        errorInputMessages.put(inputName, errorMessage);
    }

    /**
     * Overwrite or implement method fail()
     *
     * @see com.cyclopsgroup.waterview.ActionContext#fail()
     */
    public void fail()
    {
        fail("Unknown reason");
    }

    /**
     * Overwrite or implement method fail()
     *
     * @see com.cyclopsgroup.waterview.ActionContext#fail(java.lang.String)
     */
    public void fail(String errorMessage)
    {
        fail(errorMessage, null);
    }

    /**
     * Overwrite or implement method fail()
     *
     * @see com.cyclopsgroup.waterview.ActionContext#fail(java.lang.String, java.lang.Throwable)
     */
    public void fail(String errorMessage, Throwable throwable)
    {
        failed = true;
        failMessage = errorMessage;
        failCause = throwable;
    }

    /**
     * Overwrite or implement method fail()
     *
     * @see com.cyclopsgroup.waterview.ActionContext#fail(java.lang.Throwable)
     */
    public void fail(Throwable throwable)
    {
        fail(null, throwable);
    }

    /**
     * Getter method for property failCause
     *
     * @return Returns the failCause.
     */
    public Throwable getFailCause()
    {
        return failCause;
    }

    /**
     * Getter method for property failMessage
     *
     * @return Returns the failMessage.
     */
    public String getFailMessage()
    {
        return failMessage;
    }

    /**
     * Overwrite or implement method getTargetLink()
     *
     * @see com.cyclopsgroup.waterview.ActionContext#getTargetLink()
     */
    public Link getTargetLink()
    {
        return link;
    }

    /**
     * Getter method for property targetUrl
     *
     * @return Returns the targetUrl.
     */
    public String getTargetUrl()
    {
        return targetUrl;
    }

    /**
     * Getter method for property failed
     *
     * @return Returns the failed.
     */
    public boolean isFailed()
    {
        return failed;
    }

    /**
     * Overwrite or implement method setTargetUrl()
     *
     * @see com.cyclopsgroup.waterview.ActionContext#setTargetUrl(java.lang.String)
     */
    public void setTargetUrl(String url)
    {
        targetUrl = url;
    }
}
