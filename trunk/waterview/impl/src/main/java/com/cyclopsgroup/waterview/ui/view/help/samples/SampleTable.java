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
package com.cyclopsgroup.waterview.ui.view.help.samples;

import java.util.ArrayList;
import java.util.List;

import com.cyclopsgroup.waterview.Context;
import com.cyclopsgroup.waterview.Module;
import com.cyclopsgroup.waterview.RunData;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 * 
 * Module for sample table
 */
public class SampleTable
    implements Module
{
    /** Row class */
    public class Row
    {
        private String description;

        private String id;

        private String name;

        private int totalAmount;

        /**
         * Getter method for field description
         *
         * @return Returns the description.
         */
        public String getDescription()
        {
            return description;
        }

        /**
         * Getter method for field id
         *
         * @return Returns the id.
         */
        public String getId()
        {
            return id;
        }

        /**
         * Getter method for field name
         *
         * @return Returns the name.
         */
        public String getName()
        {
            return name;
        }

        /**
         * Getter method for field totalAmount
         *
         * @return Returns the totalAmount.
         */
        public int getTotalAmount()
        {
            return totalAmount;
        }

        /**
         * Setter method for field description
         *
         * @param description The description to set.
         */
        public void setDescription( String description )
        {
            this.description = description;
        }

        /**
         * Setter method for field id
         *
         * @param id The id to set.
         */
        public void setId( String id )
        {
            this.id = id;
        }

        /**
         * Setter method for field name
         *
         * @param name The name to set.
         */
        public void setName( String name )
        {
            this.name = name;
        }

        /**
         * Setter method for field totalAmount
         *
         * @param totalAmount The totalAmount to set.
         */
        public void setTotalAmount( int totalAmount )
        {
            this.totalAmount = totalAmount;
        }
    }

    private static String[] NAMES = new String[] { "hello", "yeeha", "ubuntu", "pie", "chicken" };

    /**
     * Overwrite or implement method execute()
     *
     * @see com.cyclopsgroup.waterview.Module#execute(com.cyclopsgroup.waterview.RunData, com.cyclopsgroup.waterview.Context)
     */
    public void execute( RunData data, Context context )
        throws Exception
    {
        List ret = new ArrayList();
        for ( int i = 0; i < 20; i++ )
        {
            Row row = new Row();
            row.setId( "" + i );
            row.setName( NAMES[i % 5] );
            row.setTotalAmount( i * 23 % 17 );
            row.setDescription( "aaaaaaa" );
            ret.add( row );
        }
        context.put( "rows", ret );
    }
}
