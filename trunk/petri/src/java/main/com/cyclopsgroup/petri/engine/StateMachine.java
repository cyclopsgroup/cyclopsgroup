/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.petri.engine;

import java.io.File;
import java.util.Map;

import com.cyclopsgroup.gearset.beans.Context;
import com.cyclopsgroup.gearset.beans.InheritableContext;
import com.cyclopsgroup.gearset.beans.MapContext;
import com.cyclopsgroup.gearset.beans.SimpleLogEnabled;
import com.cyclopsgroup.petri.Case;
import com.cyclopsgroup.petri.definition.FlowDefinition;
import com.cyclopsgroup.petri.definition.MessageTrigger;
import com.cyclopsgroup.petri.definition.MessageType;
import com.cyclopsgroup.petri.definition.Transition;
import com.cyclopsgroup.petri.persistence.PersistenceManager;

/**
 * State machine
 * 
 * @author <a href="mailto:jiaqi.guo@evavi.com">Jiaqi Guo </a>
 */
public class StateMachine extends SimpleLogEnabled
{

    private boolean asynchronous = false;

    private FlowDefinition flowDefinition;

    private MapContext initialContext = new MapContext(null);

    private PersistenceManager persistenceManager;

    /**
     * Constructor of class StateMachine
     * 
     * @param definition
     * @param pm
     * @param isAsynchronous
     */
    public StateMachine(FlowDefinition definition, PersistenceManager pm,
            boolean isAsynchronous)
    {
        flowDefinition = definition;
        persistenceManager = pm;
        asynchronous = isAsynchronous;
        getInitialContext().getMap().putAll(System.getProperties());
        getInitialContext().getMap().put("basedir",
                new File("").getAbsolutePath());
        getInitialContext().getMap().put("flowDefinition", definition);
        getInitialContext().getMap().put("stateMachine", this);
    }

    /**
     * Getter method for property flowDefinition
     * 
     * @return Returns the flowDefinition.
     */
    public FlowDefinition getFlowDefinition()
    {
        return flowDefinition;
    }

    /**
     * Getter method for property initialContext
     * 
     * @return Returns the initialContext.
     */
    public MapContext getInitialContext()
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
     * Getter method for property asynchronous
     * 
     * @return Returns the asynchronous.
     */
    public boolean isAsynchronous()
    {
        return asynchronous;
    }

    /**
     * Method receiveMessage() in class StateMachine
     * 
     * @param message
     */
    public void receiveMessage(Object message)
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
                    InheritableContext ctx = new InheritableContext(
                            getInitialContext());
                    ctx.put(messageTrigger.getMessageName(), message);
                    TransitionJob job = new TransitionJob()
                    {
                        public void run(Context context, PersistenceManager pm)
                        {
                            TransitionJobExecutor.getInstance()
                                    .executeInitialTransition(transition,
                                            context, pm);
                        }
                    };
                    TransitionJobChain chain = new TransitionJobChain(job);
                    chain.execute(ctx, getPersistenceManager(),
                            getFlowDefinition());
                }
                else
                {
                    Case[] cases = getPersistenceManager().loadCases(
                            transition, message);
                    for (int j = 0; j < cases.length; j++)
                    {
                        Case persistenceCase = cases[j];
                        InheritableContext ctx = new InheritableContext(
                                getInitialContext());
                        ctx.put(messageTrigger.getMessageName(), message);
                        ctx.put("workflowCase",
                                new CaseWrapper(persistenceCase));
                        TransitionJob job = new TransitionJob()
                        {
                            public void run(Context context,
                                    PersistenceManager pm)
                            {
                                CaseWrapper caseWrapper = (CaseWrapper) context
                                        .get("workflowCase");
                                if (TransitionJobExecutor.getInstance()
                                        .checkPosition(transition, caseWrapper))
                                {
                                    TransitionJobExecutor.getInstance()
                                            .executeStandardTransition(
                                                    transition, caseWrapper,
                                                    context, pm);
                                }
                            }
                        };
                        TransitionJobChain chain = new TransitionJobChain(job);
                        chain.execute(ctx, getPersistenceManager(),
                                getFlowDefinition());
                    }
                }
            }
        }
    }

    /**
     * TODO implement it
     * 
     * @param attributes Initial attributes
     * @return Case object
     */
    public Case startNewCase(Map attributes)
    {
        throw new UnsupportedOperationException();
    }
}