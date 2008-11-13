package com.cyclopsgroup.waterview.impl;

import org.apache.commons.fileupload.FileUpload;

import com.cyclopsgroup.waterview.spi.I18NService;
import com.cyclopsgroup.waterview.spi.Waterview;

public abstract class AbstractWaterview
    implements Waterview
{
    private FileUpload fileUpload;

    private I18NService i18nService;

    /**
     * @see com.cyclopsgroup.waterview.spi.Waterview#getFileUpload()
     */
    public FileUpload getFileUpload()
    {
        return fileUpload;
    }

    public I18NService getI18NService()
    {
        return i18nService;
    }

    public void setFileUpload( FileUpload fileUpload )
    {
        this.fileUpload = fileUpload;
    }

    public void setI18NService( I18NService service )
    {
        i18nService = service;
    }
}
