/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.petri.definition;

import com.cyclopsgroup.gearset.beans.Context;

/**
 * Guard for transition
 *
 * @author <a href="mailto:jiaqi.guo@evavi.com">Jiaqi Guo</a>
 */
public interface Guard
{
	/**
	 * Evaluate result
	 *
	 * @param context Runtime context
	 * @return True or false
	 * @throws Exception Throw it out
	 */
	boolean evaluate(Context context) throws Exception;
}