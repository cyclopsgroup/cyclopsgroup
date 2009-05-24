package org.cyclopsgroup.gallerian.jcvfs;

import org.apache.commons.vfs.FileObject;
import org.cyclopsgroup.gallerian.spi.FolderProvider;
import org.cyclopsgroup.gallerian.spi.RepositoryProvider;

/**
 * Vfs specific folder provider
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
class VfsFolderProvider
    implements FolderProvider
{
    /**
     * Final VFS file object
     */
    final FileObject fileObject;

    private final String path;

    private final VfsRepositoryProvider repository;

    /**
     * @param path Given full path
     * @param folder VFS file object
     * @param repository A reference to repository
     */
    VfsFolderProvider( String path, FileObject folder, VfsRepositoryProvider repository )
    {
        this.path = path;
        this.fileObject = folder;
        this.repository = repository;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getName()
    {
        return fileObject.getName().getBaseName();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getPath()
    {
        return path;
    }

    /**
     * @inheritDoc
     */
    @Override
    public RepositoryProvider getRepository()
    {
        return repository;
    }
}
