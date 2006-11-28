package com.cyclopsgroup.waterview.marmalade;

import java.io.IOException;

import org.codehaus.marmalade.MarmaladeException;
import org.codehaus.marmalade.model.MarmaladeScript;

public interface ScriptLoader
{
    MarmaladeScript loadScript( String scriptPath )
        throws IOException, MarmaladeException;
}
