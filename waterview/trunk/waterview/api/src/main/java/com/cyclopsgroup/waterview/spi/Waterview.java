package com.cyclopsgroup.waterview.spi;

import org.apache.commons.fileupload.FileUpload;

import com.cyclopsgroup.waterview.RunData;

public interface Waterview
{
    FileUpload getFileUpload();

    I18NService getI18NService();

    void processRunData( RunData data )
        throws Exception;
}
