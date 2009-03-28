package com.cyclopsgroup.tornado.hibernate;

import java.util.Iterator;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

import com.cyclopsgroup.waterview.LargeList;

public class CriteriaLargeList
    implements LargeList
{
    private Criteria criteria;

    public CriteriaLargeList(Criteria criteria)
    {
        this.criteria = criteria;
    }

    /**
     * @see com.cyclopsgroup.waterview.LargeList#getSize()
     */
    public int getSize()
        throws Exception
    {
        return UNKNOWN_SIZE;
    }

    /**
     * @see com.cyclopsgroup.waterview.LargeList#iterate(int, int, com.cyclopsgroup.waterview.LargeList.Sorting[])
     */
    public Iterator iterate( int startPosition, int maxAmount, Sorting[] sortings )
        throws Exception
    {
        for ( int i = 0; i < sortings.length; i++ )
        {
            Sorting sorting = sortings[i];
            if(sorting.isDescending())
            {
                criteria.addOrder(Order.desc(sorting.getName()));
            } else {
                criteria.addOrder(Order.asc(sorting.getName()));
            }
        }
        criteria.setFirstResult(startPosition);
        if(maxAmount != UNLIMITED_MAX_AMOUNT)
        {
            criteria.setMaxResults(maxAmount);
        }
        return criteria.list().iterator();
    }
}
