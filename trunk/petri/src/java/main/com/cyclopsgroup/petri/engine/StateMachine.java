/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.petri.engine;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;

import com.cyclopsgroup.petri.Case;
import com.cyclopsgroup.petri.WorkflowException;
import com.cyclopsgroup.petri.definition.InputArc;
import com.cyclopsgroup.petri.definition.ManualTrigger;
import com.cyclopsgroup.petri.definition.MessageTrigger;
import com.cyclopsgroup.petri.definition.MessageType;
import com.cyclopsgroup.petri.definition.NetContext;
import com.cyclopsgroup.petri.definition.NetDefinition;
import com.cyclopsgroup.petri.definition.Transition;
import com.cyclopsgroup.petri.message.MessageListener;
import com.cyclopsgroup.petri.message.SubjectAware;
import com.cyclopsgroup.petri.message.UserAware;
import com.cyclopsgroup.petri.persistence.PersistenceManager;
import com.cyclopsgroup.petri.persistence.Schedule;

/**
 * State machine
 *
 * @author <a href="mailto:jiaqi.guo@evavi.com">Jiaqi Guo</a>
 */
public class StateMachine implements MessageListener
{
    private static Log logger = LogFactory.getLog(StateMachine.class);

    /** Job group name for quartz scheduler */
    public static final String JOB_GROUP_NAME = "bpm";

    private NetDefinition flowDefinition;

    private DefaultNetContext initialContext = new DefaultNetContext();

    private transient HashMap jobQueues = new HashMap();

    private PersistenceManager persistenceManager;

    private Scheduler scheduler;

    private boolean started = false;

    private boolean stopped = false;

    /**
     * Constructor of class StateMachine
     *
     * @param definition
     * @param pm
     */
    public StateMachine(NetDefinition definition, PersistenceManager pm)
    {
        flowDefinition = definition;
        persistenceManager = pm;
        initialContext.getContent().putAll(System.getProperties());
        initialContext.getContent().put("basedir",
                new File("").getAbsolutePath());
        initialContext.getContent().put("flowDefinition", definition);
        initialContext.getContent().put("stateMachine", this);
    }

    /**
     * Getter method for property flowDefinition
     *
     * @return Returns the flowDefinition.
     */
    public NetDefinition getFlowDefinition()
    {
        return flowDefinition;
    }

    /**
     * Getter method for property initialContext
     *
     * @return Returns the initialContext.
     */
    public DefaultNetContext getInitialContext()
    {
        return initialContext;
    }

    /**
     * Getter method for property persistenceManager
     *
     * @return Returns the persistenceManager.
     */
    public PersistenceManager getPersistenceManager()
    {
        return persistenceManager;
    }

    /**
     * Getter method for property scheduler
     *
     * @return Returns the scheduler.
     */
    public Scheduler getScheduler()
    {
        return scheduler;
    }

    /**
     * Override method handleMessage() in super class
     *
     * @see com.cyclopsgroup.petri.message.MessageListener#handleMessage(java.lang.Object)
     */
    public void handleMessage(Object message) throws Exception
    {
        internalReceiveMessage(message);
    }

    private void internalReceiveMessage(Object message)
    {
        Transition[] transitions = getFlowDefinition().getTransitions();
        for (int i = 0; i < transitions.length; i++)
        {
            final Transition transition = transitions[i];
            if (!(transition.getTrigger() instanceof MessageTrigger))
            {
                continue;
            }
            MessageTrigger messageTrigger = (MessageTrigger) transition
                    .getTrigger();
            MessageType messageType = messageTrigger.getMessageType();
            if (messageType != null && messageType.accept(message))
            {
                if (transition.isInitial())
                {
                    DefaultNetContext ctx = new DefaultNetContext(
                            getInitialContext());
                    ctx.put(messageTrigger.getMessageName(), message);
                    HashMap initialAttributes = new HashMap();
                    if (message instanceof SubjectAware)
                    {
                        initialAttributes.put("subjectId",
                                ((SubjectAware) message).getSubjectId());
                    }
                    if (message instanceof UserAware)
                    {
                        initialAttributes.put("modifier", ((UserAware) message)
                                .getUser());
                    }
                    startNewCase(transition, initialAttributes, ctx);
                }
                else
                {
                    try
                    {
                        Case[] cases = getPersistenceManager().loadCases(
                                transition, message);
                        for (int j = 0; j < cases.length; j++)
                        {
                            Case persistenceCase = cases[j];
                            StateMachineCase caseWrapper = new StateMachineCase(
                                    persistenceCase);
                            DefaultNetContext ctx = new DefaultNetContext(
                                    getInitialContext());
                            ctx.put("workflowCase", caseWrapper);
                            runJob(new HandleMessageJob(message), ctx,
                                    caseWrapper);
                        }
                    }
                    catch (Exception e)
                    {
                        logger.error("Get cases error", e);
                    }
                }
            }
        }
    }

    private void runJob(Job job, NetContext context,
            StateMachineCase caseWrapper)
    {
        boolean createNew = false;
        JobQueue jobQueue = null;
        synchronized (this)
        {
            jobQueue = (JobQueue) jobQueues.get(caseWrapper.getId());
            if (jobQueue == null)
            {
                jobQueue = new JobQueue(this);
                jobQueues.put(caseWrapper.getId(), jobQueue);
                createNew = true;
            }
            jobQueue.append(job);
        }
        if (createNew)
        {
            try
            {
                jobQueue.run(context, caseWrapper);
            }
            catch (Exception e)
            {
                logger.error("Run job error", e);
            }
            jobQueues.remove(caseWrapper.getId());
        }
    }

    /**
     * Start working
     *
     * @throws Exception Throw it out
     */
    public void start() throws Exception
    {
        if (started)
        {
            return;
        }
        SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();
        scheduler = schedFact.getScheduler();
        scheduler.start();

        Schedule[] schedules = getPersistenceManager().loadSchedules(
                getFlowDefinition());
        for (int i = 0; i < schedules.length; i++)
        {
            Schedule schedule = schedules[i];
            Transition transition = getFlowDefinition().getTransition(
                    schedule.getTransitionId());
            StateMachineCase caseWrapper = new StateMachineCase(
                    getPersistenceManager().loadCase(schedule.getCaseId()));
            Job job = schedule.isRecurrent() ? (Job) new AddScheduleJob(
                    transition, schedule.getCronExpression())
                    : new AddTimerJob(transition, schedule.getOccurTime());
            job.run(this, null, null, caseWrapper);
        }
        started = true;
    }

    /**
     * Manually start a new case
     *
     * @task synchronization is still a quesition
     * @param initialAttributes Initial attributes
     * @return Case object
     */
    public Case startNewCase(Map initialAttributes) throws WorkflowException
    {
        InputArc[] arcs = getFlowDefinition().getEntrance().getOutboundArcs();
        Transition initialTransition = null;
        for (int i = 0; i < arcs.length; i++)
        {
            Transition transition = arcs[i].getTo();
            if (transition.getTrigger() instanceof ManualTrigger)
            {
                initialTransition = transition;
            }
        }
        if (initialTransition == null)
        {
            //TODO throw proper exception here
            /*			throw new WorkflowException("Flow " + getFlowDefinition().getId()
             + " can't be started manually");*/
        }

        return startNewCase(initialTransition, initialAttributes,
                new DefaultNetContext());

    }

    private Case startNewCase(Transition initialTransition,
            Map initialAttributes, NetContext context)
    {
        if (initialAttributes != null)
        {
            context.put("initialAttributes", initialAttributes);
        }
        try
        {
            if (initialTransition.getGuard() == null
                    || initialTransition.getGuard().evaluate(context))
            {
                Case persistenceCase = persistenceManager.createCase(
                        initialTransition.getFlowDefinition(),
                        initialAttributes);
                StateMachineCase cw = new StateMachineCase(persistenceCase);
                if (initialAttributes != null)
                {
                    cw.getAttributes().putAll(initialAttributes);
                }
                context.put("workflowCase", cw);
                context.put("initialAttributes", initialAttributes);
                runJob(new TriggerTransitionJob(initialTransition, true),
                        context, cw);
                return cw;
            }
            return null;
        }
        catch (Exception e)
        {
            logger.error("Initial case error", e);
            return null;
        }
    }

    /**
     * Stop working
     *
     * @throws Exception Throw it out
     */
    public void stop() throws Exception
    {
        if (stopped)
        {
            return;
        }
        scheduler.shutdown();
        stopped = true;
    }
}