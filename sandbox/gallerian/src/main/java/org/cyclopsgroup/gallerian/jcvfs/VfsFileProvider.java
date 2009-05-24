package org.cyclopsgroup.gallerian.jcvfs;

import org.apache.commons.vfs.FileObject;
import org.apache.commons.vfs.FileSystemException;
import org.cyclopsgroup.gallerian.spi.FileProvider;

/**
 * VFS specific implementation of file provider
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
class VfsFileProvider
    extends VfsFolderProvider
    implements FileProvider
{
    /**
     * @param path Given path of file
     * @param file VFS file object
     * @param repository A reference to repository
     */
    VfsFileProvider( String path, FileObject file, VfsRepositoryProvider repository )
    {
        super( path, file, repository );
    }

    /**
     * @inheritDoc
     */
    @Override
    public long getLastUpdate()
    {
        try
        {
            return fileObject.getContent().getLastModifiedTime();
        }
        catch ( FileSystemException e )
        {
            throw new VfsRuntimeException("Can't get last modified date of " + fileObject, e);
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public long getSize()
    {
        try
        {
            return fileObject.getContent().getSize();
        }
        catch ( FileSystemException e )
        {
            throw new VfsRuntimeException("Can't get size of " + fileObject, e);
        }
    }

}
