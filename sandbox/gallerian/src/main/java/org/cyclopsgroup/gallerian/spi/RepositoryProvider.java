package org.cyclopsgroup.gallerian.spi;

import java.util.List;

/**
 * Provides implementation of repository
 *
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public interface RepositoryProvider
{
    /**
     * @return URI of repository
     */
    String getUri();
    
    /**
     * @param path Full path of folder to find, starting with slash
     * @return Implementation of folder provider
     */
    FolderProvider findFolder(String path);
    
    /**
     * Find file based on given full path
     * 
     * @param path Full file path, starting with slash
     * @return File provider implementation
     */
    FileProvider findFile(String path);
    
    /**
     * @param parent Parent folder
     * @return List of folders under given parent folder
     */
    List<FolderProvider> listFolders(FolderProvider parent);
    
    /**
     * @param parent Parent folder
     * @return List of files under parent folder
     */
    List<FileProvider> listFiles(FolderProvider parent);
    
    /**
     * @return Root folder
     */
    FolderProvider getRootFolder();
}
