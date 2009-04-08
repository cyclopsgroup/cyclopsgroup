package org.cyclopsgroup.waterview.impl.module;

import java.util.List;

/**
 * Module for a page
 *
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public interface PageModule
    extends WebModule
{
    /**
     * @return Tile of page
     */
    String getTitle();
    
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
}
