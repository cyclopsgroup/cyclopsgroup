/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.petri.engine;

import java.util.Map;

import com.cyclopsgroup.petri.Case;
import com.cyclopsgroup.petri.definition.NetContext;
import com.cyclopsgroup.petri.definition.NetDefinition;

/**
 * Workflow engine interface
 *
 * @author <a href="mailto:jiaqi.guo@evavi.com">Jiaqi Guo</a>
 */
public interface WorkflowEngine
{
    /**
     * Deploy a given flow definition
     *
     * @param flowDefinition Flow definition object
     */
    public void deployFlowDefinition(NetDefinition flowDefinition);

    /**
     * Get deployed flow definition object
     *
     * @param flowDefinitionId Id of flow definition
     * @return Flow definition object
     */
    public NetDefinition getFlowDefinition(String flowDefinitionId);

    /**
     * Get all deployed flow definitions
     *
     * @return Array of flow definition objects
     */
    NetDefinition[] getFlowDefinitions();

    /**
     * Get initial context
     *
     * @return Initial context
     */
    public NetContext getInitialContext();

    /**
     * Receive a message object
     *
     * @param message Message object which could be any object
     */
    public void receiveMessage(Object message);

    /**
     * Manually start a new case in specified flow process
     *
     * @param flowDefinitionId Flow definition Id
     * @param initialAttributes
     * @return Started new case object
     */
    public Case startNewCase(String flowDefinitionId, Map initialAttributes);

    /**
     * Remove a flow definition
     *
     * @param flowDefinitionId Id of flow defintion
     */
    public void undeployFlowDefinition(String flowDefinitionId);
}