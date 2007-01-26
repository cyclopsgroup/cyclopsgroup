package com.cyclopsgroup.waterview.spi;

public class TemplateNotFoundException
    extends Exception
{
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
