package org.cyclopsgroup.eulerer.p205;

public class Chance
{
    private long less;

    private long more;

    private int value;

    Chance( long total )
    {
        more = total;
    }

    public final long less()
    {
        return less;
    }

    public final long more()
    {
        return more;
    }

    public final int value()
    {
        return value;
    }

    void increaseValue()
    {
        value++;
    }

    void adjust( long less, long more )
    {
        this.less = less;
        this.more = more;
    }

    public String toString()
    {
        return value + "(" + less + "," + more + ")";
    }
}
