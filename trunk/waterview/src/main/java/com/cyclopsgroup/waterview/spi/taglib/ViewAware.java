package com.cyclopsgroup.waterview.spi.taglib;

import com.cyclopsgroup.waterview.spi.View;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Tags that worry view
 */
public interface ViewAware
{
    /**
     * Handle view
     * @param view View object
     */
    void doView( View view );
}
