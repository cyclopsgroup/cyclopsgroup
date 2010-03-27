package org.cyclopsgroup.spee.spi.storage;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.cyclopsgroup.spee.Notification;

public interface StorageManager
{
    void addNotification( String executionId, Notification notification, long timeToLive, TimeUnit unit );

    StoredExecution findExecution( String executionId );

    void setWaitingFor( String executionId, List<Serializable> conditions, long timeout, TimeUnit unit );
}
