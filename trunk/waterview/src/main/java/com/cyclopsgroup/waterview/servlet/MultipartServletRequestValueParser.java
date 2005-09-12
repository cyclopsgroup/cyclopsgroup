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

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.MultiHashMap;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.lang.ArrayUtils;

import com.cyclopsgroup.waterview.RequestValueParser;

/**
 * Multipart servlet request value parser
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class MultipartServletRequestValueParser
    extends RequestValueParser
{
    private MultiHashMap content = new MultiHashMap();

    private MultiHashMap fileItems = new MultiHashMap();

    /**
     * Constructor for class MultipartServletRequestValueParser
     *
     * @param request Http request object
     * @param fileUpload File upload object
     * @throws FileUploadException Throw it out
     */
    public MultipartServletRequestValueParser( HttpServletRequest request, FileUploadBase fileUpload )
        throws FileUploadException
    {
        List files = fileUpload.parseRequest( request );
        for ( Iterator i = files.iterator(); i.hasNext(); )
        {
            FileItem fileItem = (FileItem) i.next();
            if ( fileItem.isFormField() )
            {
                add( fileItem.getFieldName(), fileItem.getString() );
            }
            else
            {
                fileItems.put( fileItem.getFieldName(), fileItem );
            }
        }
    }

    /**
     * Overwrite or implement method add()
     * @see com.cyclopsgroup.waterview.ValueParser#add(java.lang.String, java.lang.String)
     */
    public void add( String name, String value )
    {
        content.put( name, value );
    }

    /**
     * Overwrite or implement method doGetValue()
     * @see com.cyclopsgroup.waterview.ValueParser#doGetValue(java.lang.String)
     */
    protected String doGetValue( String name )
        throws Exception
    {
        String[] values = doGetValues( name );
        return values.length == 0 ? null : values[0];
    }

    /**
     * Overwrite or implement method doGetValues()
     * @see com.cyclopsgroup.waterview.ValueParser#doGetValues(java.lang.String)
     */
    protected String[] doGetValues( String name )
        throws Exception
    {
        Collection values = (Collection) content.get( name );
        return values == null ? ArrayUtils.EMPTY_STRING_ARRAY : (String[]) values
            .toArray( ArrayUtils.EMPTY_STRING_ARRAY );
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.RequestValueParser#getFileItem(java.lang.String)
     */
    public FileItem getFileItem( String name )
    {
        FileItem[] items = getFileItems( name );
        return items.length == 0 ? null : items[0];
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.RequestValueParser#getFileItems(java.lang.String)
     */
    public FileItem[] getFileItems( String name )
    {
        Collection items = (Collection) fileItems.get( name );
        return items == null ? EMPTY_FILEITEM_ARRAY : (FileItem[]) items.toArray( EMPTY_FILEITEM_ARRAY );
    }

    /**
     * Overwrite or implement method remove()
     * @see com.cyclopsgroup.waterview.ValueParser#remove(java.lang.String)
     */
    public void remove( String name )
    {
        content.remove( name );
        fileItems.remove( name );
    }
}
