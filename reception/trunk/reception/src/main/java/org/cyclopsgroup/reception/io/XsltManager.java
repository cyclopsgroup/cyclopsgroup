package org.cyclopsgroup.reception.io;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.net.URL;
import java.util.Collections;

import javax.cache.Cache;
import javax.cache.CacheException;
import javax.cache.CacheManager;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.Validate;

/**
 * Class that does actual XSLT transformation
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
public class XsltManager
{
    private final Cache transformerCache;

    private final TransformerFactory transformerFactory;

    /**
     * @throws CacheException If cache can't be initialized
     */
    public XsltManager()
        throws CacheException
    {
        this( CacheManager.getInstance().getCacheFactory().createCache( Collections.emptyMap() ) );
    }

    /**
     * @param transformerCache Given cache for transformers
     */
    XsltManager( Cache transformerCache )
    {
        this.transformerFactory = TransformerFactory.newInstance();
        this.transformerCache = transformerCache;
    }

    /**
     * @param xmlUrl URL of XML input
     * @param xsltUrl URL of static XSLT template
     * @param output Transformation output
     * @throws IOException If IO fails
     * @throws TransformerException If XSLT transformation fails
     */
    public void transform( String xmlUrl, String xsltUrl, Writer output )
        throws IOException, TransformerException
    {
        Validate.notNull( xmlUrl, "Input XML can't be NULL" );
        Validate.notNull( xsltUrl, "XSLT template URL can't be NULL" );
        InputStream input = new URL( xmlUrl ).openStream();
        try
        {
            transformerOf( xsltUrl ).transform( new StreamSource( input ), new StreamResult( output ) );
        }
        finally
        {
            input.close();
        }
    }

    @SuppressWarnings( "unchecked" )
    private Transformer transformerOf( String xsltUrl )
        throws IOException, TransformerConfigurationException
    {
        byte[] transformerData = (byte[]) transformerCache.get( xsltUrl );
        if ( transformerData == null )
        {
            transformerData = IOUtils.toByteArray( new URL( xsltUrl ).openStream() );
            synchronized ( transformerCache )
            {
                if ( !transformerCache.containsKey( xsltUrl ) )
                {
                    transformerCache.put( xsltUrl, transformerData );
                }
            }
        }
        InputStream input = new ByteArrayInputStream( transformerData );
        try
        {
            return transformerFactory.newTransformer( new StreamSource( input ) );
        }
        finally
        {
            input.close();
        }
    }
}
