package org.cyclopsgroup.gallerian.spi;

/**
 * Describe a folder
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public interface FolderProvider
{
    /**
     * @return Full path of folder starting with slash
     */
    String getPath();

    /**
     * @return Name of folder
     */
    String getName();
    
    /**
     * @return A reference to repository
     */
    RepositoryProvider getRepository();
}
