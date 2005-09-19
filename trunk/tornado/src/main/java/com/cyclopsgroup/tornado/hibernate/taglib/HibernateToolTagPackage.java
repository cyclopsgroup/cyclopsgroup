/* ==========================================================================
 * Copyright 2002-2005 Cyclops Group Community
 * 
 * Licensed under the COMMON DEVELOPMENT AND DISTRIBUTION LICENSE
 * (CDDL) Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.opensource.org/licenses/cddl1.txt
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * =========================================================================
 */
package com.cyclopsgroup.tornado.hibernate.taglib;

import com.cyclopsgroup.waterview.utils.TagPackage;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 */
public class HibernateToolTagPackage
    extends TagPackage
{
    /**
     * Constructor for class HibernateTagPackage
     */
    public HibernateToolTagPackage()
    {
        addTag( "Hibernate", HibernateTag.class );
        addTag( "CreateTables", CreateTablesTag.class );
        addTag( "DropTables", DropTablesTag.class );
        addTag( "UpdateSchema", UpdateSchemaTag.class );
        addTag( "Class", ClassTag.class );
        addTag( "New", NewTag.class );
    }
}
