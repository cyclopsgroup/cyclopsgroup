package com.cyclopsgroup.waterview;

import java.io.IOException;
import java.util.Map;

public interface Waterview
{
    void processRunData( RunData data )
        throws ExecutionException, IOException;

    Map<String, String> getPackageMap();
}
