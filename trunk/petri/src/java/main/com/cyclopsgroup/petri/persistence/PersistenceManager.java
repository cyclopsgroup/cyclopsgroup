/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.petri.persistence;

import java.util.Date;
import java.util.Map;

import com.cyclopsgroup.petri.Case;
import com.cyclopsgroup.petri.definition.NetDefinition;
import com.cyclopsgroup.petri.definition.Transition;

/**
 * Persistence manager interface
 *
 * @author <a href="mailto:jiaqi.guo@evavi.com">Jiaqi Guo</a>
 */
public interface PersistenceManager
{
    /** Role in container */
    String ROLE = PersistenceManager.class.getName();

    /**
     * Create new case
     *
     * @param flowDefinition Case is going to be created in this flow
     * @param initialAttributes Initial attributes
     * @return New case object
     * @throws Exception Just throw it out
     */
    Case createCase(NetDefinition flowDefinition, Map initialAttributes)
            throws Exception;

    /**
     * Remove a finished case
     *
     * @param flowDefinition Flow definition object
     * @param workflowCase Case
     * @throws Exception Just throw it out
     */
    void disposeCase(NetDefinition flowDefinition, Case workflowCase)
            throws Exception;

    /**
     * Load case with given id
     *
     * @param caseId Case id
     * @return Case object
     * @throws Exception Just throw it out
     */
    Case loadCase(String caseId) throws Exception;

    /**
     * Load cases waiting for given transition and message
     *
     * @param waitingTransition Transition this case is waiting for
     * @param message Message object
     * @return Array of cases
     * @throws Exception Just throw it out
     */
    Case[] loadCases(Transition waitingTransition, Object message)
            throws Exception;

    /**
     * Load all active schedules from persistence layer
     *
     * @param flowDefinition Flow definition
     * @return Array of schedules
     * @throws Exception Just throw it out
     */
    Schedule[] loadSchedules(NetDefinition flowDefinition) throws Exception;

    /**
     * Schedule case for transition with given occure time
     *
     * @param transition Transition object
     * @param workflowCase Workflow case object
     * @param occurTime Scheduled time
     * @throws Exception Throw it out
     */
    void schedule(Transition transition, Case workflowCase, Date occurTime)
            throws Exception;

    /**
     * Schedule case for transition with a cron expression
     *
     * @param transition Transition object
     * @param workflowCase Workflow case object
     * @param cronExpression Schedule cron expression
     * @throws Exception Throw it out
     */
    void schedule(Transition transition, Case workflowCase,
            String cronExpression) throws Exception;

    /**
     * Clear schedule on specified transition for a case
     *
     * @param transition Transition object
     * @param workflowCase Workflow case object
     * @throws Exception Throw it out
     */
    void unschedule(Transition transition, Case workflowCase) throws Exception;

    /**
     * Update the change to case
     *
     * @param transition Transition which made this change
     * @param workflowCase Case object
     * @throws Exception Just throw it out
     */
    void updateCase(Transition transition, Case workflowCase) throws Exception;
}