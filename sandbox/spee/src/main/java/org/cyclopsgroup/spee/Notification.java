package org.cyclopsgroup.spee;

import java.io.Serializable;

public interface Notification
    extends Serializable
{
    boolean isFor( Object condition );
}
