package org.cyclopsgroup.gallerian.jcvfs;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.vfs.FileObject;
import org.apache.commons.vfs.FileSystemException;
import org.apache.commons.vfs.FileSystemManager;
import org.apache.commons.vfs.FileType;
import org.cyclopsgroup.gallerian.spi.FileProvider;
import org.cyclopsgroup.gallerian.spi.FolderProvider;
import org.cyclopsgroup.gallerian.spi.RepositoryProvider;

/**
 * Implementation of {@link RepositoryProvider} with VFS {@link FileObject}
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
class VfsRepositoryProvider
    implements RepositoryProvider
{
    private final VfsFolderProvider rootFolder;

    private final String uri;

    private final FileSystemManager fileSystem;

    /**
     * @param file VFS file object
     * @param uri URI of repository
     * @param fileSystem VFS file system manager handler
     */
    VfsRepositoryProvider( FileObject file, String uri, FileSystemManager fileSystem )
    {
        this.rootFolder = new VfsFolderProvider( "/", file, this );
        this.uri = uri;
        this.fileSystem = fileSystem;
    }

    /**
     * @inheritDoc
     */
    @Override
    public FileProvider findFile( String path )
    {
        FileObject fileObject;
        try
        {
            fileObject = fileSystem.resolveFile( rootFolder.fileObject, "." + path );
        }
        catch ( FileSystemException e )
        {
            throw new VfsRuntimeException( "Can't find folder " + path + " in " + this, e );
        }
        return new VfsFileProvider( path, fileObject, this );
    }

    /**
     * @inheritDoc
     */
    @Override
    public FolderProvider findFolder( String path )
    {
        FileObject fileObject;
        try
        {
            fileObject = fileSystem.resolveFile( rootFolder.fileObject, "." + path );
        }
        catch ( FileSystemException e )
        {
            throw new VfsRuntimeException( "Can't find folder " + path + " in " + this, e );
        }
        return new VfsFolderProvider( path, fileObject, this );
    }

    /**
     * @inheritDoc
     */
    @Override
    public FolderProvider getRootFolder()
    {
        return rootFolder;
    }

    /**
     * @inheritDoc
     */
    @Override
    public final String getUri()
    {
        return uri;
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<FileProvider> listFiles( FolderProvider parent )
    {
        VfsFolderProvider p = (VfsFolderProvider) parent;
        List<FileProvider> files = new ArrayList<FileProvider>();
        try
        {
            for ( FileObject f : p.fileObject.getChildren() )
            {
                if ( f.getType() != FileType.FILE )
                {
                    continue;
                }
                files.add( new VfsFileProvider( p.getPath() + "/" + f.getName().getBaseName(), f, this ) );
            }
            return files;
        }
        catch ( FileSystemException e )
        {
            throw new VfsRuntimeException( "Couldn't get children of " + parent, e );
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<FolderProvider> listFolders( FolderProvider parent )
    {
        VfsFolderProvider p = (VfsFolderProvider) parent;
        List<FolderProvider> folders = new ArrayList<FolderProvider>();
        try
        {
            for ( FileObject f : p.fileObject.getChildren() )
            {
                if ( f.getType() != FileType.FOLDER )
                {
                    continue;
                }
                folders.add( new VfsFolderProvider( p.getPath() + "/" + f.getName().getBaseName(), f, this ) );
            }
            return folders;
        }
        catch ( FileSystemException e )
        {
            throw new VfsRuntimeException( "Couldn't get children of " + parent, e );
        }
    }
}
