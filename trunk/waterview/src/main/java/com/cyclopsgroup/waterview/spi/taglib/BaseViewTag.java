package com.cyclopsgroup.waterview.spi.taglib;

import org.apache.commons.jelly.XMLOutput;
import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.waterview.Context;
import com.cyclopsgroup.waterview.RuntimeData;
import com.cyclopsgroup.waterview.spi.JellyContextAdapter;
import com.cyclopsgroup.waterview.spi.View;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Base view tag
 */
public abstract class BaseViewTag
    extends TagSupport
{
    private String name;

    /**
     * Get name
     *
     * @return Name of view
     */
    public final String getName()
    {
        return name;
    }

    /**
     *Set view name
     *
     * @param name View name
     */
    public final void setName( String name )
    {
        this.name = name;
    }

    /**
     * Overwrite this method to create component
     *
     * @return Create view, null is ok
     * @throws Exception Throw it out
     */
    protected abstract View createView()
        throws Exception;

    /**
     * Overwrite or implement method processTag()
     *
     * @see com.cyclopsgroup.waterview.utils.TagSupportBase#processTag(org.apache.commons.jelly.XMLOutput)
     */
    protected void processTag( XMLOutput output )
        throws Exception
    {
        final View v = createView();
        View view = null;
        if ( StringUtils.isNotEmpty( getName() ) )
        {
            view = new View()
            {

                public String getName()
                {
                    return BaseViewTag.this.getName();
                }

                public void render( RuntimeData data, Context viewContext )
                    throws Exception
                {
                    v.render( data, viewContext );
                }
            };
        }
        else
        {
            view = v;
        }

        if ( view == null )
        {
            return;
        }
        if ( getParent() instanceof ViewAware )
        {
            ( (ViewAware) getParent() ).doView( view );
        }
        else
        {
            RuntimeData data = getRuntimeData();
            try
            {
                JellyContextAdapter jc = new JellyContextAdapter( getContext() );
                view.render( data, jc );
            }
            catch ( Exception e )
            {
                data.getOutput().println( "<pre>" );
                e.printStackTrace( data.getOutput() );
                data.getOutput().println( "</pre>" );
            }
        }
    }
}