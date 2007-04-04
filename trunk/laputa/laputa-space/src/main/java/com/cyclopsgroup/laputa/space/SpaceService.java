package com.cyclopsgroup.laputa.space;

import com.cyclopsgroup.laputa.space.pojo.SpaceServiceContext;

public interface SpaceService
{
    SpaceServiceContext getServiceContext( long userAccountId );

    boolean isServiceAvailable( long userAccountId );
}
