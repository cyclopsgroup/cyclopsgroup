/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.petri.engine;

import java.util.HashMap;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cyclopsgroup.petri.definition.AutomaticTrigger;
import com.cyclopsgroup.petri.definition.InputArc;
import com.cyclopsgroup.petri.definition.NetContext;
import com.cyclopsgroup.petri.definition.OutputArc;
import com.cyclopsgroup.petri.definition.Place;
import com.cyclopsgroup.petri.definition.ScheduledTrigger;
import com.cyclopsgroup.petri.definition.TimerTrigger;
import com.cyclopsgroup.petri.definition.Transition;

/**
 * Job to trigger a transition
 *
 * @author <a href="mailto:jiaqi.guo@evavi.com">Jiaqi Guo</a>
 */
public class TriggerTransitionJob implements Job
{
    private static Log logger = LogFactory.getLog(TriggerTransitionJob.class);

    private boolean ignoreGuard = false;

    private Transition transition;

    /**
     * Constructor of class TriggerTransitionJob
     *
     * @param trans Transition object
     * @param skipGuardCheck Ignore guard
     */
    public TriggerTransitionJob(Transition trans, boolean skipGuardCheck)
    {
        transition = trans;
        ignoreGuard = skipGuardCheck;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.petri.engine.Job#run(com.cyclopsgroup.petri.engine.StateMachine, com.cyclopsgroup.petri.engine.JobQueue, com.cyclopsgroup.petri.definition.NetContext, com.cyclopsgroup.petri.engine.StateMachineCase)
     */
    public void run(StateMachine stateMachine, JobQueue jobQueue,
            NetContext context, StateMachineCase caseWrapper) throws Exception
    {
        //Examine guard first
        if (ignoreGuard || transition.getGuard() == null
                || transition.getGuard().evaluate(context))
        {
            //Execute task
            if (transition.getTask() != null)
            {
                transition.getTask().execute(context);
            }

            //Change state
            InputArc[] froms = transition.getInputArcs();
            OutputArc[] tos = transition.getOutputArcs();
            HashMap futures = new HashMap();
            for (int i = 0; i < froms.length; i++)
            {
                Place fromState = froms[i].getFrom();
                caseWrapper.getCurrentStateSet().remove(fromState.getId());
                jobQueue.append(new ClearScheduleJob(fromState));
            }
            OutputArc defaultOutputArc = null;
            boolean changed = false;
            for (int i = 0; i < tos.length; i++)
            {
                OutputArc outputArc = tos[i];
                if (outputArc.isDefault())
                {
                    defaultOutputArc = outputArc;
                }
                Place toState = outputArc.getTo();
                try
                {
                    if (outputArc.getGuard() != null
                            && !(outputArc.getGuard().evaluate(context)))
                    {
                        continue;
                    }
                }
                catch (Exception e)
                {
                    logger.warn("Condition evaluation error", e);
                    continue;
                }
                caseWrapper.getCurrentStateSet().add(toState.getId());
                InputArc[] futureArcs = toState.getOutboundArcs();
                for (int j = 0; j < futureArcs.length; j++)
                {
                    Transition futureTransition = futureArcs[j].getTo();
                    futures.put(futureTransition.getId(), futureTransition);
                }
                changed = true;
                if (transition.getOutputPolicy() == Transition.SEQUENTIAL)
                {
                    break;
                }
            }
            if (!changed
                    && transition.getOutputPolicy() == Transition.SEQUENTIAL)
            {
                if (defaultOutputArc == null)
                {
                    throw new RuntimeException(
                            "Can not find required default output arc in transition "
                                    + transition.getId());
                }
                InputArc[] futureArcs = defaultOutputArc.getTo()
                        .getOutboundArcs();
                for (int j = 0; j < futureArcs.length; j++)
                {
                    Transition futureTransition = futureArcs[j].getTo();
                    futures.put(futureTransition.getId(), futureTransition);
                }
            }

            //Update case
            stateMachine.getPersistenceManager().updateCase(transition,
                    caseWrapper);

            //Check if the case is finished
            if (transition.isTerminal())
            {
                caseWrapper.getCurrentStateSet().clear();
                stateMachine.getPersistenceManager().disposeCase(
                        stateMachine.getFlowDefinition(), caseWrapper);
                return;
            }

            //Append jobs to trigger consequential automatic transitions
            for (Iterator i = futures.values().iterator(); i.hasNext();)
            {
                Transition futureTransition = (Transition) i.next();
                if (futureTransition.getTrigger() instanceof AutomaticTrigger)
                {
                    jobQueue.append(new TriggerTransitionJob(futureTransition,
                            false));
                }
                else if (futureTransition.getTrigger() instanceof TimerTrigger)
                {
                    long occurTime = System.currentTimeMillis()
                            + ((TimerTrigger) futureTransition.getTrigger())
                                    .getWaitingPeriod();
                    jobQueue
                            .append(new AddTimerJob(futureTransition, occurTime));
                }
                else if (futureTransition.getTrigger() instanceof ScheduledTrigger)
                {
                    jobQueue.append(new AddScheduleJob(futureTransition,
                            ((ScheduledTrigger) futureTransition.getTrigger())
                                    .getScheduleExpression()));
                }
            }
        }
    }
}