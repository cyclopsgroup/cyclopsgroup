package org.cyclopsgroup.cym2.awss3;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.maven.wagon.ConnectionException;
import org.apache.maven.wagon.ResourceDoesNotExistException;
import org.apache.maven.wagon.TransferFailedException;
import org.apache.maven.wagon.Wagon;
import org.apache.maven.wagon.authentication.AuthenticationException;
import org.apache.maven.wagon.authentication.AuthenticationInfo;
import org.apache.maven.wagon.authorization.AuthorizationException;
import org.apache.maven.wagon.events.SessionListener;
import org.apache.maven.wagon.events.TransferListener;
import org.apache.maven.wagon.proxy.ProxyInfo;
import org.apache.maven.wagon.proxy.ProxyInfoProvider;
import org.apache.maven.wagon.repository.Repository;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3ObjectSummary;

/**
 * Amazon S3 wagon provider implementation
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
public class S3Wagon
    implements Wagon
{
    private static final Log LOG = LogFactory.getLog( S3Wagon.class );

    private AmazonS3 s3;

    private Repository repository;

    private int timeout;

    private final Set<SessionListener> sessionListeners = new HashSet<SessionListener>();

    private final Set<TransferListener> transferListeners = new HashSet<TransferListener>();

    private boolean interactive;

    private String keyPrefix;

    private String bucketName;

    /**
     * @inheritDoc
     */
    public void get( String resourceName, File destination )
        throws ResourceDoesNotExistException, TransferFailedException
    {
        s3.getObject( new GetObjectRequest( bucketName, keyPrefix + resourceName ), destination );
    }

    /**
     * @inheritDoc
     */
    public boolean getIfNewer( String resourceName, File destination, long timestamp )
        throws ResourceDoesNotExistException, TransferFailedException
    {
        String key = keyPrefix + resourceName;
        ObjectMetadata meta;
        try
        {
            meta = s3.getObjectMetadata( bucketName, key );
        }
        catch ( AmazonServiceException e )
        {
            if ( e.getStatusCode() == 404 )
            {
                throw new ResourceDoesNotExistException( "Key " + key + " does not exist in bucket " + bucketName, e );
            }
            throw new TransferFailedException( "Getting metadata of key " + key + "failed", e );
        }
        if ( meta.getLastModified().getTime() <= timestamp )
        {
            return false;
        }
        s3.getObject( new GetObjectRequest( bucketName, key ), destination );
        return true;
    }

    /**
     * @inheritDoc
     */
    public void put( File source, String destination )
        throws TransferFailedException, ResourceDoesNotExistException, AuthorizationException
    {
        String key = keyPrefix + destination;
        ObjectMetadata meta = new ObjectMetadata();
        meta.setContentLength( source.length() );
        meta.setLastModified( new Date( source.lastModified() ) );
        try
        {
            FileInputStream in = new FileInputStream( source );
            try
            {
                System.out.println( "Putting key " + key + " to " + bucketName );
                s3.putObject( bucketName, key, in, meta );
                s3.setObjectAcl( bucketName, key, CannedAccessControlList.PublicRead );
            }
            finally
            {
                IOUtils.closeQuietly( in );
            }
        }
        catch ( IOException e )
        {
            throw new TransferFailedException( "Can't transfer file " + source + " to key " + key, e );
        }
        catch ( AmazonServiceException e )
        {
            throw new TransferFailedException( "AWS call failed", e );
        }
    }

    /**
     * @inheritDoc
     */
    public void putDirectory( File sourceDirectory, String destinationDirectory )
        throws TransferFailedException, ResourceDoesNotExistException, AuthorizationException
    {
    }

    /**
     * @inheritDoc
     */
    public boolean resourceExists( String resourceName )
        throws TransferFailedException, AuthorizationException
    {
        String key = keyPrefix + resourceName;
        try
        {
            s3.getObjectMetadata( bucketName, key );
            return true;
        }
        catch ( AmazonServiceException e )
        {
            if ( e.getStatusCode() == 404 )
            {
                return false;
            }
            throw new TransferFailedException( "Can't verify if resource key " + key + " exist or not", e );
        }
    }

    /**
     * @inheritDoc
     */
    @SuppressWarnings( "rawtypes" )
    public List getFileList( String destinationDirectory )
        throws TransferFailedException, ResourceDoesNotExistException
    {
        String path = keyPrefix + destinationDirectory;
        if ( !path.endsWith( "/" ) )
        {
            path += "/";
        }
        ObjectListing result =
            s3.listObjects( new ListObjectsRequest().withBucketName( bucketName ).withPrefix( path ).withDelimiter( "/" ) );
        if ( result.getObjectSummaries().isEmpty() )
        {
            throw new ResourceDoesNotExistException( "No keys exist with prefix " + path );
        }
        Set<String> results = new HashSet<String>();
        for ( S3ObjectSummary summary : result.getObjectSummaries() )
        {
            String name = StringUtils.removeStart( summary.getKey(), path );
            if ( name.indexOf( '/' ) == -1 )
            {
                results.add( name );
            }
            else
            {
                results.add( name.substring( 0, name.indexOf( '/' ) ) );
            }
        }
        return new ArrayList<String>( results );
    }

    /**
     * @inheritDoc
     */
    public boolean supportsDirectoryCopy()
    {
        return true;
    }

    /**
     * @inheritDoc
     */
    public Repository getRepository()
    {
        return repository;
    }

    /**
     * @inheritDoc
     */
    public void connect( Repository source )
        throws ConnectionException, AuthenticationException
    {
        throw new UnsupportedOperationException( "Authentication must be provided" );
    }

    /**
     * @inheritDoc
     */
    public void connect( Repository source, ProxyInfo proxyInfo )
        throws ConnectionException, AuthenticationException
    {
        throw new UnsupportedOperationException( "Authentication must be provided" );
    }

    /**
     * @inheritDoc
     */
    public void connect( Repository source, ProxyInfoProvider proxyInfoProvider )
        throws ConnectionException, AuthenticationException
    {
        throw new UnsupportedOperationException( "Authentication must be provided" );
    }

    /**
     * @inheritDoc
     */
    public void connect( Repository source, AuthenticationInfo authenticationInfo )
        throws ConnectionException, AuthenticationException
    {
        connect( source, authenticationInfo, (ProxyInfo) null );
    }

    /**
     * @inheritDoc
     */
    public void connect( Repository source, AuthenticationInfo authenticationInfo, ProxyInfo proxyInfo )
        throws ConnectionException, AuthenticationException
    {
        System.out.println( "Setting up AWS S3 client with source " + ToStringBuilder.reflectionToString( source )
            + " authentication information " + ToStringBuilder.reflectionToString( authenticationInfo ) + " and proxy "
            + ToStringBuilder.reflectionToString( proxyInfo ) );
        AWSCredentials credentials =
            new BasicAWSCredentials( authenticationInfo.getUserName(), authenticationInfo.getPassword() );
        ClientConfiguration config = new ClientConfiguration();

        config.setConnectionTimeout( timeout );
        config.setSocketTimeout( timeout );
        LOG.info( "Connect timeout and socket timeout is both set to " + timeout + "ms" );

        if ( proxyInfo != null )
        {
            config.setProxyDomain( proxyInfo.getNtlmDomain() );
            config.setProxyHost( proxyInfo.getHost() );
            config.setProxyPassword( proxyInfo.getPassword() );
            config.setProxyPort( proxyInfo.getPort() );
            config.setProxyUsername( proxyInfo.getUserName() );
            config.setProxyWorkstation( proxyInfo.getNtlmHost() );
        }
        System.out.println( "Client config is " + ToStringBuilder.reflectionToString( config ) );

        s3 = new AmazonS3Client( credentials, config );
        String prefix = StringUtils.trimToEmpty( source.getBasedir() );
        StringUtils.removeStart( prefix, "/" );
        if ( StringUtils.isNotEmpty( prefix ) && !prefix.endsWith( "/" ) )
        {
            prefix = prefix + "/";
        }
        keyPrefix = prefix;
        System.out.println( "Key prefix " + keyPrefix );
        repository = source;
    }

    public void connect( Repository source, AuthenticationInfo authenticationInfo, ProxyInfoProvider proxyInfoProvider )
        throws ConnectionException, AuthenticationException
    {
        connect( source, authenticationInfo, proxyInfoProvider.getProxyInfo( "s3" ) );
    }

    public void openConnection()
    {
    }

    public void disconnect()
    {
    }

    /**
     * @inheritDoc
     */
    public void setTimeout( int timeoutValue )
    {
        this.timeout = timeoutValue;
    }

    /**
     * @inheritDoc
     */
    public int getTimeout()
    {
        return timeout;
    }

    /**
     * @inheritDoc
     */
    public void addSessionListener( SessionListener listener )
    {
        sessionListeners.add( listener );
    }

    /**
     * @inheritDoc
     */
    public void removeSessionListener( SessionListener listener )
    {
        sessionListeners.remove( listener );
    }

    /**
     * @inheritDoc
     */
    public boolean hasSessionListener( SessionListener listener )
    {
        return sessionListeners.contains( listener );
    }

    /**
     * @inheritDoc
     */
    public void addTransferListener( TransferListener listener )
    {
        transferListeners.add( listener );
    }

    /**
     * @inheritDoc
     */
    public void removeTransferListener( TransferListener listener )
    {
        transferListeners.remove( listener );
    }

    /**
     * @inheritDoc
     */
    public boolean hasTransferListener( TransferListener listener )
    {
        return transferListeners.contains( listener );
    }

    /**
     * @inheritDoc
     */
    public boolean isInteractive()
    {
        return interactive;
    }

    /**
     * @inheritDoc
     */
    public void setInteractive( boolean interactive )
    {
        this.interactive = interactive;
    }
}
