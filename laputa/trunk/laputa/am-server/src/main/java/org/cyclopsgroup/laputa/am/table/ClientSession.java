package org.cyclopsgroup.laputa.am.table;

import org.joda.time.DateTime;

/**
 * Login session
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class ClientSession
{
    private boolean acitve;

    private boolean authenticated;

    private String ipAddress;

    private DateTime lastActiveDate;

    private DateTime lastAuthenticationDate;

    private String sessionId;

    private DateTime startDate;
}
