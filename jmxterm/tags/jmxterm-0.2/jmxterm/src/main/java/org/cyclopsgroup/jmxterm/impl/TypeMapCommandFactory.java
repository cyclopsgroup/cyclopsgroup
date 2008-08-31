package org.cyclopsgroup.jmxterm.impl;

import java.util.Collections;
import java.util.Map;

import org.apache.commons.lang.Validate;
import org.cyclopsgroup.jmxterm.Command;
import org.cyclopsgroup.jmxterm.CommandFactory;

/**
 * CommandFactory implementation based on a Map of command types
 *
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class TypeMapCommandFactory
    implements CommandFactory
{
    private final Map<String, Class<? extends Command>> commandTypes;

    /**
     * @param commandTypes Map of command types
     */
    public TypeMapCommandFactory( Map<String, Class<? extends Command>> commandTypes )
    {
        Validate.notNull( commandTypes, "Command type can't be NULL" );
        this.commandTypes = Collections.unmodifiableMap( commandTypes );
    }

    /**
     * @inheritDoc
     */
    public Command createCommand( String commandName )
        throws InstantiationException, IllegalAccessException, IllegalArgumentException
    {
        Validate.notNull( commandName, "commandName can't be NULL" );
        Class<? extends Command> commandType = commandTypes.get( commandName );
        if ( commandType == null )
        {
            throw new IllegalArgumentException( "Command " + commandName
                + " isn't valid, run help to see available commands" );
        }
        return commandType.newInstance();
    }

    /**
     * @inheritDoc
     */
    public Map<String, Class<? extends Command>> getCommandTypes()
    {
        return commandTypes;
    }
}