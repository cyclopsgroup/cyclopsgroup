/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.cyclib.velocity;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;

/**
 * @author <a href="mailto:jiaqi@evavi.com">Jiaqi Guo</a>
 *
 * Velocity facade component
 */
public abstract class VelocityComponent extends VelocityEngine
{
    /** Role name in container */
    public static final String ROLE = VelocityComponent.class.getName();

    /**
     * Evaluate given string expression template
     *
     * @param context Velocity context
     * @param template String template
     * @return Evaluation result
     * @throws Exception Throw it out
     */
    public abstract String evaluate(Context context, String template)
            throws Exception;
}