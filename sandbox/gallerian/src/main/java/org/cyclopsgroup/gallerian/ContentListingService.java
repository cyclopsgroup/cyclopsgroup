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
    @WebMethod
    Content getContent(String repositoryName, String path);
    
    @WebMethod
    Content getContent(String repositoryName, String folderPath, String name);
    
    @WebMethod
    Folder getFolder(String repositoryName, String path);
    
    ListingOptions getOptions(String sessionId);
    
    @WebMethod
    List<ContentRepository> getRepositories();
    
    @WebMethod
    List<Content> listContents(String repositoryName, String path, String sessionId);
    
    @WebMethod
    List<Folder> listFolders(String repositoryName, String path, String sessionId);
    
    void setOptions(String sessionId, ListingOptions options);
}
