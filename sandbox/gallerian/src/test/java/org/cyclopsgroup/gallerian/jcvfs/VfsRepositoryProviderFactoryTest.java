package org.cyclopsgroup.gallerian.jcvfs;

import java.io.File;

import org.apache.commons.vfs.FileSystemException;
import org.cyclopsgroup.gallerian.spi.RepositoryProvider;
import org.cyclopsgroup.gallerian.spi.RepositoryProviderFactory;
import org.junit.Test;

public class VfsRepositoryProviderFactoryTest
{
    @Test
    public void testCreateProvider()
        throws FileSystemException
    {
        String srcDir = new File( "src" ).getAbsolutePath();
        RepositoryProviderFactory factory = new VfsRepositoryProviderFactory();

        RepositoryProvider provider = factory.createProvider( "vfs:" + srcDir );
    }
}
