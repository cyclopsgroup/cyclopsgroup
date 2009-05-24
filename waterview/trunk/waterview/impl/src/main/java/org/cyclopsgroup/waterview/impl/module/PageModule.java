package org.cyclopsgroup.waterview.impl.module;

import java.util.List;

/**
 * A module richer than {@link WebModule}. On top of information related to a resource, this module provides extra
 * information specific to a given request path.
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public interface PageModule
    extends WebModule
{
    /**
     * @return MIME content type
     */
    String getContentType();

    /**
     * @return Description of page longer than title
     */
    String getDescription();

    /**
     * @return List of keywords
     */
    List<String> getKeywords();

    /**
     * @return Path of layout if it's defined
     */
    String getLayout();
    
    /**
     * @return Tile of page
     */
    String getTitle();
    
    /**
     * @return True if it takes care of entire rendering work
     */
    boolean isRaw();
}
