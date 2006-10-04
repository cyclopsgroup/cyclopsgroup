package com.cyclopsgroup.waterview;

/**
 *
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public interface ViewContext
{
    /**
     * Get run data context
     *
     * @return Run data
     */
    RunData getRunData();

    Context getContext();

    /**
     * Get unique ID for this view in current page
     *
     * @return A unique string to identify this view
     */
    String getViewId();
}
