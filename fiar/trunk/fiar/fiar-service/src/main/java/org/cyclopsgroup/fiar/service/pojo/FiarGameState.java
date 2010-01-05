package org.cyclopsgroup.fiar.service.pojo;

/**
 * Possible state of game
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
public enum FiarGameState
{
    /**
     * When game is created, it's put to pending until the second player joins
     */
    PENDING,
    /**
     * Game is being actively played
     */
    ONGOING,
    /**
     * Game has been inactive
     */
    EXPIRED,
    /**
     * Game is over
     */
    FINISHED;
}
