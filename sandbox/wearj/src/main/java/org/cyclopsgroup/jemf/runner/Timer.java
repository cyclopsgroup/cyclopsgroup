package org.cyclopsgroup.jemf.runner;

/**
 * Timer to time execution
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public interface Timer
{
    /**
     * Start timing
     */
    void begin();

    /**
     * End timing and return result
     * 
     * @return Result value
     */
    long end();
}
