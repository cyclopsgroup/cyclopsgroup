/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.petri.engine;

import java.util.HashSet;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;

import com.cyclopsgroup.petri.Case;

/**
 * Default implementation of Case
 *
 * @author <a href="mailto:jiaqi.guo@evavi.com">Jiaqi Guo</a>
 */
public class StateMachineCase implements Case
{

    private HashSet states = new HashSet();

    private Case wrappedCase;

    /**
     * Constructor of class CaseWrapper
     *
     * @param realCase Real case object came from persistence manager
     */
    public StateMachineCase(Case realCase)
    {
        wrappedCase = realCase;
        CollectionUtils.addAll(states, realCase.getCurrentStates());
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.petri.Case#getAttributes()
     */
    public Map getAttributes()
    {
        return wrappedCase.getAttributes();
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.petri.Case#getCurrentStates()
     */
    public String[] getCurrentStates()
    {
        return (String[]) states.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
    }

    /**
     * Get modifiable current states
     *
     * @return HashSet object which contains the id of states
     */
    public HashSet getCurrentStateSet()
    {
        return states;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.petri.Case#getFlowId()
     */
    public String getFlowId()
    {
        return wrappedCase.getFlowId();
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.petri.Case#getId()
     */
    public String getId()
    {
        return wrappedCase.getId();
    }

    /**
     * Override method toString() in super class
     *
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        return wrappedCase.toString() + " at states " + states;
    }
}