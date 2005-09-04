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
package com.cyclopsgroup.courselist.persist;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.avalon.framework.activity.Initializable;
import org.apache.avalon.framework.context.Context;
import org.apache.avalon.framework.context.ContextException;
import org.apache.avalon.framework.context.Contextualizable;
import org.apache.avalon.framework.logger.AbstractLogEnabled;

import com.cyclopsgroup.courselist.Course;
import com.cyclopsgroup.courselist.CourseFilter;
import com.cyclopsgroup.courselist.CoursePersistenceManager;
import com.cyclopsgroup.waterview.Waterview;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 * 
 * Default implementation of course persistence manager
 */
public class DefaultCoursePersistenceManager extends AbstractLogEnabled
        implements CoursePersistenceManager, Initializable, Contextualizable
{
    private Hashtable courses = new Hashtable();

    private String dataFile;

    /**
     * Save changes
     */
    protected void commitChanges()
    {
        try
        {
            XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(
                    new FileOutputStream(dataFile)));
            encoder.writeObject(courses);
            encoder.close();
        }
        catch (Exception e)
        {
            getLogger().error("Can not commit change", e);
        }
    }

    /**
     * Overwrite or implement method contextualize()
     *
     * @see org.apache.avalon.framework.context.Contextualizable#contextualize(org.apache.avalon.framework.context.Context)
     */
    public void contextualize(Context context) throws ContextException
    {
        Properties props = (Properties) context.get(Waterview.INIT_PROPERTIES);
        dataFile = props.getProperty("basedir") + "/data.xml";
    }

    /**
     * Overwrite or implement method delete()
     *
     * @see com.cyclopsgroup.courselist.CoursePersistenceManager#delete(com.cyclopsgroup.courselist.Course)
     */
    public void delete(Course course)
    {
        delete(course.getNumber());
    }

    /**
     * Overwrite or implement method delete()
     *
     * @see com.cyclopsgroup.courselist.CoursePersistenceManager#delete(java.lang.String)
     */
    public void delete(String number)
    {
        courses.remove(number);
        commitChanges();
    }

    /**
     * Overwrite or implement method find()
     *
     * @see com.cyclopsgroup.courselist.CoursePersistenceManager#find(com.cyclopsgroup.courselist.CourseFilter)
     */
    public Course[] find(CourseFilter filter)
    {
        if (filter == null)
        {
            return list();
        }
        List ret = new ArrayList();
        for (Iterator i = courses.values().iterator(); i.hasNext();)
        {
            Course course = (Course) i.next();
            try
            {
                if (filter.evaluate(course))
                {
                    ret.add(course);
                }
            }
            catch (Exception e)
            {
                getLogger().warn("Evaluation error", e);
            }
        }
        return (Course[]) ret.toArray(Course.EMPTY_ARRAY);
    }

    /**
     * Overwrite or implement method findByNumber()
     *
     * @see com.cyclopsgroup.courselist.CoursePersistenceManager#findByNumber(java.lang.String)
     */
    public Course findByNumber(String number)
    {
        return (Course) courses.get(number);
    }

    /**
     * Overwrite or implement method initialize()
     *
     * @see org.apache.avalon.framework.activity.Initializable#initialize()
     */
    public void initialize() throws Exception
    {
        File data = new File(dataFile);
        if (!data.getParentFile().isDirectory())
        {
            data.getParentFile().mkdirs();
        }

        if (data.exists())
        {
            XMLDecoder d = new XMLDecoder(new BufferedInputStream(
                    new FileInputStream(data)));
            courses = (Hashtable) d.readObject();
            d.close();
        }
        else
        {
            courses = new Hashtable();
        }
    }

    /**
     * Overwrite or implement method list()
     *
     * @see com.cyclopsgroup.courselist.CoursePersistenceManager#list()
     */
    public Course[] list()
    {
        return (Course[]) courses.values().toArray(Course.EMPTY_ARRAY);
    }

    /**
     * Overwrite or implement method save()
     *
     * @see com.cyclopsgroup.courselist.CoursePersistenceManager#save(com.cyclopsgroup.courselist.Course)
     */
    public void save(Course course)
    {
        courses.put(course.getNumber(), course);
        commitChanges();
    }
}
