package org.cyclopsgroup.gallerian.serv;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.commons.lang.Validate;
import org.cyclopsgroup.gallerian.Content;
import org.cyclopsgroup.gallerian.ContentListingService;
import org.cyclopsgroup.gallerian.ContentRepository;
import org.cyclopsgroup.gallerian.Folder;
import org.cyclopsgroup.gallerian.ListingOptions;
import org.cyclopsgroup.gallerian.spi.RepositoryProvider;
import org.cyclopsgroup.gallerian.spi.RepositoryProviderFactory;

/**
 * Default implementation of {@link ContentListingService} that takes advantage of {@link RepositoryProviderFactory}
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class ServerSideContentListingService
    implements ContentListingService
{
    private final ConcurrentMap<String, RepositoryProvider> providers =
        new ConcurrentHashMap<String, RepositoryProvider>();

    private RepositoryProvider getProvider( String name )
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
    public Content getContent( String repositoryName, String path )
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Content getContent( String repositoryName, String folderPath, String name )
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Folder getFolder( String repositoryName, String path )
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ListingOptions getOptions( String sessionId )
    {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<ContentRepository> getRepositories()
    {
        List<ContentRepository> repositories = new ArrayList<ContentRepository>(providers.size());
        for(Map.Entry<String, RepositoryProvider> entry: providers.entrySet())
        {
            repositories.add( newContentRepository( entry.getValue(), entry.getKey() ) );
        }
        return repositories;
    }

    @Override
    public List<Content> listContents( String repositoryName, String path, String sessionId )
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Folder> listFolders( String repositoryName, String path, String sessionId )
    {
        // TODO Auto-generated method stub
        return null;
    }

    private ContentRepository newContentRepository( RepositoryProvider provider, String name )
    {
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public ContentRepository registerRepository( String name, String uri )
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
        return newContentRepository( provider, name );
    }

    @Override
    public void setOptions( String sessionId, ListingOptions options )
    {
        // TODO Auto-generated method stub

    }

}
