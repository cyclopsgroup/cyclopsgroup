/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.petri.engine;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;

import com.cyclopsgroup.petri.definition.NetContext;
import com.cyclopsgroup.petri.definition.Transition;

/**
 * Job to add schedule
 *
 * @author <a href="mailto:jiaqi.guo@evavi.com">Jiaqi Guo</a>
 */
public class AddScheduleJob implements Job
{

    private String cronExpression;

    private Transition transition;

    /**
     * Constructor of class AddScheduleJob
     *
     * @param scheduledTransition Transition
     * @param expression Cron expression
     */
    public AddScheduleJob(Transition scheduledTransition, String expression)
    {
        transition = scheduledTransition;
        cronExpression = expression;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.petri.engine.Job#run(com.cyclopsgroup.petri.engine.StateMachine, com.cyclopsgroup.petri.engine.JobQueue, com.cyclopsgroup.petri.definition.NetContext, com.cyclopsgroup.petri.engine.StateMachineCase)
     */
    public void run(StateMachine stateMachine, JobQueue jobQueue,
            NetContext context, StateMachineCase caseWrapper) throws Exception
    {
        String jobName = caseWrapper.getId() + "@" + transition.getId();
        CronTrigger ct = new CronTrigger(jobName, StateMachine.JOB_GROUP_NAME,
                cronExpression);
        JobDetail jobDetail = new JobDetail(jobName,
                StateMachine.JOB_GROUP_NAME, QuartzTriggerTransitionJob.class);
        jobDetail.getJobDataMap().put("stateMachine", stateMachine);
        jobDetail.getJobDataMap().put("transitionId", transition.getId());
        jobDetail.getJobDataMap().put("caseId", caseWrapper.getId());
        stateMachine.getScheduler().scheduleJob(jobDetail, ct);
        stateMachine.getPersistenceManager().schedule(transition, caseWrapper,
                ct.getCronExpression());
    }
}