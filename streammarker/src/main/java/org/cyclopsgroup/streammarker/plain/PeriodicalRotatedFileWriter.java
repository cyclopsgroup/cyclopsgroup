package org.cyclopsgroup.streammarker.plain;

import java.io.File;
import java.util.concurrent.ScheduledExecutorService;

public class PeriodicalRotatedFileWriter
    extends AbstractRotatedFileWriter
{
    public PeriodicalRotatedFileWriter( Application application, ScheduledExecutorService scheduler,
                                        long intervalMillis, long maxIdleMillis, File directory )
    {
        super( application, scheduler, intervalMillis, maxIdleMillis, directory );
    }

    @Override
    protected String calculateFileName()
    {
        // TODO Auto-generated method stub
        return null;
    }

}
