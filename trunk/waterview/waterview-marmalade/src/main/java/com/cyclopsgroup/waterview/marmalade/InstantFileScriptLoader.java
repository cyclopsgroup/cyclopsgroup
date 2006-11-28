package com.cyclopsgroup.waterview.marmalade;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.codehaus.marmalade.MarmaladeException;
import org.codehaus.marmalade.metamodel.ScriptBuilder;
import org.codehaus.marmalade.model.MarmaladeScript;
import org.codehaus.marmalade.parsing.DefaultParsingContext;
import org.codehaus.marmalade.parsing.MarmaladeParsetimeException;
import org.codehaus.marmalade.parsing.MarmaladeParsingContext;
import org.codehaus.marmalade.parsing.ScriptParser;
import org.codehaus.marmalade.tags.passthrough.PassThroughTagLibrary;
import org.codehaus.marmalade.util.RecordingReader;

public class InstantFileScriptLoader
    implements ScriptLoader
{

    protected File getScriptFile( String scriptPath )
    {
        return new File( scriptPath );
    }

    public MarmaladeScript loadScript( String scriptPath )
        throws IOException, MarmaladeException
    {
        MarmaladeParsingContext parsingContext = new DefaultParsingContext();

        parsingContext.setDefaultTagLibrary( new PassThroughTagLibrary() );

        File scriptFile = getScriptFile( scriptPath );
        BufferedReader input = new BufferedReader( new FileReader( scriptFile ) );

        ScriptBuilder scriptBuilder = null;

        try
        {
            parsingContext.setInput( new RecordingReader( input ) );
            parsingContext.setInputLocation( scriptFile.getAbsolutePath() );

            ScriptParser parser = new ScriptParser();

            scriptBuilder = parser.parse( parsingContext );
            input.close();
        }
        catch ( MarmaladeParsetimeException e )
        {
            input.close();
            throw e;
        }
        return scriptBuilder.build();
    }
}
