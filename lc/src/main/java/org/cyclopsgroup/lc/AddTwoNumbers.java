package org.cyclopsgroup.lc;

public class AddTwoNumbers
{
    static class ListNode
    {
        private int val;

        private ListNode next;

        private ListNode( int x )
        {
            val = x;
            next = null;
        }
    }

    public ListNode addTwoNumbers( ListNode l1, ListNode l2 )
    {
        return add( l1, l2, 0 );
    }

    private ListNode add( ListNode l1, ListNode l2, int addition )
    {
        if ( l1 == null && l2 == null && addition == 0 )
        {
            return null;
        }
        int val = addition;
        if ( l1 != null )
        {
            val += l1.val;
        }
        if ( l2 != null )
        {
            val += l2.val;
        }
        ListNode n = new ListNode( val % 10 );
        n.next = add( l1 == null ? null : l1.next, l2 == null ? null : l2.next, val / 10 );
        return n;
    }
}
