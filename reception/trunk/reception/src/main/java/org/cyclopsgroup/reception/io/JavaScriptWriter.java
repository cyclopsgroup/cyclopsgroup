package org.cyclopsgroup.reception.io;

import java.io.BufferedWriter;
import java.io.Writer;

public class JavaScriptWriter
    extends BufferedWriter
{

    public JavaScriptWriter( Writer output, int bufferSize )
    {
        super( output, bufferSize );
    }

    public JavaScriptWriter( Writer output )
    {
        super( output );
    }

}
