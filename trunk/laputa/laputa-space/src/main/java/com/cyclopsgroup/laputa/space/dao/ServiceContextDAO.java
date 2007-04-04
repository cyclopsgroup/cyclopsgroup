package com.cyclopsgroup.laputa.space.dao;

import com.cyclopsgroup.laputa.space.pojo.SpaceServiceContext;

public interface ServiceContextDAO
{
    SpaceServiceContext findByUserAccount( long userAccountId );

    void save( SpaceServiceContext ctx );
}
