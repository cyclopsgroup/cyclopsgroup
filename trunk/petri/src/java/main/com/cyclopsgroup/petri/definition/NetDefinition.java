/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.petri.definition;

/**
 * State machine model
 *
 * @author <a href="mailto:jiaqi.guo@evavi.com">Jiaqi Guo</a>
 */
public interface NetDefinition
{
    /** Empty array */
    NetDefinition[] EMPTY_ARRAY = new NetDefinition[0];

    /**
     * Get entrance state
     *
     * @return Entrance state
     */
    Place getEntrance();

    /**
     * Get exit state
     *
     * @return Exit state
     */
    Place getExit();

    /**
     * Get Identification of this flow definition
     *
     * @return Full name
     */
    String getId();

    /**
     * Get specified state
     *
     * @param stateId State id
     * @return State object or null if not found
     */
    Place getState(String stateId);

    /**
     * Get all states
     *
     * @return State object array
     */
    Place[] getStates();

    /**
     * Method getTransition() in class FlowDefinition
     *
     * @param transitionId
     * @return Transition model
     */
    Transition getTransition(String transitionId);

    /**
     * Get all transitions in this flow
     *
     * @return Array of transitions
     */
    Transition[] getTransitions();
}