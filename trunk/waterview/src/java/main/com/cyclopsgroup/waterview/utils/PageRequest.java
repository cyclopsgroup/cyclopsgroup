package com.cyclopsgroup.waterview.utils;

import java.util.Hashtable;

import org.apache.commons.lang.StringUtils;

/**
 * Page request element
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public final class PageRequest
{
    private static Hashtable pageRequestCache = new Hashtable();

    /**
     * Parse given string into PageRequest object
     *
     * @param fullPath Path of request page
     * @return PageRequest object
     */
    public static synchronized PageRequest parsePageRequest(String fullPath)
    {
        if (pageRequestCache.containsKey(fullPath))
        {
            return (PageRequest) pageRequestCache.get(fullPath);
        }
        int lastSeparatorPosition = fullPath.lastIndexOf('/');
        int dotPosition = fullPath.lastIndexOf('.');
        if (dotPosition < lastSeparatorPosition)
        {
            dotPosition = -1;
        }
        String extension = StringUtils.EMPTY;
        if (dotPosition != -1)
        {
            extension = fullPath.substring(dotPosition + 1);
        }
        else
        {
            dotPosition = dotPosition == -1 ? fullPath.length() : fullPath
                    .length() - 1;
        }
        String shortName = fullPath.substring(lastSeparatorPosition + 1,
                dotPosition);
        String parent = fullPath.substring(0, lastSeparatorPosition + 1);
        while (parent.length() > 0 && parent.charAt(0) == '/')
        {
            parent = parent.substring(1, parent.length());
        }
        PageRequest result = new PageRequest(parent, shortName, extension);
        pageRequestCache.put(fullPath, result);
        return result;
    }

    private String extension;

    private String fileName;

    private String parentPath;

    private String shortName;

    /**
     * Constructor for class PageRequest
     *
     * @param extension File extension
     * @param parentPath Parent path
     * @param shortName Short name of module
     */
    public PageRequest(String parentPath, String shortName, String extension)
    {
        this.extension = extension;
        this.parentPath = parentPath;
        this.shortName = shortName;
        this.fileName = shortName + '.' + extension;
    }

    /**
     * Getter method for extension
     *
     * @return Returns the extension.
     */
    public String getExtension()
    {
        return extension;
    }

    /**
     * Getter method for fileName
     *
     * @return Returns the fileName.
     */
    public String getFileName()
    {
        return fileName;
    }

    /**
     * Getter method for parentPath
     *
     * @return Returns the parentPath.
     */
    public String getParentPath()
    {
        return parentPath;
    }

    /**
     * Getter method for shortName
     *
     * @return Returns the shortName.
     */
    public String getShortName()
    {
        return shortName;
    }
}