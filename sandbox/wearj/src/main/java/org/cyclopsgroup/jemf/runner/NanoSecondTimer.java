package org.cyclopsgroup.jemf.runner;

/**
 * Timer in nanoSecond
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class NanoSecondTimer implements Timer
{
    private long start = 0;

    /**
     * @inheritDoc
     */
    @Override
    public void begin()
    {
        start = System.nanoTime();
    }

    /**
     * @inheritDoc
     */
    @Override
    public long end()
    {
        long result = System.nanoTime() - start;
        if ( start == 0 )
        {
            throw new IllegalStateException( "It hasn't been started yet" );
        }
        start = 0;
        return result;
    }
}
