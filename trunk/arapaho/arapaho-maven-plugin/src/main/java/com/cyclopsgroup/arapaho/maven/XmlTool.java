package com.cyclopsgroup.arapaho.maven;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

public class XmlTool
{
    private SAXReader saxReader = new SAXReader();

    public List<List<Object>> groupBy( List<Object> list, int groupSize )
    {
        List<List<Object>> groups = new ArrayList<List<Object>>( list.size() / groupSize + 1 );
        for ( int index = 0; index < list.size(); index++ )
        {
            List<Object> group;
            if ( index % groupSize == 0 )
            {
                group = new ArrayList<Object>( groupSize );
                groups.add( group );
            }
            else
            {
                group = groups.get( index / groupSize );
            }
            group.add( list.get( index ) );
        }

        return groups;
    }

    public Document parseFile( String filePath )
        throws DocumentException
    {
        return saxReader.read( new File( filePath ) );
    }

    public Document parseUrl( String urlString )
        throws DocumentException, MalformedURLException
    {
        return saxReader.read( new URL( urlString ) );
    }
}