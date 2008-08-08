package org.cyclopsgroup.jcli.jline;

import java.util.List;

import jline.ConsoleReader;

import org.apache.commons.lang.ArrayUtils;
import org.cyclopsgroup.jcli.ExampleNormalBean;
import org.cyclopsgroup.jcli.QuotedStringTokenizer;
import org.cyclopsgroup.jcli.jccli.JakartaCommonsCliParser;

/**
 * Example console for testing purpose
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class ExampleConsole
{
    /**
     * Main entry method
     * 
     * @param args Command line arguments
     * @throws Exception
     */
    public static void main( String[] args )
        throws Exception
    {
        JakartaCommonsCliParser parser = new JakartaCommonsCliParser();
        QuotedStringTokenizer tokenizer = new QuotedStringTokenizer();

        ConsoleReader reader = new ConsoleReader();
        reader.addCompletor( new CliCompletor( new ExampleNormalBean(), tokenizer ) );
        String line;
        while ( !( line = reader.readLine( "%" ) ).equals( "exit" ) )
        {
            List<String> params = tokenizer.parse( line );
            ExampleNormalBean bean = new ExampleNormalBean();
            try
            {
                parser.parse( params.toArray( ArrayUtils.EMPTY_STRING_ARRAY ), bean );
                System.out.println( bean );
            }
            catch ( Exception e )
            {
                System.out.println( e );
            }
        }
    }
}
