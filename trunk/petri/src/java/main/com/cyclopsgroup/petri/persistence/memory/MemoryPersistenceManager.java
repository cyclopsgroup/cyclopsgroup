/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.petri.persistence.memory;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.petri.Case;
import com.cyclopsgroup.petri.definition.NetDefinition;
import com.cyclopsgroup.petri.definition.Transition;
import com.cyclopsgroup.petri.persistence.PersistenceManager;
import com.cyclopsgroup.petri.persistence.Schedule;

/**
 * Memory implemented persistence manager
 *
 * @author <a href="mailto:jiaqi.guo@evavi.com">Jiaqi Guo</a>
 */
public class MemoryPersistenceManager implements PersistenceManager
{
    private static long uniqueId = 0;

    private HashMap caseRepository = new HashMap();

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.petri.persistence.PersistenceManager#createCase(com.cyclopsgroup.petri.definition.FlowDefinition, java.util.Map)
     */
    public Case createCase(NetDefinition flowDefinition, Map initialAttributes)
    {
        MemoryCase mc = new MemoryCase("" + uniqueId++, flowDefinition.getId());
        if (initialAttributes != null)
        {
            mc.getAttributes().putAll(initialAttributes);
        }
        caseRepository.put(mc.getId(), mc);
        return mc;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.petri.persistence.PersistenceManager#disposeCase(com.cyclopsgroup.petri.definition.FlowDefinition, com.cyclopsgroup.petri.Case)
     */
    public void disposeCase(NetDefinition flowDefinition, Case workflowCase)
    {
        caseRepository.remove(workflowCase.getId());
        System.out.println("Case " + workflowCase.getId() + " disposed");
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.petri.persistence.PersistenceManager#loadCase(com.cyclopsgroup.petri.definition.FlowDefinition, java.lang.String)
     */
    public Case loadCase(NetDefinition flowDefinition, String subjectId)
            throws Exception
    {
        Case ret = null;
        for (Iterator i = caseRepository.values().iterator(); i.hasNext();)
        {
            Case workflowCase = (Case) i.next();
            if (StringUtils.equals(workflowCase.getFlowId(), flowDefinition
                    .getId()))
            {
                if (ObjectUtils.equals(workflowCase.getAttributes().get(
                        "subjectId"), subjectId))
                {
                    ret = workflowCase;
                }
            }
        }
        return ret;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.petri.persistence.PersistenceManager#loadCase(java.lang.String)
     */
    public Case loadCase(String caseId)
    {
        return (Case) caseRepository.get(caseId);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.petri.persistence.PersistenceManager#loadCases(com.cyclopsgroup.petri.definition.Transition, java.lang.Object)
     */
    public Case[] loadCases(Transition waitingTransition, Object message)
    {
        return (Case[]) caseRepository.values().toArray(Case.EMPTY_ARRAY);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.petri.persistence.PersistenceManager#loadSchedules(com.cyclopsgroup.petri.definition.FlowDefinition)
     */
    public Schedule[] loadSchedules(NetDefinition flowDefinition)
            throws Exception
    {
        return Schedule.EMPTY_ARRAY;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.petri.persistence.PersistenceManager#schedule(com.cyclopsgroup.petri.definition.Transition, com.cyclopsgroup.petri.Case, java.util.Date)
     */
    public void schedule(Transition transition, Case worflowCase, Date occurTime)
    {
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.petri.persistence.PersistenceManager#schedule(com.cyclopsgroup.petri.definition.Transition, com.cyclopsgroup.petri.Case, java.lang.String)
     */
    public void schedule(Transition transition, Case workflowCase,
            String cronExpression)
    {
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.petri.persistence.PersistenceManager#unschedule(com.cyclopsgroup.petri.definition.Transition, com.cyclopsgroup.petri.Case)
     */
    public void unschedule(Transition transition, Case workflowCase)
            throws Exception
    {
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.petri.persistence.PersistenceManager#updateCase(com.cyclopsgroup.petri.definition.Transition, com.cyclopsgroup.petri.Case)
     */
    public void updateCase(Transition transition, Case workflowCase)
    {
        MemoryCase memoryCase = (MemoryCase) loadCase(workflowCase.getId());
        memoryCase.copyFrom(workflowCase);
        HashSet states = new HashSet();
        CollectionUtils.addAll(states, workflowCase.getCurrentStates());
        System.out.println("Case " + workflowCase.getId() + " updated to "
                + states);
    }
}