/* ==========================================================================
 * Copyright 2002-2005 Cyclops Group Community
 * 
 * Licensed under the Open Software License, Version 2.1 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://opensource.org/licenses/osl-2.1.php
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * =========================================================================
 */
package com.cyclopsgroup.waterview.servlet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;

import com.cyclopsgroup.waterview.MapParameters;

/**
 * Multipart servlet request value parser
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class MultipartServletRequestParameters
    extends MapParameters
{
    private final Map<String, List<FileItem>> fileItems = new HashMap<String, List<FileItem>>();

    /**
     * Constructor for class MultipartServletRequestValueParser
     *
     * @param request Http request object
     * @param fileUpload File upload object
     * @throws FileUploadException Throw it out
     */
    public MultipartServletRequestParameters( HttpServletRequest request, FileUploadBase fileUpload )
        throws FileUploadException
    {
        for ( Object item : fileUpload.parseRequest( request ) )
        {
            FileItem fileItem = (FileItem) item;
            if ( fileItem.isFormField() )
            {
                add( fileItem.getFieldName(), fileItem.getString() );
            }
            else
            {
                List<FileItem> list = fileItems.get( fileItem.getFieldName() );
                if ( list == null )
                {
                    list = new ArrayList<FileItem>();
                    fileItems.put( fileItem.getFieldName(), list );
                }
                list.add( fileItem );
            }
        }
    }

    /**
     * Overwrite or implement method doGetValue()
     * @see com.cyclopsgroup.waterview.Attributes#doGetValue(java.lang.String)
     */
    @Override
    protected String doGetValue( String name )
        throws Exception
    {
        List<String> values = doGetValues( name );
        return values.isEmpty() ? null : values.get( 0 );
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.Parameters#getFileItem(java.lang.String)
     */
    @Override
    public FileItem getFileItem( String name )
    {
        Collection<FileItem> items = getFileItems( name );
        return items.size() == 0 ? null : items.iterator().next();
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.Parameters#getFileItems(java.lang.String)
     */
    @Override
    public List<FileItem> getFileItems( String name )
    {
        List<FileItem> items = fileItems.get( name );
        if ( items == null )
        {
            items = Collections.emptyList();
        }
        return items;
    }
}
