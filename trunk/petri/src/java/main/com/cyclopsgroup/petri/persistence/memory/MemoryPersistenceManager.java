/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.petri.persistence.memory;

import java.util.HashMap;
import java.util.Map;

import com.cyclopsgroup.gearset.beans.Context;
import com.cyclopsgroup.petri.Case;
import com.cyclopsgroup.petri.definition.FlowDefinition;
import com.cyclopsgroup.petri.definition.Transition;
import com.cyclopsgroup.petri.persistence.PersistenceManager;

/**
 * Memory implemented persistence manager
 * 
 * @author <a href="mailto:jiaqi.guo@evavi.com">Jiaqi Guo </a>
 */
public class MemoryPersistenceManager implements PersistenceManager
{
    private static long uniqueId = 0;

    private HashMap caseRepository = new HashMap();

    /**
     * Override method createCase in super class of MemoryPersistenceManager
     * 
     * @see com.cyclopsgroup.petri.persistence.PersistenceManager#createCase(com.cyclopsgroup.petri.definition.FlowDefinition, com.cyclopsgroup.gearset.beans.Context)
     */
    public Case createCase(FlowDefinition flowDefinition, Context context)
    {
        MemoryCase mc = new MemoryCase("" + uniqueId++, flowDefinition.getId());
        Map initialAttributes = (Map) context.get("initialAttributes");
        if (initialAttributes != null)
        {
            mc.getAttributes().putAll(initialAttributes);
        }
        caseRepository.put(flowDefinition.getId() + "$" + mc.getId(), mc);
        return mc;
    }

    /**
     * Override method loadCase in super class of MemoryPersistenceManager
     * 
     * @see com.cyclopsgroup.petri.persistence.PersistenceManager#loadCase(com.cyclopsgroup.petri.definition.FlowDefinition, java.lang.String)
     */
    public Case loadCase(FlowDefinition flowDefinition, String caseId)
    {
        String key = flowDefinition.getId() + "$" + caseId;
        return (Case) caseRepository.get(key);
    }

    /**
     * Override method loadCases in super class of MemoryPersistenceManager
     * 
     * @see com.cyclopsgroup.petri.persistence.PersistenceManager#loadCases(com.cyclopsgroup.petri.definition.Transition, java.lang.Object)
     */
    public Case[] loadCases(Transition waitingTransition, Object message)
    {
        return (Case[]) caseRepository.values().toArray(Case.EMPTY_ARRAY);
    }

    /**
     * Override method updateCase in super class of MemoryPersistenceManager
     * 
     * @see com.cyclopsgroup.petri.persistence.PersistenceManager#updateCase(com.cyclopsgroup.petri.definition.Transition, com.cyclopsgroup.petri.Case)
     */
    public void updateCase(Transition transition, Case workflowCase)
    {
        MemoryCase memoryCase = (MemoryCase) loadCase(transition
                .getFlowDefinition(), workflowCase.getId());
        memoryCase.copyFrom(workflowCase);
    }
}