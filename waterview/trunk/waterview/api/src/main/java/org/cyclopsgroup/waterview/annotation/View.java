package org.cyclopsgroup.waterview.annotation;

/**
 * Flag an object as View module
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public @interface View
{
    /**
     * @return Path of template to render this view
     */
    String template();

    /**
     * @return Name of view
     */
    String name();
}
