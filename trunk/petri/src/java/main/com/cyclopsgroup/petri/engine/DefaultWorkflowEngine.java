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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cyclopsgroup.petri.Case;
import com.cyclopsgroup.petri.WorkflowException;
import com.cyclopsgroup.petri.definition.NetContext;
import com.cyclopsgroup.petri.definition.NetDefinition;
import com.cyclopsgroup.petri.message.MessageManager;
import com.cyclopsgroup.petri.persistence.PersistenceManager;

/**
 * Default implementation of workflow engine
 *
 * @author <a href="mailto:jiaqi.guo@evavi.com">Jiaqi Guo</a>
 */
public class DefaultWorkflowEngine implements WorkflowEngine
{
    private static Log logger = LogFactory.getLog(DefaultWorkflowEngine.class);

    private DefaultNetContext initialContext = new DefaultNetContext();

    private MessageManager messageManager;

    private PersistenceManager persistenceManager;

    private HashMap stateMachines = new HashMap();

    /**
     * Constructor of class DefaultWorkflowEngine
     *
     * @param pm PersistenceManager
     * @param mm MessageManager
     */
    public DefaultWorkflowEngine(PersistenceManager pm, MessageManager mm)
    {
        persistenceManager = pm;
        messageManager = mm;
        initialContext.put("stateMachine", this);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.petri.engine.WorkflowEngine#deployFlowDefinition(com.cyclopsgroup.petri.definition.NetDefinition)
     */
    public synchronized void deployFlowDefinition(NetDefinition flowDefinition)
    {
        if (stateMachines.containsKey(flowDefinition.getId()))
        {
            return;
        }
        StateMachine stateMachine = new StateMachine(flowDefinition,
                getPersistenceManager());
        messageManager.addMessageListener(stateMachine);
        try
        {
            stateMachine.start();
            stateMachines.put(flowDefinition.getId(), stateMachine);
            String[] names = getInitialContext().getNames();
            for (int i = 0; i < names.length; i++)
            {
                String name = names[i];
                try
                {
                    stateMachine.getInitialContext().put(name,
                            getInitialContext().get(name));
                }
                catch (Exception ignored)
                {
                    logger.debug("Can not set variable [" + name
                            + "] in context");
                }
            }
        }
        catch (Exception e)
        {
            logger.error("Can not start work flow engine "
                    + flowDefinition.getId(), e);
        }
    }

    /**
     * Override method getFlowDefinition() in super class
     *
     * @see com.cyclopsgroup.petri.engine.WorkflowEngine#getFlowDefinition(java.lang.String)
     */
    public NetDefinition getFlowDefinition(String flowDefinitionId)
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
    public NetDefinition[] getFlowDefinitions()
    {
        ArrayList flowDefinitions = new ArrayList(stateMachines.size());
        for (Iterator i = stateMachines.values().iterator(); i.hasNext();)
        {
            StateMachine stateMachine = (StateMachine) i.next();
            flowDefinitions.add(stateMachine.getFlowDefinition());
        }
        return (NetDefinition[]) flowDefinitions
                .toArray(NetDefinition.EMPTY_ARRAY);
    }

    /**
     * Getter method for property initialContext
     *
     * @return Returns the initialContext.
     */
    public NetContext getInitialContext()
    {
        return initialContext;
    }

    /**
     * Getter method for property messageManager
     *
     * @return Returns the messageManager.
     */
    public MessageManager getMessageManager()
    {
        return messageManager;
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
     * Override method receiveMessage() in super class
     *
     * @see com.cyclopsgroup.petri.engine.WorkflowEngine#receiveMessage(java.lang.Object)
     */
    public void receiveMessage(Object message)
    {
        messageManager.receiveMessage(message);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.petri.engine.WorkflowEngine#startNewCase(java.lang.String, java.util.Map)
     */
    public Case startNewCase(String flowDefinitionId, Map initialAttributes)
            throws WorkflowException
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
        StateMachine stateMachine = (StateMachine) stateMachines
                .remove(flowDefinitionId);
        messageManager.removeMessageListener(stateMachine);
        try
        {
            stateMachine.stop();
        }
        catch (Exception e)
        {
            logger.error("Can not force stopping workflow " + flowDefinitionId);
        }
    }
}