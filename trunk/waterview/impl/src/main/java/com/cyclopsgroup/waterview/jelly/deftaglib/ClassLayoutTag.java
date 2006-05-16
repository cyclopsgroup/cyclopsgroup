package com.cyclopsgroup.waterview.jelly.deftaglib;

import org.apache.commons.jelly.XMLOutput;

import com.cyclopsgroup.waterview.spi.Layout;
import com.cyclopsgroup.waterview.utils.TagSupport;

/**
 * Define layout with class name of it
 * 
 * @author jiaqi
 *
 */
public class ClassLayoutTag
    extends TagSupport
{
    private String className;

    public String getClassName()
    {
        return className;
    }

    public void setClassName( String className )
    {
        this.className = className;
    }

    /**
     * @see com.cyclopsgroup.waterview.utils.TagSupportBase#processTag(org.apache.commons.jelly.XMLOutput)
     */
    protected void processTag( XMLOutput output )
        throws Exception
    {
        requireAttribute( "className" );
        LayoutTag layoutTag = (LayoutTag) requireParent( LayoutTag.class );

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Layout layout = (Layout) classLoader.loadClass( getClassName() ).newInstance();

        layoutTag.setLayout( layout );
    }
}
