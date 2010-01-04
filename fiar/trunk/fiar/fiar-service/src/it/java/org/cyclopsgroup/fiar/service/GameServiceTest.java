package org.cyclopsgroup.fiar.service;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.cyclopsgroup.fiar.GameService;
import org.junit.Before;
import org.junit.Test;

public class GameServiceTest
{
    private GameService gameService;

    @Before
    public void setUpGameService()
    {
        gameService = JAXRSClientFactory.create( "http://localhost:8080/fiar-service", GameService.class );
    }

    @Test
    public void testCreateGame()
    {
        System.out.println( gameService.getGameVersion( "12345", "12345" ) );
    }
}
