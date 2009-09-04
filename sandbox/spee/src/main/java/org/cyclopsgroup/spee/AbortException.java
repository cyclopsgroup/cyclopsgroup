package org.cyclopsgroup.spee;

public class AbortException extends RuntimeException
{
    private final Object notification;
    
    public AbortException(Object notification, String description)
    {
        super("Flow is aborted: " + description + "! notification=" + notification);
        this.notification = notification;
    }
    
    public final Object getNotification()
    {
        return notification;
    }
}
