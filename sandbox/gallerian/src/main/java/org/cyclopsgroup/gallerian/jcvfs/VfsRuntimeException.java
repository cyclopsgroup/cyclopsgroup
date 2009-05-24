package org.cyclopsgroup.gallerian.jcvfs;

import org.apache.commons.vfs.FileSystemException;

/**
 * Runtime exception wrapper of VSF specific {@link FileSystemException}
 *
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
class VfsRuntimeException
    extends RuntimeException
{
    private static final long serialVersionUID = 3789892477261854525L;

    /**
     * @param description Verbal description of error
     * @param e Included error cause
     */
    VfsRuntimeException( String description, FileSystemException e )
    {
        super( description + ": " + e.getMessage(), e );
    }
}
