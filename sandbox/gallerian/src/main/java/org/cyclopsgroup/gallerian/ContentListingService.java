package org.cyclopsgroup.gallerian;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Facade of content viewer
 *
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
@WebService
public interface ContentListingService
{
    /**
     * Get content information based on repository name and path of content
     * 
     * @param repositoryName Repository name
     * @param path Path of content
     * @return Content object
     */
    @WebMethod
    Content getContent(String repositoryName, String path);
    
    /**
     * Get content information based on repository name, path of folder and content name
     * 
     * @param repositoryName Name of repository
     * @param folderPath Path of folder that contains the content
     * @param name Name of content
     * @return Content object
     */
    @WebMethod
    Content getContent(String repositoryName, String folderPath, String name);
    
    /**
     * Get folder information based on repository name and path of folder
     * 
     * @param repositoryName Repository name
     * @param path Path of folder
     * @return Folder object
     */
    @WebMethod
    Folder getFolder(String repositoryName, String path);
    
    /**
     * @param sessionId Session that option object is tied to
     * @return Options object
     */
    ListingOptions getOptions(String sessionId);
    
    /**
     * @return List of available repositories
     */
    @WebMethod
    List<ContentRepository> getRepositories();
    
    /**
     * List available contents under given path of folder and given repository
     * 
     * @param repositoryName Name of repository
     * @param path Path of folder to list
     * @param sessionId Current session ID
     * @return List of content
     */
    @WebMethod
    List<Content> listContents(String repositoryName, String path, String sessionId);
    
    /**
     * List available folders under given path of folder and given repository
     * 
     * @param repositoryName Name of repository
     * @param path Path of folder to list
     * @param sessionId Current session ID
     * @return List of folders
     */
    @WebMethod
    List<Folder> listFolders(String repositoryName, String path, String sessionId);
    
    /**
     * Register a repository dynamically
     * 
     * @param name Name of repository to register
     * @param uri URI of repository
     * @return Repository it registers
     */
    @WebMethod
    ContentRepository registerRepository( String name, String uri );
    
    /**
     * Save changes made on options
     * 
     * @param sessionId Session ID where the options are stored to
     * @param options Option object
     */
    @WebMethod
    void setOptions(String sessionId, ListingOptions options);
}
