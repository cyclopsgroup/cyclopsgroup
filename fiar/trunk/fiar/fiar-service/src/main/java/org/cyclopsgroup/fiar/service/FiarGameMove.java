package org.cyclopsgroup.fiar.service;

import java.io.Serializable;

import org.joda.time.DateTime;

public class FiarGameMove
    implements Serializable
{
    private DateTime moveDate;

    private FiarGamePlayer player;

    private int x;

    private int y;

    private int version;
}
