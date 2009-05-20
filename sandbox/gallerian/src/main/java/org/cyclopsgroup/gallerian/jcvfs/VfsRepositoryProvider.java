package org.cyclopsgroup.gallerian.jcvfs;

import org.apache.commons.vfs.FileObject;
import org.cyclopsgroup.gallerian.spi.RepositoryProvider;

/**
 * Implementation of {@link RepositoryProvider} with VFS {@link FileObject}
 *
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
class VfsRepositoryProvider
    implements RepositoryProvider
{
    private final FileObject file;

    private final String uri;
    
    /**
     * @param file VFS file object
     */
    VfsRepositoryProvider( FileObject file, String uri )
    {
        this.file = file;
        this.uri = uri;
    }

    /**
     * @inheritDoc
     */
    @Override
    public final String getUri()
    {
        return uri;
    }
}
