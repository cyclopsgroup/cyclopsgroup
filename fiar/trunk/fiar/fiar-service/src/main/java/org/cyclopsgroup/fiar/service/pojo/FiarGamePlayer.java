package org.cyclopsgroup.fiar.service.pojo;

public enum FiarGamePlayer
{
    DEFENSE
    {
        @Override
        public String getUserIdOf( FiarGame game )
        {
            return game.getDefensePlayerId();
        }
    },
    OFFENSE
    {
        @Override
        public String getUserIdOf( FiarGame game )
        {
            return game.getOffensePlayerId();
        }
    };

    public FiarGamePlayer getCounterPart()
    {
        switch ( this )
        {
            case OFFENSE:
                return DEFENSE;
            case DEFENSE:
                return OFFENSE;
            default:
                throw new AssertionError( "Strange player " + this );
        }
    }

    public abstract String getUserIdOf( FiarGame game );
}
