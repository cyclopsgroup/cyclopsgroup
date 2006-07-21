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
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.MultiHashMap;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;

import com.cyclopsgroup.waterview.Parameters;

/**
 * Multipart servlet request value parser
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class MultipartServletRequestParameters
    extends Parameters
{
    private MultiHashMap content = new MultiHashMap();

    private MultiHashMap fileItems = new MultiHashMap();

    /**
     * Override method doGetAttributeNames in class MultipartServletRequestValueParser
     *
     * @see com.cyclopsgroup.waterview.Attributes#doGetAttributeNames()
     */
    @Override
    protected Set<String> doGetAttributeNames()
    {
        return content.keySet();
    }

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
     * @see com.cyclopsgroup.waterview.Attributes#add(java.lang.String, java.lang.String)
     */
    public void add( String name, String value )
    {
        content.put( name, value );
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
     * Overwrite or implement method doGetValues()
     * @see com.cyclopsgroup.waterview.Attributes#doGetValues(java.lang.String)
     */
    @Override
    protected List<String> doGetValues( String name )
        throws Exception
    {
        Collection<String> values = (Collection<String>) content.get( name );
        if ( values instanceof List )
        {
            return (List<String>) values;
        }
        return new ArrayList<String>( values );
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.Parameters#getFileItem(java.lang.String)
     */
    @Override
    public FileItem getFileItem( String name )
    {
        FileItem[] items = getFileItems( name );
        return items.length == 0 ? null : items[0];
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.Parameters#getFileItems(java.lang.String)
     */
    @Override
    public FileItem[] getFileItems( String name )
    {
        Collection items = (Collection) fileItems.get( name );
        return items == null ? EMPTY_FILEITEM_ARRAY : (FileItem[]) items.toArray( EMPTY_FILEITEM_ARRAY );
    }

    /**
     * Overwrite or implement method remove()
     * @see com.cyclopsgroup.waterview.Attributes#remove(java.lang.String)
     */
    @Override
    public void remove( String name )
    {
        content.remove( name );
        fileItems.remove( name );
    }
}
