/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.petri;

import java.util.Map;

import com.cyclopsgroup.petri.definition.NetDefinition;

/**
 * Workflow facade interface
 *
 * @author <a href="mailto:jiaqi.guo@evavi.com">Jiaqi Guo</a>
 */
public interface Workflow
{
    /** Role name in container */
    String ROLE = Workflow.class.getName();

    /**
     * Method createCase() in class Workflow
     *
     * @param flowDefinitionId flow definition id
     * @param initialAttribute Initial attributes
     * @return New case created
     * @throws WorkflowException Workflow exception
     */
    Case createCase(String flowDefinitionId, Map initialAttribute)
            throws WorkflowException;

    /**
     * Dynamically deploy a flow
     *
     * @param flowDefinition Flow definition model
     * @throws WorkflowException Workflow exception
     */
    void deployFlow(NetDefinition flowDefinition) throws WorkflowException;

    /**
     * Method deployFlow() in class Workflow
     *
     * @param flowId Flow definition id
     * @throws WorkflowException Workflow exception
     */
    void deployFlow(String flowId) throws WorkflowException;

    /**
     * Get case object with given case id
     *
     * @param caseId Given case id
     * @return Case object, null if not found
     */
    Case getCase(String caseId);

    /**
     * Get deployed workflow definition object with given id
     *
     * @param flowDefinitionId Flow definition id
     * @return FlowDefinition object or null if not found
     */
    NetDefinition getFlowDefinition(String flowDefinitionId);

    /**
     * Get deployed workflow definitions
     *
     * @return Array of flow definitions
     */
    NetDefinition[] getFlowDefinitions();

    /**
     * Method receiveMessage() in class Workflow
     *
     * @param message
     */
    void receiveMessage(Object message);

    /**
     * Dynamically undeploy a flow
     *
     * @param flowDefinitionId flow definition id
     * @throws WorkflowException Workflow exception
     */
    void undeployFlow(String flowDefinitionId) throws WorkflowException;
}