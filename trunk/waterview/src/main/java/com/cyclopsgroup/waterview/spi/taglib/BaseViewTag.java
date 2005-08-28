package com.cyclopsgroup.waterview.spi.taglib;

import java.util.HashMap;

import org.apache.avalon.framework.service.ServiceManager;
import org.apache.commons.jelly.JellyContext;
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
public abstract class BaseViewTag extends BaseTag
{
    /**
     * Overwrite this method to create component
     *
     * @param context Jelly context
     * @param data Runtime data
     * @return Create view, null is ok
     * @throws Exception Throw it out
     */
    protected abstract View createView(JellyContext context, RuntimeData data)
            throws Exception;

    /**
     * Overwrite or implement method doTag()
     * @see com.cyclopsgroup.waterview.spi.taglib.BaseTag#doTag(org.apache.avalon.framework.service.ServiceManager, org.apache.commons.jelly.XMLOutput)
     */
    protected void doTag(ServiceManager serviceManager, XMLOutput output)
            throws Exception
    {
        RuntimeData data = getRuntimeData();
        View view = createView(context, data);

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