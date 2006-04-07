package com.cyclopsgroup.waterview.spi;

import com.cyclopsgroup.waterview.RunData;

public interface RunDataSpi
    extends RunData
{
    Page getPageObject();

    void setPageObject(Page page);
}
