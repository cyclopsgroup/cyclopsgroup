package com.cyclopsgroup.laputa.webutils;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.PosixParser;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

public class EatjRemoteControl
{
    /**
     * @param args
     */
    public static void main( String[] args )
        throws Exception
    {
        Options opts = new Options();
        opts.addOption( "u", "user", true, "User name" );
        opts.addOption( "p", "password", true, "Password" );
        opts.getOption( "p" ).setRequired( true );
        opts.addOption( "s", "siteurl", true, "Base URL of eatj.com website" );
        opts.addOption( "h", "help", false, "Display usage" );

        PosixParser parser = new PosixParser();
        CommandLine cli = parser.parse( opts, args );

        if ( cli.hasOption( "h" ) )
        {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp( "ccc", opts );
            return;
        }

        HttpClient client = new HttpClient();

        String siteUrl = cli.getOptionValue( "s", "http://s42.eatj.com" );
        PostMethod login = new PostMethod( siteUrl + "/logonhandler1.jsp" );
        String user = cli.getOptionValue( 'u', System.getProperty( "user.name" ) );
        String password = cli.getOptionValue( 'p' );
        login.addParameter( "username", user );
        login.addParameter( "password", password );
        client.executeMethod( login );

        for ( String op : cli.getArgs() )
        {
            if ( op.equals( "restart" ) )
            {
                GetMethod restart = new GetMethod( siteUrl + "/acctmanager.jsp" );
                restart.setQueryString( "action=restart" );
                client.executeMethod( restart );
            }
        }

        GetMethod logout = new GetMethod( siteUrl + "/logout.jsp" );
        client.executeMethod( logout );
    }
}
