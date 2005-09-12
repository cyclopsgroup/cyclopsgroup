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
package com.cyclopsgroup.waterview;

import java.io.PrintWriter;
import java.util.HashMap;

import com.cyclopsgroup.waterview.utils.FakeServiceManager;

/**
 * Fake page runtime object
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class MockRuntimeData
    extends AbstractRuntimeData
    implements RuntimeData
{
    private String page;

    private Path pageObject;

    /**
     * Constructor for class FakePageRuntime
     *
     * @param output Output
     */
    public MockRuntimeData( PrintWriter output )
    {
        setOutput( output );
        setApplicationBaseUrl( "http://localhost:8080/waterview" );
        setPageBaseUrl( "http://localhost:8080/waterview/servlet/waterview" );
        setRequestContext( new DefaultContext( new HashMap() ) );
        setParams( new MapRequestValueParser() );
        setRequestPath( "/index.html" );
        setServiceManager( new FakeServiceManager() );
        setSessionContext( new DefaultContext( new HashMap() ) );
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.RuntimeData#getMimeType(java.lang.String)
     */
    public String getMimeType( String fileName )
    {
        return "text/html";
    }

    /**
     * Overwrite or implement method getPage()
     *
     * @see com.cyclopsgroup.waterview.RuntimeData#getPage()
     */
    public Path getPage()
    {
        return pageObject;
    }

    /**
     * @see com.cyclopsgroup.waterview.RuntimeData#getSessionId()
     */
    public String getSessionId()
    {
        return "";
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.RuntimeData#setOutputContentType(java.lang.String)
     */
    public void setOutputContentType( String contentType )
    {
    }

    /**
     * Overwrite or implement method setPage()
     *
     * @see com.cyclopsgroup.waterview.RuntimeData#setPage(java.lang.String)
     */
    public void setPage( String page )
    {
        this.page = page;
        this.pageObject = new Path()
        {
            /**
             * Overwrite or implement method getExtension()
             *
             * @see com.cyclopsgroup.waterview.Path#getExtension()
             */
            public String getExtension()
            {
                // TODO Auto-generated method stub
                return null;
            }

            /**
             * Overwrite or implement method getFullPath()
             *
             * @see com.cyclopsgroup.waterview.Path#getFullPath()
             */
            public String getFullPath()
            {
                return getPath();
            }

            /**
             * Overwrite or implement method getPackage()
             *
             * @see com.cyclopsgroup.waterview.Path#getPackage()
             */
            public String getPackage()
            {
                return "";
            }

            /**
             * Overwrite or implement method getPackageAlias()
             *
             * @see com.cyclopsgroup.waterview.Path#getPackageAlias()
             */
            public String getPackageAlias()
            {
                return "";
            }

            /**
             * Overwrite or implement method getPath()
             *
             * @see com.cyclopsgroup.waterview.Path#getPath()
             */
            public String getPath()
            {
                return MockRuntimeData.this.page;
            }

            /**
             * Overwrite or implement method getPathWithoutExtension()
             *
             * @see com.cyclopsgroup.waterview.Path#getPathWithoutExtension()
             */
            public String getPathWithoutExtension()
            {
                return getPath();
            }
        };
    }
}
