package com.cyclopsgroup.arapaho.maven;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.dialect.Dialect;

/**
 * @description Generate hibernate schema
 * @author <a href="mailto:jiaqi.guo@gmail.com>jiaqi</a>
 * @goal schema-drop
 * @requiresDependencyResolution runtime
 */
public class SchemaDropSqlMojo
    extends AbstractHibernateMojo
{
    /**
     * @description Path of output sql file 
     * @parameter expression="${basedir}/target/sql/drop-tables.sql"
     * @required
     */
    private File output;

    /**
     * @see com.cyclopsgroup.arapaho.maven.AbstractHibernateMojo#doExecute(org.hibernate.cfg.AnnotationConfiguration)
     */
    protected void doExecute( AnnotationConfiguration config )
        throws Exception
    {
        if ( !output.getParentFile().isDirectory() )
        {
            output.getParentFile().mkdirs();
        }
        Dialect dialect = Dialect.getDialect( config.getProperties() );
        String[] sqls = config.generateDropSchemaScript( dialect );
        PrintWriter out = new PrintWriter( new FileWriter( output ) );
        for ( String sql : sqls )
        {
            out.println( sql );
        }
        out.flush();
        out.close();
    }
}