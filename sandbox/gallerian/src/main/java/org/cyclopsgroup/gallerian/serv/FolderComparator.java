package org.cyclopsgroup.gallerian.serv;

import java.util.Comparator;

import org.cyclopsgroup.gallerian.Folder;

/**
 * Folder comparator for sorting
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
class FolderComparator
    implements Comparator<Folder>
{
    /**
     * @inheritDoc
     */
    @Override
    public int compare( Folder f1, Folder f2 )
    {
        return f1.getName().compareTo( f2.getName() );
    }
}
