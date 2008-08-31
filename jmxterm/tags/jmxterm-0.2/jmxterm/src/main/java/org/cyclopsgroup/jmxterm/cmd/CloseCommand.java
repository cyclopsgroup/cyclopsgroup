package org.cyclopsgroup.jmxterm.cmd;

import java.io.IOException;

import org.cyclopsgroup.jcli.annotation.Cli;
import org.cyclopsgroup.jmxterm.Command;
import org.cyclopsgroup.jmxterm.Session;

/**
 * Command to close current connection
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
@Cli( name = "close", description = "Close JMX connection" )
public class CloseCommand
    extends Command
{
    /**
     * @inheritDoc
     */
    @Override
    public void execute( Session session )
        throws IOException
    {
        session.disconnect();
    }
}