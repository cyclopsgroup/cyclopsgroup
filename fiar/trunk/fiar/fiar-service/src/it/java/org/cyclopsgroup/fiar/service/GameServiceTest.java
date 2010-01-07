package org.cyclopsgroup.fiar.service;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.cyclopsgroup.fiar.GameService;

public class GameServiceTest
    extends GameServiceVerifier
{
    /**
     * @inheritDoc
     */
    @Override
    protected GameService createGameService()
    {
        return JAXRSClientFactory.create( "http://localhost:8080/fiar-service/game", GameService.class );
    }
}
