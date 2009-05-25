package org.cyclopsgroup.gallerian;

import java.io.IOException;
import java.io.InputStream;

/**
 * Service for getting stream of content
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public interface ContentStreamService
{
    /**
     * Open stream of content
     * 
     * @param id Id of content to open
     * @return Input stream of content
     * @throws IOException If network or filesystem fails
     */
    InputStream openContent( ContentId id )
        throws IOException;

    /**
     * Open stream of icon
     * 
     * @param id Id of content
     * @return Input stream of icon
     * @throws IOException If network or filesystem fails
     */
    InputStream openIcon( ContentId id )
        throws IOException;

    /**
     * @param id Content ID
     * @return MIME type of icon
     */
    String getIconMimeType( ContentId id );

}
