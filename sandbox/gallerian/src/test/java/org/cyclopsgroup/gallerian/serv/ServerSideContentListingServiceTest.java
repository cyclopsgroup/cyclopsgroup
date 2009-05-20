package org.cyclopsgroup.gallerian.serv;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.cyclopsgroup.gallerian.Folder;
import org.junit.Test;

/**
 * Test case of {@link ServerSideContentListingService}
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class ServerSideContentListingServiceTest
{
    private final ServerSideContentListingService service = new ServerSideContentListingService();

    /**
     * Run {@link ServerSideContentListingService#register(String, String)} and verify result
     */
    @Test
    public void testRegister()
    {
        String path = new File( "src/test" ).getAbsolutePath();
        service.register( "test", "vfs:" + path );
        assertEquals( 1, service.getRepositories().size() );
        Folder folder = service.getFolder( "test", "/" );
        //assertNotNull(folder);
    }
}
