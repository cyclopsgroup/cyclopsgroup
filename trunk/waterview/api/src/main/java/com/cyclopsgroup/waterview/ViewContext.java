package com.cyclopsgroup.waterview;

/**
 *
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public interface ViewContext
    extends Context
{
    String getPackage();

    String getPath();

    /**
     * Get unique ID for this view in current page
     *
     * @return A unique string to identify this view
     */
    String getViewId();

    void setPackage( String packageAlias );

    void setPath( String path );
}
