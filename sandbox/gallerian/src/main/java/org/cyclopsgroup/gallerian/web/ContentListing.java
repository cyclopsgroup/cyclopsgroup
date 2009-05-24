package org.cyclopsgroup.gallerian.web;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.cyclopsgroup.gallerian.ContentListingService;
import org.cyclopsgroup.waterview.InputParameter;
import org.cyclopsgroup.waterview.InputParameterType;
import org.cyclopsgroup.waterview.Module;

/**
 * Web module that does content listing
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class ContentListing
{
    private static final Pattern REPOSITORY_NAME = Pattern.compile( "^\\/\\w+" );

    private final ContentListingService listing;

    /**
     * @param listing Need the listing service
     */
    public ContentListing( ContentListingService listing )
    {
        this.listing = listing;
    }

    /**
     * @param path Content path
     * @return Map of results
     */
    @Module( path = "/browse.vm", returnVariable = "contents" )
    public Map<String, Object> browse(
                                       @InputParameter( name = "contentPath", type = InputParameterType.VARIABLE ) String path )
    {
        HashMap<String, Object> result = new HashMap<String, Object>();
        if ( StringUtils.isEmpty( path ) || path.equals( "/" ) )
        {
            result.put( "dirs", listing.getRepositories() );
            result.put( "isRoot", Boolean.TRUE );
        }
        else
        {
            String subPath = REPOSITORY_NAME.matcher( path ).replaceFirst( "" );
            String repository = path.substring( 1, path.length() - subPath.length() );
            if(StringUtils.isEmpty( subPath ))
            {
                subPath = "/";
            }
            result.put( "dirs", listing.listFolders( repository, subPath, null ) );
            result.put( "isRoot", Boolean.FALSE );
            result.put( "files", listing.listContents( repository, subPath, null ));
        }
        return result;
    }
}
