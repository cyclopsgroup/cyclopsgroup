/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.petri.engine;

import java.util.LinkedList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cyclopsgroup.petri.definition.NetContext;

/**
 * Synchronized job queue
 *
 * @author <a href="mailto:jiaqi.guo@evavi.com">Jiaqi Guo</a>
 */
public class JobQueue
{
    private static Log logger = LogFactory.getLog(JobQueue.class);

    private LinkedList queue = new LinkedList();

    private StateMachine stateMachine;

    /**
     * Constructor of class JobQueue
     *
     * @param machine
     */
    public JobQueue(StateMachine machine)
    {
        stateMachine = machine;
    }

    /**
     * Add new job to the end of queue
     *
     * @param job Job
     */
    public void append(Job job)
    {
        queue.addLast(job);
    }

    /**
     * Run queue
     *
     * @param context Runtime context
     * @param caseWrapper Case object
     */
    public void run(NetContext context, StateMachineCase caseWrapper)
    {
        while (!queue.isEmpty())
        {
            Job job = (Job) queue.removeFirst();
            try
            {
                job.run(stateMachine, this, context, caseWrapper);
            }
            catch (Exception e)
            {
                logger.error("Run workflow job error", e);
            }
        }
    }
}