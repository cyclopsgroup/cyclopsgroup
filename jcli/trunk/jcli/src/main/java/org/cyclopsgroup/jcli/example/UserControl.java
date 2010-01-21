package org.cyclopsgroup.jcli.example;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.cyclopsgroup.jcli.annotation.Argument;
import org.cyclopsgroup.jcli.annotation.Cli;
import org.cyclopsgroup.jcli.annotation.MultiValue;
import org.cyclopsgroup.jcli.annotation.Option;

@Cli( name = "user", description = "Comamnd line tool that manages user accounts" )
public class UserControl
{
    private UserControlAction action = UserControlAction.DISPLAY;

    private List<String> userNames;

    @Option( name = "a", longName = "action", description = "Action to perform" )
    public final UserControlAction getAction()
    {
        return action;
    }

    @MultiValue
    @Argument( description = "User account name" )
    public final List<String> getUserNames()
    {
        return userNames;
    }

    public final void setAction( UserControlAction action )
    {
        this.action = action;
    }

    public final void setUserNames( List<String> userNames )
    {
        this.userNames = userNames;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString( this );
    }
}
