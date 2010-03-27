package org.cyclopsgroup.spee.example;

import org.cyclopsgroup.spee.Notification;

public class ReviewResult
    implements Notification
{
    private static final long serialVersionUID = 1L;

    public ReviewResult( String reviewer, boolean approved )
    {
        this.reviewer = reviewer;
        this.approved = approved;
    }

    final String reviewer;

    final boolean approved;

    @Override
    public boolean isFor( Object condition )
    {
        if ( condition.getClass() != String.class )
        {
            return false;
        }
        String c = (String) condition;
        return c.startsWith( "ReviewedBy" );
    }
}
