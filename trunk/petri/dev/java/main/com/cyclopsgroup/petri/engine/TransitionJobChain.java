/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.petri.engine;

import java.util.HashMap;
import java.util.LinkedList;

import com.cyclopsgroup.petri.definition.AutomaticTrigger;
import com.cyclopsgroup.petri.definition.Context;
import com.cyclopsgroup.petri.definition.FlowDefinition;
import com.cyclopsgroup.petri.definition.State;
import com.cyclopsgroup.petri.definition.Transition;
import com.cyclopsgroup.petri.persistence.PersistenceManager;

/**
 * Transition job chain
 * 
 * @author <a href="mailto:jiaqi.guo@evavi.com">Jiaqi Guo </a>
 */
public class TransitionJobChain
{

    private class NewJob implements TransitionJob
    {
        private Transition transition;

        /**
         * Constructor of class NewJob
         * 
         * @param t
         *                   Transition
         */
        private NewJob(Transition t)
        {
            transition = t;
        }

        /**
         * Override method run in super class of NewJob
         * 
         * @see com.cyclopsgroup.petri.engine.TransitionJob#run(com.cyclopsgroup.petri.definition.Context, com.cyclopsgroup.petri.persistence.PersistenceManager)
         */
        public void run(Context context, PersistenceManager persistenceManager)
        {
            CaseWrapper caseWrapper = (CaseWrapper) context.get("workflowCase");
            TransitionJobExecutor.getInstance().executeStandardTransition(
                    transition, caseWrapper, context, persistenceManager);
        }
    }

    private LinkedList jobQueue = new LinkedList();

    /**
     * Constructor of class TransitionJobChain
     * 
     * @param firstJob
     */
    public TransitionJobChain(TransitionJob firstJob)
    {
        jobQueue.add(firstJob);
    }

    /**
     * Main execution method
     * 
     * @param context
     * @param persistenceManager
     * @param flowDefinition
     */
    public void execute(Context context, PersistenceManager persistenceManager,
            FlowDefinition flowDefinition)
    {
        while (!jobQueue.isEmpty())
        {
            TransitionJob job = (TransitionJob) jobQueue.removeFirst();
            job.run(context, persistenceManager);
            CaseWrapper caseWrapper = (CaseWrapper) context.get("workflowCase");
            if (caseWrapper == null)
            {
                return; //Only if initial transition failed to be triggered
            }
            String[] currentStates = caseWrapper.getCurrentStates();
            HashMap newJobs = new HashMap();
            for (int i = 0; i < currentStates.length; i++)
            {
                State currentState = flowDefinition.getState(currentStates[i]);
                Transition[] transitions = currentState
                        .getOutboundTransitions();
                for (int j = 0; j < transitions.length; j++)
                {
                    Transition transition = transitions[j];
                    if (!TransitionJobExecutor.getInstance().checkPosition(
                            transition, caseWrapper))
                    {
                        continue;
                    }
                    if (transition.getTrigger() instanceof AutomaticTrigger)
                    {
                        newJobs.put(transition.getId(), new NewJob(transition));
                    }
                }
            }
            jobQueue.addAll(newJobs.values());
        }
    }
}