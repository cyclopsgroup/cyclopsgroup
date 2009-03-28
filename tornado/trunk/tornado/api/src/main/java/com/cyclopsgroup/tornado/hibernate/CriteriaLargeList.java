package com.cyclopsgroup.tornado.hibernate;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

import com.cyclopsgroup.waterview.LargeList;

public class CriteriaLargeList<T>
    implements LargeList<T>
{
    private Criteria criteria;

    public CriteriaLargeList( Criteria criteria )
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

    @SuppressWarnings("unchecked")
    public Iterator<T> iterate( int startPosition, int maxAmount, List<Sorting> sortings )
        throws Exception
    {
        for ( Sorting sorting : sortings )
        {
            if ( sorting.isDescending() )
            {
                criteria.addOrder( Order.desc( sorting.getName() ) );
            }
            else
            {
                criteria.addOrder( Order.asc( sorting.getName() ) );
            }
        }
        criteria.setFirstResult( startPosition );
        if ( maxAmount != UNLIMITED_MAX_AMOUNT )
        {
            criteria.setMaxResults( maxAmount );
        }
        return criteria.list().iterator();
    }
}
