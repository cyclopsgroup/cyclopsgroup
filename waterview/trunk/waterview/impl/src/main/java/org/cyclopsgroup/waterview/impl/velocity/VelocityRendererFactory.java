package org.cyclopsgroup.waterview.impl.velocity;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.FactoryBean;

/**
 * Spring factory bean that creates Velocity renderer
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class VelocityRendererFactory
    implements FactoryBean
{
    /**
     * @param templatePaths Value of field templatePaths to set
     */
    public final void setTemplatePaths( List<String> templatePaths )
    {
        Validate.notNull(templatePaths, "Paths can't be NULL");
        this.templatePaths = templatePaths;
    }

    /**
     * @param classPathPrefices Value of field classPathPrefices to set
     */
    public final void setClassPathPrefices( List<String> classPathPrefices )
    {
        Validate.notNull(classPathPrefices, "Prefices can't be NULL");
        this.classPathPrefices = classPathPrefices;
    }
    
    /**
     * @param templatePath Single template path
     */
    public void setTemplatePath(String templatePath)
    {
        setTemplatePaths(Collections.singletonList( templatePath ));
    }
    
    /**
     * @param prefix Single class path prefix
     */
    public void setClassPathPrefix(String prefix)
    {
        setClassPathPrefices(Collections.singletonList( prefix ));
    }

    private List<String> templatePaths = Collections.emptyList();
    private List<String> classPathPrefices = Collections.emptyList();

    /**
     * @inheritDoc
     */
    @Override
    public Object getObject()
    {
        VelocityEngineBuilder builder = new VelocityEngineBuilder();
        builder.addDeepClassPathResourceLoaders( classPathPrefices );
        for(String templatePath : templatePaths)
        {
            builder.addFileSystemResourceLoader( templatePath );
        }
        return new VelocityRenderer(builder.newEngine());
    }

    /**
     * @inheritDoc
     */
    @SuppressWarnings("unchecked")
    @Override
    public Class getObjectType()
    {
        return VelocityRenderer.class;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isSingleton()
    {
        return true;
    }

}
