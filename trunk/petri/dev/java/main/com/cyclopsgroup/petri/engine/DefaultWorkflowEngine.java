/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.petri.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.cyclopsgroup.petri.Case;
import com.cyclopsgroup.petri.definition.FlowDefinition;
import com.cyclopsgroup.petri.persistence.PersistenceManager;

/**
 * Default implementation of workflow engine
 * 
 * @author <a href="mailto:jiaqi.guo@evavi.com">Jiaqi Guo </a>
 */
public class DefaultWorkflowEngine implements WorkflowEngine
{

    private boolean asynchronous = false;

    private PersistenceManager persistenceManager;

    private HashMap stateMachines = new HashMap();

    /**
     * Override method deployFlowDefinition in super class of DefaultWorkflowEngine
     * 
     * @see com.cyclopsgroup.petri.engine.WorkflowEngine#deployFlowDefinition(com.cyclopsgroup.petri.definition.FlowDefinition)
     */
    public synchronized void deployFlowDefinition(FlowDefinition flowDefinition)
    {
        if (stateMachines.containsKey(flowDefinition.getId()))
        {
            return;
        }
        StateMachine stateMachine = new StateMachine(flowDefinition,
                persistenceManager, asynchronous);
        stateMachines.put(flowDefinition.getId(), stateMachine);
        stateMachine.getInitialContext().getContent().put("workflowEngine",
                this);
    }

    /**
     * Override method getFlowDefinition() in super class
     * 
     * @see com.cyclopsgroup.petri.engine.WorkflowEngine#getFlowDefinition(java.lang.String)
     */
    public FlowDefinition getFlowDefinition(String flowDefinitionId)
    {
        StateMachine stateMachine = (StateMachine) stateMachines
                .get(flowDefinitionId);
        return stateMachine == null ? null : stateMachine.getFlowDefinition();
    }

    /**
     * Override method getFlowDefinitions() in super class
     * 
     * @see com.cyclopsgroup.petri.engine.WorkflowEngine#getFlowDefinitions()
     */
    public FlowDefinition[] getFlowDefinitions()
    {
        ArrayList flowDefinitions = new ArrayList(stateMachines.size());
        for (Iterator i = stateMachines.values().iterator(); i.hasNext();)
        {
            StateMachine stateMachine = (StateMachine) i.next();
            flowDefinitions.add(stateMachine.getFlowDefinition());
        }
        return (FlowDefinition[]) flowDefinitions
                .toArray(FlowDefinition.EMPTY_ARRAY);
    }

    /**
     * Override method receiveMessage() in super class
     * 
     * @see com.cyclopsgroup.petri.engine.WorkflowEngine#receiveMessage(java.lang.Object)
     */
    public void receiveMessage(Object message)
    {
        for (Iterator i = stateMachines.values().iterator(); i.hasNext();)
        {
            StateMachine stateMachine = (StateMachine) i.next();
            stateMachine.receiveMessage(message);
        }
    }

    /**
     * Setter method for property asynchronous
     * 
     * @param isAsynchronous
     *                   The asynchronous to set.
     */
    public void setAsynchronous(boolean isAsynchronous)
    {
        asynchronous = isAsynchronous;
    }

    /**
     * Override method startNewCase() in super class
     * 
     * @see com.cyclopsgroup.petri.engine.WorkflowEngine#startNewCase(java.lang.String, java.util.Map)
     */
    public Case startNewCase(String flowDefinitionId, Map initialAttributes)
    {
        StateMachine stateMachine = (StateMachine) stateMachines
                .get(flowDefinitionId);
        return stateMachine == null ? null : stateMachine
                .startNewCase(initialAttributes);
    }

    /**
     * Override method undeployFlowDefinition() in super class
     * 
     * @see com.cyclopsgroup.petri.engine.WorkflowEngine#undeployFlowDefinition(java.lang.String)
     */
    public void undeployFlowDefinition(String flowDefinitionId)
    {
        stateMachines.remove(flowDefinitionId);
    }
}