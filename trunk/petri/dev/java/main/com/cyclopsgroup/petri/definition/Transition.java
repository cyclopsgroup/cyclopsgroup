/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.petri.definition;

import org.apache.commons.lang.enum.Enum;

/**
 * Transition model in finite state machine
 * 
 * @author <a href="mailto:jiaqi.guo@evavi.com">Jiaqi Guo </a>
 */
public interface Transition
{
    /** Multi input states policy class */
    public static final class InputStatesPolicy extends Enum
    {
        /**
         * Get policy object associated to a string
         * 
         * @param value
         *                   String value, and or or
         * @return Policy object or null
         */
        public static InputStatesPolicy valueOf(String value)
        {
            return (InputStatesPolicy) getEnum(InputStatesPolicy.class, value);
        }

        /**
         * Constructor of InputStatesPolicy
         * 
         * @param value
         *                   Value of enum
         */
        InputStatesPolicy(String value)
        {
            super(value);
        }
    }

    /** And policy for multipal input states */
    public static final InputStatesPolicy AND = new InputStatesPolicy("and");

    /** Or policy for multipal input states */
    public static final InputStatesPolicy OR = new InputStatesPolicy("or");

    /** Empty transition array */
    Transition[] EMPTY_ARRAY = new Transition[0];

    /**
     * Get flow definition this transition belongs to
     * 
     * @return State machine object
     */
    FlowDefinition getFlowDefinition();

    /**
     * Get array of from states
     * 
     * @return Array of state objects
     */
    State[] getFromStates();

    /**
     * Get guard
     * 
     * @return Guard object to monitor this transition
     */
    Guard getGuard();

    /**
     * Get id of transition
     * 
     * @return Strng id
     */
    String getId();

    /**
     * Get input states policy
     * 
     * @return Input states policy
     */
    InputStatesPolicy getInputStatesPolicy();

    /**
     * Get task attached to this transition
     * 
     * @return Task object
     */
    Task getTask();

    /**
     * Get array of to states
     * 
     * @return Array of state objects
     */
    State[] getToStates();

    /**
     * Get the trigger of this transition
     * 
     * @return Transition trigger
     */
    Trigger getTrigger();

    /**
     * If this transition is initial transition
     * 
     * @return Initial transition flag
     */
    boolean isInitial();

    /**
     * If this transition is terminal transition
     * 
     * @return Terminal transition flat
     */
    boolean isTerminal();
}