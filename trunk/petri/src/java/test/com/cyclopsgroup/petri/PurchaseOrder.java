/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.petri;

/**
 * TODO Add java doc for this class
 *
 * @author <a href="mailto:jiaqi.guo@evavi.com">Jiaqi Guo</a>
 */
public class PurchaseOrder
{
	private String code;

	private String id;

	private int totalAmount;

	/**
	 * Constructor of class PurchaseOrder
	 *
	 * @param poId
	 * @param poTotalAmount
	 */
	public PurchaseOrder(String poId, int poTotalAmount)
	{
		this(poId, poId, poTotalAmount);
	}

	/**
	 * Constructor of class PurchaseOrder
	 *
	 * @param poId
	 * @param poCode
	 * @param poTotalAmount
	 */
	public PurchaseOrder(String poId, String poCode, int poTotalAmount)
	{
		id = poId;
		code = poCode;
		totalAmount = poTotalAmount;
	}

	/**
	 * Getter method for property code
	 *
	 * @return Returns the code.
	 */
	public String getCode()
	{
		return code;
	}

	/**
	 * Getter method for property id
	 *
	 * @return Returns the id.
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * Getter method for property totalAmount
	 *
	 * @return Returns the totalAmount.
	 */
	public int getTotalAmount()
	{
		return totalAmount;
	}

	/**
	 * Override method toString() in super class
	 *
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		return "Order (id=" + id + ", code=" + code + ",totalAmount="
				+ totalAmount + ")";
	}
}