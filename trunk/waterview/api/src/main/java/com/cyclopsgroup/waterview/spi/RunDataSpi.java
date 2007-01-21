package com.cyclopsgroup.waterview.spi;

import java.util.List;

import com.cyclopsgroup.waterview.RunData;

/**
 * Implementation side RunData interface
 *
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public interface RunDataSpi
    extends RunData
{
    interface Request
    {
        String getPackageAlias();

        String getRequestPath();
    }

    List<Request> getRequests();

    Waterview getWaterview();
}
