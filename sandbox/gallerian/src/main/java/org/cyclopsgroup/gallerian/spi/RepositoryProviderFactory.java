package org.cyclopsgroup.gallerian.spi;

/**
 * Factory of {@link RepositoryProvider}
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public interface RepositoryProviderFactory
{
    /**
     * Lightweight operation to tell if uri is acceptable to this implementation of repository provider factory
     * 
     * @param uri URI to check
     * @return True if this URI is acceptable
     */
    boolean accept(String uri);
    
    /**
     * @param uri URI that describes repository
     * @return Respository provider instance which never is NULL
     */
    RepositoryProvider createProvider( String uri );
}
