package com.cyclopsgroup.laputa.webutils;

import java.io.IOException;
import java.io.StringWriter;

import junit.framework.TestCase;

public class JavascriptEscapingWriterTest
    extends TestCase
{
    public void testWrite()
        throws IOException
    {
        StringWriter s = new StringWriter();
        JavascriptEscapingWriter w = new JavascriptEscapingWriter( s );
        w.write( "hello \"john\"\n" );
        w.flush();
        assertEquals( "document.writeln(\"hello \\\"john\\\"\");", s.toString().trim() );

        w.write( "another line\\" );
        w.close();
    }
}
