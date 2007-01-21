package com.cyclopsgroup.waterview.spi;

import org.apache.commons.fileupload.FileUpload;

import com.cyclopsgroup.waterview.RunData;

public interface Waterview
{
    String ROLE = Waterview.class.getName();

    FileUpload getFileUpload();

    void processRunData( RunData data )
        throws Exception;
}
