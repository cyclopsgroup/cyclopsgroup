/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.petri.engine;

import com.cyclopsgroup.gearset.beans.Context;
import com.cyclopsgroup.gearset.beans.SimpleLogEnabled;
import com.cyclopsgroup.petri.Case;
import com.cyclopsgroup.petri.definition.Guard;
import com.cyclopsgroup.petri.definition.State;
import com.cyclopsgroup.petri.definition.Transition;
import com.cyclopsgroup.petri.persistence.PersistenceManager;

/**
 * Transition executor
 * 
 * @author <a href="mailto:jiaqi.guo@evavi.com">Jiaqi Guo </a>
 */
public class TransitionJobExecutor extends SimpleLogEnabled
{
    private static final TransitionJobExecutor ONLY_INSTANCE = new TransitionJobExecutor();

    /**
     * Get only instance
     * 
     * @return
     */
    public static final TransitionJobExecutor getInstance()
    {
        return ONLY_INSTANCE;
    }

    private TransitionJobExecutor()
    {

    }

    private boolean checkGuard(Guard guard, Context context)
    {
        if (guard == null)
        {
            return true;
        }
        try
        {
            return guard.evaluate(context);
        }
        catch (Exception e)
        {
            getLogger().warn("Guard checking error", e);
            return false;
        }
    }

    /**
     * Check is the case is in the position to fire
     * 
     * @param transition Transition
     * @param caseWrapper Case
     * @return Should this transition be triggered?
     */
    public boolean checkPosition(Transition transition, CaseWrapper caseWrapper)
    {
        State[] froms = transition.getFromStates();
        if (transition.getInputStatesPolicy() == Transition.OR)
        {
            boolean blocked = true;
            for (int i = 0; i < froms.length; i++)
            {
                if (caseWrapper.getCurrentStateSet().contains(froms[i].getId()))
                {
                    blocked = false;
                    break;
                }
            }
            if (blocked)
            {
                return false;
            }
        }
        else if (transition.getInputStatesPolicy() == Transition.AND)
        {
            boolean blocked = false;
            for (int i = 0; i < froms.length; i++)
            {
                if (!caseWrapper.getCurrentStateSet()
                        .contains(froms[i].getId()))
                {
                    blocked = true;
                    break;
                }
            }
            if (blocked)
            {
                return false;
            }
        }
        return true;
    }

    private void doTransition(Transition transition, CaseWrapper caseWrapper,
            Context context, PersistenceManager persistenceManager)
    {
        State[] froms = transition.getFromStates();
        State[] tos = transition.getToStates();
        for (int i = 0; i < froms.length; i++)
        {
            State from = froms[i];
            caseWrapper.getCurrentStateSet().remove(from.getId());
        }
        for (int i = 0; i < tos.length; i++)
        {
            State to = tos[i];
            caseWrapper.getCurrentStateSet().add(to.getId());
        }
        persistenceManager.updateCase(transition, caseWrapper);
        if (transition.getTask() != null)
        {
            try
            {
                transition.getTask().execute(context);
            }
            catch (Exception e)
            {
                getLogger().warn("Task execution error", e);
            }
        }
    }

    /**
     * Method executeInitialTransition() in class TransitionExecutor
     * 
     * @param transition Transition to execute
     * @param context Runtime context for certain case
     * @param persistenceManager Persistence manager object
     * @return Created case object
     */
    public CaseWrapper executeInitialTransition(Transition transition,
            Context context, PersistenceManager persistenceManager)
    {
        if (!transition.isInitial())
        {
            return null;
        }
        if (!checkGuard(transition.getGuard(), context))
        {
            return null;
        }
        Case persistenceCase = persistenceManager.createCase(transition
                .getFlowDefinition(), context);
        CaseWrapper cw = new CaseWrapper(persistenceCase);
        cw.getCurrentStateSet().clear();
        cw.getCurrentStateSet().add(
                transition.getFlowDefinition().getEntrance().getId());
        context.put("workflowCase", cw);
        doTransition(transition, cw, context, persistenceManager);
        return cw;
    }

    /**
     * Execute context for certain case
     * 
     * @param transition Transition to execute
     * @param caseWrapper Case wrapper object
     * @param context Runtime context for certain case
     * @param persistenceManager Persistence manager object
     */
    public void executeStandardTransition(Transition transition,
            CaseWrapper caseWrapper, Context context,
            PersistenceManager persistenceManager)
    {
        if (transition.isInitial())
        {
            return;
        }
        if (!checkPosition(transition, caseWrapper))
        {
            return;
        }
        if (!checkGuard(transition.getGuard(), context))
        {
            return;
        }
        doTransition(transition, caseWrapper, context, persistenceManager);
    }
}