/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.petri.message;

import java.util.Iterator;
import java.util.LinkedList;

import org.apache.avalon.framework.logger.AbstractLogEnabled;

/**
 * message manager
 *
 * @author <a href="mailto:jiaqi.guo@evavi.com">Jiaqi Guo</a>
 */
public class DefaultMessageManager extends AbstractLogEnabled implements
        MessageManager
{

    private LinkedList listeners = new LinkedList();

    /**
     * Method addMessageListener() in class MessagingManager
     *
     * @param listener
     */
    public void addMessageListener(MessageListener listener)
    {
        listeners.add(listener);
    }

    /**
     * Override method clearListeners() in super class
     *
     * @see com.cyclopsgroup.petri.message.MessageManager#clearListeners()
     */
    public void clearListeners()
    {
        listeners.clear();
    }

    /**
     * Override method getListeners() in super class
     *
     * @see com.cyclopsgroup.petri.message.MessageManager#getListeners()
     */
    public MessageListener[] getListeners()
    {
        return (MessageListener[]) listeners
                .toArray(MessageListener.EMPTY_ARRAY);
    }

    /**
     * Override method receiveMessage() in super class
     *
     * @see com.cyclopsgroup.petri.message.MessageManager#receiveMessage(java.lang.Object)
     */
    public void receiveMessage(Object message)
    {
        for (Iterator i = listeners.iterator(); i.hasNext();)
        {
            MessageListener listener = (MessageListener) i.next();
            synchronized (listener)
            {
                try
                {
                    listener.handleMessage(message);
                }
                catch (Exception e)
                {
                    getLogger().warn("Handle message error", e);
                }
            }
        }
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.petri.message.MessageManager#removeMessageListener(com.cyclopsgroup.petri.message.MessageListener)
     */
    public void removeMessageListener(MessageListener listener)
    {
        listeners.remove(listener);
    }
}