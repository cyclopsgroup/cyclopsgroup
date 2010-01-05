package org.cyclopsgroup.fiar.service.pojo;

public enum FiarGamePlayer
{
    OFFENSE
    {
        @Override
        public String getUserIdOf( FiarGame game )
        {
            return game.getOffensePlayerId();
        }
    },
    DEFENSE
    {
        @Override
        public String getUserIdOf( FiarGame game )
        {
            return game.getDefensePlayerId();
        }

    };

    public abstract String getUserIdOf( FiarGame game );
}
