/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.petri.engine;

import junit.framework.TestCase;

import com.cyclopsgroup.petri.PurchaseOrder;
import com.cyclopsgroup.petri.PurchaseOrderMessage;
import com.cyclopsgroup.petri.definition.FlowDefinition;
import com.cyclopsgroup.petri.engine.StateMachine;
import com.cyclopsgroup.petri.persistence.memory.MemoryPersistenceManager;
import com.cyclopsgroup.petri.syntax.FlowDefinitionLoader;

/**
 * Test case for state machine
 *
 * @author <a href="mailto:jiaqi.guo@evavi.com">Jiaqi Guo</a>
 */
public class StateMachineTest extends TestCase
{
	/**
	 * Method testReceiveMessage() in class StateMachineTest
	 *
	 * @throws Exception
	 */
	public void testReceiveMessage() throws Exception
	{
		FlowDefinition fd = FlowDefinitionLoader.getInstance()
				.getFlowDefinition("com.cyclopsgroup.petri.syntax.SimpleFlow1");
		MemoryPersistenceManager mpm = new MemoryPersistenceManager();
		StateMachine sm = new StateMachine(fd, mpm, false);
		PurchaseOrder po1 = new PurchaseOrder("aaa", 11);
		sm.receiveMessage(new PurchaseOrderMessage(po1, "order.create"));
		PurchaseOrder po2 = new PurchaseOrder("bbbb", 19);
		sm.receiveMessage(new PurchaseOrderMessage(po2, "order.create"));
		PurchaseOrder po3 = new PurchaseOrder("ccccc", 5);
		sm.receiveMessage(new PurchaseOrderMessage(po3, "order.create"));
		sm.receiveMessage(new PurchaseOrderMessage(po1, "order.submit"));
		sm.receiveMessage(new PurchaseOrderMessage(po2, "order.submit"));
		sm.receiveMessage(new PurchaseOrderMessage(po3, "order.submit"));
	}
}