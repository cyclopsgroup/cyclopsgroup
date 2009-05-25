package org.cyclopsgroup.gallerian.serv;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.servlet.ServletContext;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.cyclopsgroup.gallerian.Content;
import org.cyclopsgroup.gallerian.ContentId;
import org.cyclopsgroup.gallerian.ContentListingService;
import org.cyclopsgroup.gallerian.ContentRepository;
import org.cyclopsgroup.gallerian.Folder;
import org.cyclopsgroup.gallerian.ListingOptions;
import org.cyclopsgroup.gallerian.spi.FileProvider;
import org.cyclopsgroup.gallerian.spi.FolderProvider;
import org.cyclopsgroup.gallerian.spi.RepositoryProvider;
import org.cyclopsgroup.gallerian.spi.RepositoryProviderFactory;
import org.springframework.web.context.ServletContextAware;

/**
 * Default implementation of {@link ContentListingService} that takes advantage of {@link RepositoryProviderFactory}
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class ServerSideContentListingService
    implements ContentListingService, ServerSideContentListingServiceMBean, ServletContextAware
{
    private static final ContentComparator CONTENT_COMPARATOR = new ContentComparator();

    private static final long DEFAULT_PREVIEW_SIZE_THRESHOLD = 1048578L * 2;

    private static final FolderComparator FOLDER_COMPARATOR = new FolderComparator();

    private long previewSizeThreshold = DEFAULT_PREVIEW_SIZE_THRESHOLD;
    
    private final ConcurrentMap<String, RepositoryProvider> providers =
        new ConcurrentHashMap<String, RepositoryProvider>();

    private ServletContext servletContext;

    private RepositoryProvider doRegisterRepository( String name, String uri )
    {
        Validate.notNull( name, "Name can't be NULL" );
        RepositoryProviderFactory providerFactory = null;
        for ( Iterator<RepositoryProviderFactory> i = ServiceLoader.load( RepositoryProviderFactory.class ).iterator(); i.hasNext(); )
        {
            RepositoryProviderFactory f = i.next();
            if ( f.accept( uri ) )
            {
                providerFactory = f;
                break;
            }
        }
        if ( providerFactory == null )
        {
            throw new IllegalArgumentException( "URI " + uri + " isn't accepted by any provider factory" );
        }

        RepositoryProvider provider = providerFactory.createProvider( uri );
        if ( providers.putIfAbsent( name, provider ) != null )
        {
            throw new IllegalArgumentException( "Name " + name + " already exists" );
        }
        return provider;
    }
    
    /**
     * @inheritDoc
     */
    @Override
    public Content getContent( String repositoryName, String path )
    {
        FileProvider file = getFileProvider( repositoryName, path );
        return newContent( file, repositoryName );
    }

    /**
     * @inheritDoc
     */
    @Override
    public Content getContent( String repositoryName, String folderPath, String name )
    {
        return getContent( repositoryName, folderPath + "/" + name );
    }

    /**
     * @param repositoryName Name of repository
     * @param path Full path of file starting with slash
     * @return File provider instance
     */
    FileProvider getFileProvider( String repositoryName, String path )
    {
        Validate.notNull( path, "Path can't be NULL" );
        Validate.notNull( repositoryName, "Must specify a repository" );
        RepositoryProvider repo = getRespositoryProvider( repositoryName );
        FileProvider file = repo.findFile( path );
        if ( file == null )
        {
            throw new IllegalArgumentException( "No such file " + path + " in repository " + repositoryName );
        }
        return file;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Folder getFolder( String repositoryName, String path )
    {
        return newFolder( getFolderProvider( repositoryName, path ) );
    }

    private FolderProvider getFolderProvider( String repositoryName, String path )
    {
        Validate.notNull( path, "Path can't be NULL" );
        Validate.notNull( repositoryName, "Must specify a repository" );
        RepositoryProvider repo = getRespositoryProvider( repositoryName );
        if ( path.equals( "/" ) )
        {
            return repo.getRootFolder();
        }
        FolderProvider folder = repo.findFolder( path );
        if ( folder == null )
        {
            throw new IllegalArgumentException( "No such folder " + path + " in repository " + repositoryName );
        }
        return folder;
    }

    /**
     * @inheritDoc
     */
    @Override
    public ListingOptions getOptions( String sessionId )
    {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @return Value of field previewSizeThreshold
     */
    public final long getPreviewSizeThreshold()
    {
        return previewSizeThreshold;
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<ContentRepository> getRepositories()
    {
        List<ContentRepository> repositories = new ArrayList<ContentRepository>( providers.size() );
        for ( Map.Entry<String, RepositoryProvider> entry : providers.entrySet() )
        {
            repositories.add( newContentRepository( entry.getValue(), entry.getKey() ) );
        }
        return repositories;
    }

    private RepositoryProvider getRespositoryProvider( String name )
    {
        Validate.notNull( name, "Repository name can't be NULL" );
        RepositoryProvider provider = providers.get( name );
        if ( provider == null )
        {
            throw new IllegalArgumentException( "Repository name " + name + " doesn't exist" );
        }
        return provider;
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<Content> listContents( String repositoryName, String path, String sessionId )
    {
        FolderProvider parent = getFolderProvider( repositoryName, path );
        List<FileProvider> files = parent.getRepository().listFiles( parent );
        List<Content> result = new ArrayList<Content>( files.size() );
        for ( FileProvider file : files )
        {
            if ( file.getName().charAt( 0 ) != '.' )
            {
                result.add( newContent( file, repositoryName ) );
            }
        }
        Collections.sort( result, CONTENT_COMPARATOR );
        return result;
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<Folder> listFolders( String repositoryName, String path, String sessionId )
    {
        FolderProvider parent = getFolderProvider( repositoryName, path );
        if ( parent == null )
        {
            throw new IllegalArgumentException( "No such folder " + path + " in repository " + repositoryName );
        }
        List<FolderProvider> folders = parent.getRepository().listFolders( parent );
        List<Folder> result = new ArrayList<Folder>( folders.size() );
        for ( FolderProvider folder : folders )
        {
            if ( folder.getName().charAt( 0 ) != '.' )
            {
                result.add( newFolder( folder ) );
            }
        }
        Collections.sort( result, FOLDER_COMPARATOR );
        return result;
    }

    private Content newContent( FileProvider file, String repositoryName )
    {
        Content content = new Content();
        content.setName( file.getName() );
        content.setLastUpdated( file.getLastUpdate() );
        content.setSize( file.getSize() );
        content.setDescription( file.getDescription() );

        ContentId id = new ContentId();
        id.setPath( file.getPath() );
        id.setRepositoryName( repositoryName );
        content.setId( id );
        
        String mimeType = servletContext.getMimeType( file.getPath() );
        if(StringUtils.isEmpty( mimeType ))
        {
            mimeType = "unknown";
        }
        content.setMimeType( mimeType );
        content.setPreviewable( content.getSize() < previewSizeThreshold && mimeType.startsWith( "image/" ) );
        return content;
    }

    private ContentRepository newContentRepository( RepositoryProvider provider, String name )
    {
        ContentRepository repo = new ContentRepository();
        repo.setName( name );
        repo.setUri( provider.getUri() );
        repo.setDescription( provider.getUri() );
        return repo;
    }

    private Folder newFolder( FolderProvider folder )
    {
        Folder f = new Folder();
        f.setName( folder.getName() );
        f.setPath( folder.getPath() );
        return f;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void register( String name, String uri )
    {
        doRegisterRepository( name, uri );
    }

    /**
     * @inheritDoc
     */
    @Override
    public ContentRepository registerRepository( String name, String uri )
    {
        return newContentRepository( doRegisterRepository( name, uri ), name );
    }

    /**
     * @param uris Map of repositories to register
     */
    public void setInitialRepositories( Map<String, String> uris )
    {
        for ( Map.Entry<String, String> entry : uris.entrySet() )
        {
            registerRepository( entry.getKey(), entry.getValue() );
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setOptions( String sessionId, ListingOptions options )
    {
        // TODO Auto-generated method stub

    }

    /**
     * @param previewSizeThreshold Value of field previewSizeThreshold to set
     */
    public final void setPreviewSizeThreshold( long previewSizeThreshold )
    {
        this.previewSizeThreshold = previewSizeThreshold;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setServletContext( ServletContext context )
    {
        this.servletContext = context;
    }

}
