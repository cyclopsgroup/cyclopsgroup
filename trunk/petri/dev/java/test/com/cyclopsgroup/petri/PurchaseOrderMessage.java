/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.petri;

import com.cyclopsgroup.petri.message.SubjectMessage;

/**
 * Message for testing
 *
 * @author <a href="mailto:jiaqi.guo@evavi.com">Jiaqi Guo</a>
 */
public class PurchaseOrderMessage extends SubjectMessage
{

	/**
	 * Constructor of class PurchaseOrderMessage
	 *
	 * @param po
	 * @param action
	 */
	public PurchaseOrderMessage(PurchaseOrder po, String action)
	{
		super(po, action);
	}

	/**
	 * Constructor of class PurchaseOrderMessage
	 *
	 * @param po
	 * @param action
	 * @param user
	 */
	public PurchaseOrderMessage(PurchaseOrder po, String action, String user)
	{
		super(po, action, user);
	}

	/**
	 * Override method getSubjectId() in super class
	 *
	 * @see com.cyclopsgroup.petri.message.SubjectAware#getSubjectId()
	 */
	public String getSubjectId()
	{
		PurchaseOrder po = (PurchaseOrder) getSubject();
		return po.getId();
	}

}