package org.cyclopsgroup.fiar;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement( name = "MoveResults" )
public class Moves
{
    private String gameId;

    private int previousVersion;

    private List<Move> results;

    private int version;

    @XmlElement
    public final String getGameId()
    {
        return gameId;
    }

    @XmlElement
    public final int getPreviousVersion()
    {
        return previousVersion;
    }

    @XmlElement( name = "move" )
    public final List<Move> getResults()
    {
        return results;
    }

    @XmlElement
    public final int getVersion()
    {
        return version;
    }

    public final void setGameId( String gameId )
    {
        this.gameId = gameId;
    }

    public final void setPreviousVersion( int previousVersion )
    {
        this.previousVersion = previousVersion;
    }

    public final void setResults( List<Move> results )
    {
        this.results = results;
    }

    public final void setVersion( int version )
    {
        this.version = version;
    }
}
