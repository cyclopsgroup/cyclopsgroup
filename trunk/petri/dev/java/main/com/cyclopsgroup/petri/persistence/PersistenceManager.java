/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.petri.persistence;

import com.cyclopsgroup.petri.Case;
import com.cyclopsgroup.petri.definition.Context;
import com.cyclopsgroup.petri.definition.FlowDefinition;
import com.cyclopsgroup.petri.definition.Transition;

/**
 * Persistence manager interface
 *
 * @author <a href="mailto:jiaqi.guo@evavi.com">Jiaqi Guo</a>
 */
public interface PersistenceManager
{

	/**
	 * Create new case
	 *
	 * @param flowDefinition Case is going to be created in this flow
	 * @param context Runtime context
	 * @return New case object
	 */
	Case createCase(FlowDefinition flowDefinition, Context context);

	/**
	 * Load case with given id
	 *
	 * @param flowDefinition Flow definition object
	 * @param caseId Case id
	 * @return Case object
	 */
	Case loadCase(FlowDefinition flowDefinition, String caseId);

	/**
	 * Load cases waiting for given transition and message
	 *
	 * @param waitingTransition Transition this case is waiting for
	 * @param message Message object
	 * @return Array of cases
	 */
	Case[] loadCases(Transition waitingTransition, Object message);

	/**
	 * Update the change to case
	 *
	 * @param transition Transition which made this change
	 * @param workflowCase Case object
	 */
	void updateCase(Transition transition, Case workflowCase);
}