/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.petri.engine;

import com.cyclopsgroup.gearset.beans.Context;
import com.cyclopsgroup.petri.persistence.PersistenceManager;

/**
 * TODO Add java doc for this class
 * 
 * @author <a href="mailto:jiaqi.guo@evavi.com">Jiaqi Guo </a>
 */
public interface TransitionJob
{
    /**
     * Method run() in class TransitionJob
     * 
     * @param context
     * @param persistenceManager
     */
    void run(Context context, PersistenceManager persistenceManager);
}