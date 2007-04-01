package com.cyclopsgroup.waterview.spi;

public class TemplateNotFoundException
    extends Exception
{
    private static final long serialVersionUID = 1L;

    private String templatePath;

    public TemplateNotFoundException( String templatePath )
    {
        super( "Template " + templatePath + " is not found" );
        this.templatePath = templatePath;
    }

    public String getTemplatePath()
    {
        return templatePath;
    }
}
