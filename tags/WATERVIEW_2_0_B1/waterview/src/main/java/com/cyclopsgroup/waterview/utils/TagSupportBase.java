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
package com.cyclopsgroup.waterview.utils;

import java.security.MessageDigest;

import org.apache.avalon.framework.service.ServiceManager;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.LocationAware;
import org.apache.commons.jelly.MissingAttributeException;
import org.apache.commons.jelly.TagSupport;
import org.apache.commons.jelly.XMLOutput;
import org.apache.commons.lang.StringUtils;

/**
 * Base jelly tag
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public abstract class TagSupportBase extends TagSupport
{
    private static final String DIGEST_ALGORITHM = "MD5";

    private int columnNumber;

    private String elementName;

    private String fileName;

    private int lineNumber;

    private String tagId;

    private String uniqueTagId;

    /**
     * Overwrite or implement method doTag()
     *
     * @see org.apache.commons.jelly.Tag#doTag(org.apache.commons.jelly.XMLOutput)
     */
    public void doTag(XMLOutput output) throws MissingAttributeException,
            JellyTagException
    {
        try
        {
            processTag(output);
        }
        catch (JellyTagException e)
        {
            throw e;
        }
        catch (Exception e)
        {
            throw new JellyTagException(e);
        }
    }

    /**
     * Override method BaseTagSupport in supper class
     *
     * @see org.apache.commons.jelly.LocationAware#getColumnNumber()
     * @return column number
     */
    public final int getColumnNumber()
    {
        return columnNumber;
    }

    /**
     * Override method BaseTagSupport in supper class
     *
     * @see org.apache.commons.jelly.LocationAware#getElementName()
     * @return element name
     */
    public final String getElementName()
    {
        return elementName;
    }

    /**
     * Override method BaseTagSupport in supper class
     *
     * @see org.apache.commons.jelly.LocationAware#getFileName()
     * @return current file name
     */
    public final String getFileName()
    {
        return fileName;
    }

    /**
     * Override method BaseTagSupport in supper class
     *
     * @see org.apache.commons.jelly.LocationAware#getLineNumber()
     * @return line number
     */
    public final int getLineNumber()
    {
        return lineNumber;
    }

    /**
     * Get service manager
     *
     * @return Service manager object
     */
    protected ServiceManager getServiceManager()
    {
        return (ServiceManager) getContext().getVariable(
                ServiceManager.class.getName());
    }

    /**
     * @return Returns the id.
     */
    public final String getTagId()
    {
        return tagId;
    }

    /**
     * Get internal tag id
     *
     * @return Tag id
     * @throws Exception
     */
    public final synchronized String getUniqueTagId() throws Exception
    {
        if (uniqueTagId == null)
        {
            if (!(this instanceof LocationAware))
            {
                throw new UnsupportedOperationException(this
                        + " tag is not location aware, can't get tag id");
            }
            if (StringUtils.isEmpty(getTagId()))
            {
                throw new IllegalArgumentException(
                        "Id attribute is required for " + this
                                + "to get tag ID");
            }
            StringBuffer sb = new StringBuffer(getFileName());
            sb.append(':').append(getElementName());
            sb.append(':').append(getTagId());
            byte[] digest = MessageDigest.getInstance(DIGEST_ALGORITHM).digest(
                    sb.toString().getBytes());
            uniqueTagId = new String(Hex.encodeHex(digest));
        }
        return uniqueTagId;
    }

    /**
     * process the tag
     *
     * @param output Output
     * @throws Exception Throw it out
     */
    protected abstract void processTag(XMLOutput output) throws Exception;

    /**
     * Require body of a tag
     *
     * @throws JellyTagException If the body is empty, throw it out
     */
    protected final void requireBody() throws JellyTagException
    {
        if (StringUtils.isEmpty(getBodyText()))
        {
            throw new JellyTagException("Body text is required");
        }
    }

    /**
     * Require parent to be a class
     *
     * @param parentTagClass Parent class
     * @throws JellyTagException Throw it out if not matched
     */
    protected final void requireParent(Class parentTagClass)
            throws JellyTagException
    {
        if (!parentTagClass.isAssignableFrom(getParent().getClass()))
        {
            throw new JellyTagException("Tag's parent must be "
                    + parentTagClass.getName());
        }
    }

    /**
     * Override method BaseTagSupport in supper class
     *
     * @see org.apache.commons.jelly.LocationAware#setColumnNumber(int)
     * @param columnNumber Column number
     */
    public final void setColumnNumber(int columnNumber)
    {
        this.columnNumber = columnNumber;
    }

    /**
     * Override method BaseTagSupport in supper class
     *
     * @see org.apache.commons.jelly.LocationAware#setElementName(java.lang.String)
     * @param elementName Name of the element
     */
    public final void setElementName(String elementName)
    {
        this.elementName = elementName;
    }

    /**
     * Override method BaseTagSupport in supper class
     *
     * @see org.apache.commons.jelly.LocationAware#setFileName(java.lang.String)
     * @param fileName Name of the script file
     */
    public final void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    /**
     * Override method BaseTagSupport in supper class
     *
     * @see org.apache.commons.jelly.LocationAware#setLineNumber(int)
     * @param lineNumber number of line
     */
    public final void setLineNumber(int lineNumber)
    {
        this.lineNumber = lineNumber;
    }

    /**
     * @param id The id to set.
     */
    public final void setTagId(String id)
    {
        this.tagId = id;
    }
}