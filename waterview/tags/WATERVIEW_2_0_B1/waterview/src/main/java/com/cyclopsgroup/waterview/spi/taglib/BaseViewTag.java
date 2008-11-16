package com.cyclopsgroup.waterview.spi.taglib;

import java.util.HashMap;

import org.apache.commons.jelly.XMLOutput;

import com.cyclopsgroup.waterview.DefaultContext;
import com.cyclopsgroup.waterview.RuntimeData;
import com.cyclopsgroup.waterview.spi.JellyContextAdapter;
import com.cyclopsgroup.waterview.spi.View;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Base view tag
 */
public abstract class BaseViewTag extends TagSupport
{
    /**
     * Overwrite this method to create component
     *
     * @return Create view, null is ok
     * @throws Exception Throw it out
     */
    protected abstract View createView() throws Exception;

    /**
     * Overwrite or implement method processTag()
     *
     * @see com.cyclopsgroup.waterview.utils.TagSupportBase#processTag(org.apache.commons.jelly.XMLOutput)
     */
    protected void processTag(XMLOutput output) throws Exception
    {
        RuntimeData data = getRuntimeData();
        View view = createView();

        if (view == null)
        {
            return;
        }
        if (getParent() instanceof ViewAware)
        {
            ViewAware viewAware = (ViewAware) getParent();
            viewAware.doView(view);
        }
        else
        {
            JellyContextAdapter adapter = new JellyContextAdapter(context);
            DefaultContext ctx = new DefaultContext(new HashMap(), adapter);
            try
            {
                view.render(data, ctx);
            }
            catch (Exception e)
            {
                data.getOutput().println("<pre>");
                e.printStackTrace(data.getOutput());
                data.getOutput().println("</pre>");
            }
        }
    }
}
