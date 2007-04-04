package com.cyclopsgroup.laputa.space.impl.service;

import com.cyclopsgroup.laputa.space.SpaceService;
import com.cyclopsgroup.laputa.space.dao.ServiceContextDAO;
import com.cyclopsgroup.laputa.space.pojo.SpaceServiceContext;

public class DefaultSpaceService
    implements SpaceService
{
    private ServiceContextDAO serviceContextDao;

    public DefaultSpaceService( ServiceContextDAO serviceContextDao )
    {
        this.serviceContextDao = serviceContextDao;
    }

    public SpaceServiceContext getServiceContext( long userAccountId )
    {
        SpaceServiceContext ctx = serviceContextDao.findByUserAccount( userAccountId );
        if ( ctx == null )
        {
            throw new IllegalStateException( "Service context doesn't exist for user account " + userAccountId );
        }
        return ctx;
    }

    public boolean isServiceAvailable( long userAccountId )
    {
        return serviceContextDao.findByUserAccount( userAccountId ) != null;
    }

    public void saveChanges( SpaceServiceContext ctx )
    {
        serviceContextDao.save( ctx );
    }
}
