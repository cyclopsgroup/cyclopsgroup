/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.petri.engine;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import org.apache.avalon.framework.activity.Initializable;
import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;

import com.cyclopsgroup.petri.Case;
import com.cyclopsgroup.petri.Workflow;
import com.cyclopsgroup.petri.WorkflowException;
import com.cyclopsgroup.petri.definition.NetDefinition;
import com.cyclopsgroup.petri.definition.NetDefinitionLoader;
import com.cyclopsgroup.petri.message.MessageManager;
import com.cyclopsgroup.petri.persistence.PersistenceManager;

/**
 * Default implementation of workflow
 *
 * @author <a href="mailto:jiaqi.guo@evavi.com">Jiaqi Guo</a>
 */
public class DefaultWorkflow extends AbstractLogEnabled implements Workflow,
        Serviceable, Initializable, Configurable
{

    private NetDefinitionLoader definitionLoader;

    private MessageManager messageManager;

    private PersistenceManager persistenceManager;

    private HashSet preloadFlowIds = new HashSet();

    private WorkflowEngine workflowEngine;

    /**
     * Override method configure() in super class
     *
     * @see org.apache.avalon.framework.configuration.Configurable#configure(org.apache.avalon.framework.configuration.Configuration)
     */
    public void configure(Configuration conf) throws ConfigurationException
    {
        Configuration c = conf.getChild("preload");
        if (c == null)
        {
            return;
        }
        Configuration[] ids = c.getChildren("flow-definition");
        for (int i = 0; i < ids.length; i++)
        {
            Configuration id = ids[i];
            preloadFlowIds.add(id.getValue());
        }
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.petri.Workflow#createCase(java.lang.String, java.util.Map)
     */
    public Case createCase(String flowDefinitionId, Map initialAttributes)
    {
        return getWorkflowEngine().startNewCase(flowDefinitionId,
                initialAttributes);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.petri.Workflow#deployFlow(com.cyclopsgroup.petri.definition.NetDefinition)
     */
    public void deployFlow(NetDefinition flowDefinition)
            throws WorkflowException
    {
        getWorkflowEngine().deployFlowDefinition(flowDefinition);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.petri.Workflow#deployFlow(java.lang.String)
     */
    public void deployFlow(String flowId) throws WorkflowException
    {
        NetDefinition flowDefinition = getDefinitionLoader().getFlowDefinition(
                flowId);
        getWorkflowEngine().deployFlowDefinition(flowDefinition);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.petri.Workflow#getCase(java.lang.String)
     */
    public Case getCase(String caseId)
    {
        try
        {
            return getPersistenceManager().loadCase(caseId);
        }
        catch (Exception e)
        {
            getLogger().error("Can not get case", e);
            return null;
        }
    }

    /**
     * Getter method for property definitionLoader
     *
     * @return Returns the definitionLoader.
     */
    public NetDefinitionLoader getDefinitionLoader()
    {
        return definitionLoader;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.petri.Workflow#getFlowDefinition(java.lang.String)
     */
    public NetDefinition getFlowDefinition(String flowDefinitionId)
    {
        return getWorkflowEngine().getFlowDefinition(flowDefinitionId);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.petri.Workflow#getFlowDefinitions()
     */
    public NetDefinition[] getFlowDefinitions()
    {
        return getWorkflowEngine().getFlowDefinitions();
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
     * Getter method for property workflowEngine
     *
     * @return Returns the workflowEngine.
     */
    public WorkflowEngine getWorkflowEngine()
    {
        return workflowEngine;
    }

    /**
     * Override method initialize() in super class
     *
     * @see org.apache.avalon.framework.activity.Initializable#initialize()
     */
    public void initialize() throws Exception
    {
        workflowEngine = new DefaultWorkflowEngine(persistenceManager,
                messageManager);
        workflowEngine.getInitialContext().put("workflow", this);

        for (Iterator i = preloadFlowIds.iterator(); i.hasNext();)
        {
            String flowId = (String) i.next();
            try
            {
                deployFlow(flowId);
            }
            catch (Exception e)
            {
                getLogger()
                        .warn("Can not preload flow definition " + flowId, e);
            }
        }
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.petri.Workflow#receiveMessage(java.lang.Object)
     */
    public void receiveMessage(Object message)
    {
        getWorkflowEngine().receiveMessage(message);
    }

    /**
     * Override method service() in super class
     *
     * @see org.apache.avalon.framework.service.Serviceable#service(org.apache.avalon.framework.service.ServiceManager)
     */
    public void service(ServiceManager serviceManager) throws ServiceException
    {
        persistenceManager = (PersistenceManager) serviceManager
                .lookup(PersistenceManager.ROLE);
        definitionLoader = (NetDefinitionLoader) serviceManager
                .lookup(NetDefinitionLoader.ROLE);
        messageManager = (MessageManager) serviceManager
                .lookup(MessageManager.ROLE);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.petri.Workflow#undeployFlow(java.lang.String)
     */
    public void undeployFlow(String flowDefinitionId)
    {
        getWorkflowEngine().undeployFlowDefinition(flowDefinitionId);
    }
}