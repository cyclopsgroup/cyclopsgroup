package org.cyclopsgroup.waterview.ipa;

/**
 * Specified template doesn't exist
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class TemplateNotFoundException
    extends RuntimeException
{
    private static final long serialVersionUID = -7061461220615481661L;

    private final String templatePath;

    /**
     * @param message Error message
     * @param templatePath Missing template
     */
    public TemplateNotFoundException( String message, String templatePath )
    {
        super( templatePath + ":" + message );
        this.templatePath = templatePath;
    }

    /**
     * @param message Error message
     * @param e Cause
     * @param templatePath Missing template
     */
    public TemplateNotFoundException( String message, Throwable e, String templatePath )
    {
        super( templatePath + ":" + message, e );
        this.templatePath = templatePath;
    }

    /**
     * @return Path of missing template
     */
    public final String getTemplatePath()
    {
        return templatePath;
    }
}
