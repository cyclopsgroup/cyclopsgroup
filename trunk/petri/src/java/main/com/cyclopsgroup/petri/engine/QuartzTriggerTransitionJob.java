/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.petri.engine;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.cyclopsgroup.petri.Case;
import com.cyclopsgroup.petri.definition.InputArc;
import com.cyclopsgroup.petri.definition.Place;
import com.cyclopsgroup.petri.definition.Transition;

/**
 * Trigger transition quartz job
 *
 * @author <a href="mailto:jiaqi.guo@evavi.com">Jiaqi Guo</a>
 */
public class QuartzTriggerTransitionJob implements Job
{
    private static Log logger = LogFactory
            .getLog(QuartzTriggerTransitionJob.class);

    /**
     * Override method execute() in super class
     *
     * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
     */
    public void execute(JobExecutionContext jobContext)
            throws JobExecutionException
    {
        StateMachine stateMachine = (StateMachine) jobContext.getJobDetail()
                .getJobDataMap().get("stateMachine");
        String transitionId = jobContext.getJobDetail().getJobDataMap()
                .getString("transitionId");
        String caseId = jobContext.getJobDetail().getJobDataMap().getString(
                "caseId");
        try
        {
            Case c = stateMachine.getPersistenceManager().loadCase(caseId);
            StateMachineCase caseWrapper = new StateMachineCase(c);
            Transition transition = stateMachine.getFlowDefinition()
                    .getTransition(transitionId);

            boolean shouldContinue = false;
            InputArc[] arcs = transition.getInputArcs();
            for (int i = 0; i < arcs.length; i++)
            {
                Place fromState = arcs[i].getFrom();
                if (caseWrapper.getCurrentStateSet()
                        .contains(fromState.getId()))
                {
                    shouldContinue = true;
                    break;
                }
            }
            if (!shouldContinue)
            {
                JobDetail jobDetail = jobContext.getJobDetail();
                jobContext.getScheduler().deleteJob(jobDetail.getName(),
                        jobDetail.getGroup());
                logger.warn("Cleaned uncontrolled schedule at transition "
                        + transitionId + " for case " + caseId);
                return;
            }

            JobQueue queue = new JobQueue(stateMachine);
            TriggerTransitionJob ttj = new TriggerTransitionJob(transition,
                    false);
            queue.append(ttj);
            DefaultNetContext dc = new DefaultNetContext(stateMachine
                    .getInitialContext());
            dc.put("workflowCase", caseWrapper);
            queue.run(dc, caseWrapper);
        }
        catch (Exception e)
        {
            throw new JobExecutionException(e);
        }
    }
}