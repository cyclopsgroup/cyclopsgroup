/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.petri.engine;

import java.util.HashSet;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;

import com.cyclopsgroup.petri.Case;

/**
 * Default implementation of Case
 *
 * @author <a href="mailto:jiaqi.guo@evavi.com">Jiaqi Guo</a>
 */
public class CaseWrapper implements Case
{
	private HashSet states = new HashSet();

	private Case wrappedCase;

	/**
	 * Constructor of class CaseWrapper
	 *
	 * @param realCase Real case object came from persistence manager
	 */
	public CaseWrapper(Case realCase)
	{
		wrappedCase = realCase;
		CollectionUtils.addAll(states, realCase.getCurrentStates());
	}

	/**
	 * Override method getAttributes() in super class
	 *
	 * @see com.cyclopsgroup.petri.Case#getAttributes()
	 */
	public Map getAttributes()
	{
		return wrappedCase.getAttributes();
	}

	/**
	 * Override method getCurrentStates() in super class
	 *
	 * @see com.cyclopsgroup.petri.Case#getCurrentStates()
	 */
	public String[] getCurrentStates()
	{
		return (String[]) states.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
	}

	/**
	 * Get modifiable current states
	 *
	 * @return HashSet object which contains the id of states
	 */
	public HashSet getCurrentStateSet()
	{
		return states;
	}

	/**
	 * Override method getFlowDefinitionName() in super class
	 *
	 * @see com.cyclopsgroup.petri.Case#getFlowDefinitionName()
	 */
	public String getFlowDefinitionName()
	{
		return wrappedCase.getFlowDefinitionName();
	}

	/**
	 * Override method getId() in super class
	 *
	 * @see com.cyclopsgroup.petri.Case#getId()
	 */
	public String getId()
	{
		return wrappedCase.getId();
	}
}