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

import com.cyclopsgroup.petri.definition.InputArc;
import com.cyclopsgroup.petri.definition.MessageTrigger;
import com.cyclopsgroup.petri.definition.NetContext;
import com.cyclopsgroup.petri.definition.Place;
import com.cyclopsgroup.petri.definition.Transition;

/**
 * Job to handle a message
 *
 * @author <a href="mailto:jiaqi.guo@evavi.com">Jiaqi Guo</a>
 */
public class HandleMessageJob implements Job
{
    private Object message;

    /**
     * Constructor of class HandleMessageJob
     *
     * @param msg Message object
     */
    public HandleMessageJob(Object msg)
    {
        message = msg;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.petri.engine.Job#run(com.cyclopsgroup.petri.engine.StateMachine, com.cyclopsgroup.petri.engine.JobQueue, com.cyclopsgroup.petri.definition.NetContext, com.cyclopsgroup.petri.engine.StateMachineCase)
     */
    public void run(StateMachine stateMachine, JobQueue jobQueue,
            NetContext context, StateMachineCase caseWrapper) throws Exception
    {
        String[] states = caseWrapper.getCurrentStates();
        HashMap possibleTransitions = new HashMap();
        for (int i = 0; i < states.length; i++)
        {
            Place currentState = stateMachine.getFlowDefinition().getState(
                    states[i]);
            InputArc[] arcs = currentState.getOutboundArcs();
            for (int j = 0; j < arcs.length; j++)
            {
                Transition transition = arcs[j].getTo();
                possibleTransitions.put(transition.getId(), transition);
            }
        }
        for (Iterator i = possibleTransitions.values().iterator(); i.hasNext();)
        {
            Transition transition = (Transition) i.next();
            if (transition.getTrigger() instanceof MessageTrigger)
            {
                MessageTrigger mt = (MessageTrigger) transition.getTrigger();
                if (mt.getMessageType().accept(message))
                {
                    context.put(mt.getMessageName(), message);
                    context.put("workflowCase", caseWrapper);
                    jobQueue
                            .append(new TriggerTransitionJob(transition, false));
                }
            }
        }
    }
}