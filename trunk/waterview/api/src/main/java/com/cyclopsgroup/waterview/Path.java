package com.cyclopsgroup.waterview;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Path model
 */
public interface Path
{
    /**
     * Get extension of this path
     *
     * @return Extension of empty string if there's no extension
     */
    String getExtension();

    /**
     * Get full path expression
     *
     * @return Full path
     */
    String getFullPath();

    /**
     * @return Package of path
     */
    String getPackage();

    /**
     * @return Package alias
     */
    String getPackageAlias();

    /**
     * @return Path
     */
    String getPath();

    /**
     * Relative path without extension
     *
     * @return Relative path without extension
     */
    String getPathWithoutExtension();
}