package org.cyclopsgroup.waterview.impl.module;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.Validate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * A spring bean that loads web modules from web-module.xml files in given packages
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class SpringPackageModuleResolver
    implements ModuleResolver, ApplicationContextAware, InitializingBean
{
    private static final Log LOG = LogFactory.getLog( SpringPackageModuleResolver.class );

    private ApplicationContext applicationContext;

    private final List<String> packages;

    private CollectionModuleResolver resolver;

    /**
     * @param packages List of package names
     */
    public SpringPackageModuleResolver( List<String> packages )
    {
        Validate.notNull( packages, "Packages can't be NULL" );
        this.packages = Collections.unmodifiableList( new ArrayList<String>( packages ) );
    }

    /**
     * @param packages Array of package names
     */
    public SpringPackageModuleResolver( String... packages )
    {
        this( Arrays.asList( packages ) );
    }

    /**
     * @inheritDoc
     */
    @Override
    public void afterPropertiesSet()
    {
        ArrayList<String> configs = new ArrayList<String>( packages.size() );
        for ( String packagePath : packages )
        {
            String path = packagePath.trim().replace( '.', '/' ) + "/web-modules.xml";
            configs.add( path );
        }
        LOG.info( "Loading web modules defined in " + configs );
        String[] configLocations = configs.toArray( ArrayUtils.EMPTY_STRING_ARRAY );
        ClassPathXmlApplicationContext context =
            new ClassPathXmlApplicationContext( configLocations, applicationContext );
        List<Object> modules = new ArrayList<Object>();
        for ( String name : context.getBeanDefinitionNames() )
        {
            Object bean = context.getBean( name );
            LOG.info( "Loading web module " + name + ":" + bean );
            modules.add( bean );
        }
        resolver = new CollectionModuleResolver( modules );
    }

    /**
     * @inheritDoc
     */
    @Override
    public WebModule findModule( String name )
    {
        return resolver.findModule( name );
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setApplicationContext( ApplicationContext context )
        throws BeansException
    {
        applicationContext = context;
    }

}
