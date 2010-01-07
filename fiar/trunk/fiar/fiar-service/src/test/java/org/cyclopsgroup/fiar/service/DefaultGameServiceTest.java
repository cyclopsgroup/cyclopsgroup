package org.cyclopsgroup.fiar.service;

import org.cyclopsgroup.fiar.GameService;
import org.cyclopsgroup.fiar.service.mem.ConstantUserSessionService;
import org.cyclopsgroup.fiar.service.mem.HashMapGameStorageService;

public class DefaultGameServiceTest
    extends GameServiceVerifier
{
    /**
     * @inheritDoc
     */
    @Override
    protected GameService createGameService()
    {
        return new DefaultGameService( new HashMapGameStorageService(), new ConstantUserSessionService() );
    }
}
