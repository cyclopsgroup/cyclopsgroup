package org.cyclopsgroup.eulerer.p205;

public class Chance
{
    private int less;

    private int more;

    private int value;

    public final int getLess()
    {
        return less;
    }

    public final int getMore()
    {
        return more;
    }

    public final int getValue()
    {
        return value;
    }

    void increase()
    {
        value++;
    }

    void setLessAndMore( int less, int more )
    {
        this.less = less;
        this.more = more;
    }

    public String toString()
    {
        return value + "(" + less + "," + more + ")";
    }
}
