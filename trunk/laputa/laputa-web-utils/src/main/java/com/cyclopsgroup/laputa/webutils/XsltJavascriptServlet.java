package com.cyclopsgroup.laputa.webutils;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class XsltJavascriptServlet
    extends HttpServlet
{
    private TransformerFactory transformerFactory;

    @Override
    public void init()
        throws ServletException
    {
        transformerFactory = TransformerFactory.newInstance();
    }

    @Override
    protected void doGet( HttpServletRequest req, HttpServletResponse resp )
        throws ServletException, IOException
    {
        String xml = req.getParameter( "xml" );
        URL xmlurl = new URL( xml );
        String xsl = req.getParameter( "xsl" );
        URL xslurl = new URL( xsl );

        resp.setContentType( "text/javascript" );
        JavascriptEscapingWriter output = new JavascriptEscapingWriter( resp.getWriter() );
        try
        {
            Transformer transformer = transformerFactory.newTransformer( new StreamSource( xslurl.openStream() ) );
            transformer.transform( new StreamSource( xmlurl.openStream() ), new StreamResult( output ) );
        }
        catch ( Exception e )
        {
            e.printStackTrace( new PrintWriter( output ) );
        }
        finally
        {
            output.close();
        }
    }
}
