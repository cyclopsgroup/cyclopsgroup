package org.cyclopsgroup.eulerer.p205;

/**
 * Chance of a value, chance of being less than a value and chance of being greater than the value
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
public class Chance
{
    private long greater;

    private long less;

    private int value;

    /**
     * @return Chance of being greater than the value
     */
    public final long greater()
    {
        return greater;
    }

    /**
     * Increase the chance of being equal to the value
     */
    void increaseValue()
    {
        value++;
    }

    /**
     * @return Chance of being less than the value
     */
    public final long less()
    {
        return less;
    }

    /**
     * Position the chance so that chance of being less or more is accurate
     *
     * @param less Chance of being less than value
     * @param greater Chance of being more than value
     */
    void position( long less, long greater )
    {
        this.less = less;
        this.greater = greater;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString()
    {
        return value + "(" + less + "," + greater + ")";
    }

    /**
     * @return Chance of being equal to the value
     */
    public final int value()
    {
        return value;
    }
}
