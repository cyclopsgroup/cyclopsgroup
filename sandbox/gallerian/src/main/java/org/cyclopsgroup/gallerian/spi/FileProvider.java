package org.cyclopsgroup.gallerian.spi;

/**
 * Interface that describes a file
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public interface FileProvider
{
    /**
     * @return Path of file starting with slash
     */
    String getPath();

    /**
     * @return Name of file
     */
    String getName();

    /**
     * @return Timestamp of last update
     */
    long getLastUpdate();

    /**
     * @return Number of bytes in file
     */
    long getSize();
    
    /**
     * @return Description of content
     */
    String getDescription();
}
