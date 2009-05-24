package org.cyclopsgroup.gallerian.jcvfs;

import org.apache.commons.vfs.FileObject;
import org.apache.commons.vfs.FileSystemException;
import org.apache.commons.vfs.FileSystemManager;
import org.apache.commons.vfs.VFS;
import org.cyclopsgroup.gallerian.spi.RepositoryProvider;
import org.cyclopsgroup.gallerian.spi.RepositoryProviderFactory;

/**
 * Implementation of {@link RepositoryProviderFactory} using Jakarta commons VFS
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class VfsRepositoryProviderFactory
    implements RepositoryProviderFactory
{
    private static final String URI_PREFIX = "vfs:";

    private final FileSystemManager fsManager;

    /**
     * Default constructor that instantiate file system manager
     * 
     * @throws FileSystemException If file system can't be instantiate
     */
    public VfsRepositoryProviderFactory()
        throws FileSystemException
    {
        this.fsManager = VFS.getManager();
    }

    /**
     * @inheritDoc
     */
    @Override
    public RepositoryProvider createProvider( String uri )
    {
        try
        {
            FileObject file = fsManager.resolveFile( uri.substring( URI_PREFIX.length() ) );
            return new VfsRepositoryProvider( file, uri, fsManager );
        }
        catch ( FileSystemException e )
        {
            throw new VfsRuntimeException( "Can't create provider with uri " + uri, e );
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean accept( String uri )
    {
        return uri.startsWith( URI_PREFIX );
    }
}
