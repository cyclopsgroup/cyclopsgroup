/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.waterview.web.taglib;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 * 
 * Container of form tag
 */
public interface FormControlTag
{
    /**
     * Handle form tag
     *
     * @param formTag Form tag object
     * @throws Exception Throw it out
     */
    void setFormTag(FormTag formTag) throws Exception;
}
