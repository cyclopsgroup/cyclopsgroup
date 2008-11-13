package com.cyclopsgroup.waterview;

import java.util.HashMap;

/**
 *
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class ViewContext
    extends DefaultContext
{
    private String templatePath;

    public ViewContext( Context parent )
    {
        super( new HashMap<String, Object>(), parent );
    }

    public String getTemplatePath()
    {
        return templatePath;
    }

    public void setTemplatePath( String templatePath )
    {
        this.templatePath = templatePath;
    }
}