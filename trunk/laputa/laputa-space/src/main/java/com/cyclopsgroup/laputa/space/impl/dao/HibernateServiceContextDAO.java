package com.cyclopsgroup.laputa.space.impl.dao;

import java.util.List;

import org.hibernate.Query;

import com.cyclopsgroup.arapaho.hibernate.BaseHibernateGeneticDAO;
import com.cyclopsgroup.laputa.space.dao.ServiceContextDAO;
import com.cyclopsgroup.laputa.space.pojo.SpaceServiceContext;

public class HibernateServiceContextDAO
    extends BaseHibernateGeneticDAO<SpaceServiceContext, Long>
    implements ServiceContextDAO
{

    @SuppressWarnings("unchecked")
    public SpaceServiceContext findByUserAccount( long userAccountId )
    {
        Query query = getSession().getNamedQuery( "findServiceContextByUserAccountId" );
        query.setParameter( "userAccountId", userAccountId );
        List<SpaceServiceContext> contexts = query.list();
        if ( contexts.isEmpty() )
        {
            return null;
        }
        if ( contexts.size() > 1 )
        {
            throw new IllegalStateException( "There are multiple contexts associated with user account "
                + userAccountId );
        }
        return contexts.get( 0 );
    }

    public SpaceServiceContext createForUserAccount( long userAccountId, String title )
    {
        SpaceServiceContext ssc = new SpaceServiceContext();
        ssc.setUserAccountId( userAccountId );
        ssc.setTitle( title );
        ssc.setHidden( true );
        getSession().save( ssc );
        return ssc;
    }
}
