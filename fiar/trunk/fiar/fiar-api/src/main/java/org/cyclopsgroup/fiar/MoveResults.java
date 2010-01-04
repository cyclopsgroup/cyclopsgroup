package org.cyclopsgroup.fiar;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement( name = "MoveResults" )
public class MoveResults
{
    private String gameId;

    private int previousVersion;

    private int version;

    private List<MoveResult> results;
}
