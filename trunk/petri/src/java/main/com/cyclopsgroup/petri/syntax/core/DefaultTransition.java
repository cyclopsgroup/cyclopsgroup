/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.petri.syntax.core;

import java.util.Hashtable;

import com.cyclopsgroup.gearset.beans.Context;
import com.cyclopsgroup.petri.definition.AutomaticTrigger;
import com.cyclopsgroup.petri.definition.NetDefinition;
import com.cyclopsgroup.petri.definition.Guard;
import com.cyclopsgroup.petri.definition.Place;
import com.cyclopsgroup.petri.definition.Task;
import com.cyclopsgroup.petri.definition.Transition;
import com.cyclopsgroup.petri.definition.Trigger;

/**
 * Default implementation of transition
 * 
 * @author <a href="mailto:jiaqi.guo@evavi.com">Jiaqi Guo </a>
 */
public class DefaultTransition implements Transition
{

    private static final Guard DEFAULT_GUARD = new Guard()
    {

        public boolean evaluate(Context context) throws Exception
        {
            return true;
        }
    };

    private static final Task DEFAULT_TASK = new Task()
    {

        public void execute(Context context) throws Exception
        {
        }
    };

    private static final Trigger DEFAULT_TRIGGER = new AutomaticTrigger()
    {
    };

    private NetDefinition flowDefinition;

    private Hashtable fromStates = new Hashtable();

    private Guard guard = DEFAULT_GUARD;

    private String id;

    private boolean initial = false;

    private InputStatesPolicy inputStatesPolicy = OR;

    private Task task = DEFAULT_TASK;

    private boolean terminal = false;

    private Hashtable toStates = new Hashtable();

    private Trigger trigger = DEFAULT_TRIGGER;

    /**
     * Constructor of class DefaultTransition
     * 
     * @param transitionId Transition ID
     * @param definition Flow definition
     */
    public DefaultTransition(String transitionId, NetDefinition definition)
    {
        id = transitionId;
        flowDefinition = definition;
    }

    /**
     * Add from state
     * 
     * @param state
     */
    public void addFromState(Place state)
    {
        fromStates.put(state.getId(), state);
    }

    /**
     * Add to state
     * 
     * @param state
     */
    public void addToState(Place state)
    {
        toStates.put(state.getId(), state);
    }

    /**
     * Override method getFlowDefinition() in super class
     * 
     * @see com.cyclopsgroup.petri.definition.Transition#getFlowDefinition()
     */
    public NetDefinition getFlowDefinition()
    {
        return flowDefinition;
    }

    /**
     * Override method getFromStates() in super class
     * 
     * @see com.cyclopsgroup.petri.definition.Transition#getFromStates()
     */
    public Place[] getFromStates()
    {
        return (Place[]) fromStates.values().toArray(Place.EMPTY_ARRAY);
    }

    /**
     * Override method getGuard() in super class
     * 
     * @see com.cyclopsgroup.petri.definition.Transition#getGuard()
     */
    public Guard getGuard()
    {
        return guard;
    }

    /**
     * Override method getId() in super class
     * 
     * @see com.cyclopsgroup.petri.definition.Transition#getId()
     */
    public String getId()
    {
        return id;
    }

    /**
     * Override method getInputStatesPolicy() in super class
     * 
     * @see com.cyclopsgroup.petri.definition.Transition#getInputStatesPolicy()
     */
    public InputStatesPolicy getInputStatesPolicy()
    {
        return inputStatesPolicy;
    }

    /**
     * Override method getTask() in super class
     * 
     * @see com.cyclopsgroup.petri.definition.Transition#getTask()
     */
    public Task getTask()
    {
        return task;
    }

    /**
     * Override method getToStates() in super class
     * 
     * @see com.cyclopsgroup.petri.definition.Transition#getToStates()
     */
    public Place[] getToStates()
    {
        return (Place[]) toStates.values().toArray(Place.EMPTY_ARRAY);
    }

    /**
     * Override method getTrigger() in super class
     * 
     * @see com.cyclopsgroup.petri.definition.Transition#getTrigger()
     */
    public Trigger getTrigger()
    {
        return trigger;
    }

    /**
     * Override method isInitial() in super class
     * 
     * @see com.cyclopsgroup.petri.definition.Transition#isInitial()
     */
    public boolean isInitial()
    {
        return initial;
    }

    /**
     * Override method isTerminal() in super class
     * 
     * @see com.cyclopsgroup.petri.definition.Transition#isTerminal()
     */
    public boolean isTerminal()
    {
        return terminal;
    }

    /**
     * Setter method for property guard
     * 
     * @param transitionGuard The guard to set.
     */
    public void setGuard(Guard transitionGuard)
    {
        guard = transitionGuard;
    }

    /**
     * Setter method for property initial
     * 
     * @param isInitial The initial to set.
     */
    public void setInitial(boolean isInitial)
    {
        initial = isInitial;
    }

    /**
     * Setter method for property inputStatesPolicy
     * 
     * @param policy The inputStatesPolicy to set.
     */
    public void setInputStatesPolicy(InputStatesPolicy policy)
    {
        inputStatesPolicy = policy;
    }

    /**
     * Setter method for property task
     * 
     * @param transitionTask The task to set.
     */
    public void setTask(Task transitionTask)
    {
        task = transitionTask;
    }

    /**
     * Setter method for property terminal
     * 
     * @param isTerminal The terminal to set.
     */
    public void setTerminal(boolean isTerminal)
    {
        terminal = isTerminal;
    }

    /**
     * Setter method for property trigger
     * 
     * @param transitionTrigger The trigger to set.
     */
    public void setTrigger(Trigger transitionTrigger)
    {
        trigger = transitionTrigger;
    }
}