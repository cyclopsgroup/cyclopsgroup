package com.cyclopsgroup.waterview.spi.taglib;

import java.util.ArrayList;
import java.util.List;

import org.apache.avalon.framework.service.ServiceManager;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.jelly.JellyContext;
import org.apache.commons.jelly.XMLOutput;
import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.waterview.RuntimeData;
import com.cyclopsgroup.waterview.spi.Page;
import com.cyclopsgroup.waterview.spi.Panel;
import com.cyclopsgroup.waterview.spi.PanelContent;
import com.cyclopsgroup.waterview.spi.View;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Base panel tag
 */
public abstract class BasePanelTag extends BaseTag implements ViewAware
{
    private String name;

    private List views = new ArrayList();

    /**
     * Implement this method to provide panel
     *
     * @param context Jelly context
     * @param data Runtime data
     * @return Panel object. Null is allowed
     * @throws Exception Throw it out
     */
    protected abstract Panel createPanel(JellyContext context, RuntimeData data)
            throws Exception;

    /**
     * @param serviceManager
     * @param output
     * @throws Exception
     */
    protected void doTag(ServiceManager serviceManager, XMLOutput output)
            throws Exception
    {
        invokeBody(output);

        List vs = new ArrayList(views);
        Page page = (Page) context.getVariable(Page.NAME);
        if (page != null && StringUtils.isNotEmpty(getName()))
        {
            PanelContent content = page.getPanelContent(getName());
            if (content != null)
            {
                if (!content.isAppend())
                {
                    vs.clear();
                }
                CollectionUtils.addAll(vs, content.getViews());
            }
        }

        RuntimeData data = getRuntimeData();
        Panel panel = createPanel(context, data);
        if (panel == null)
        {
            panel = Panel.DUMMY;
        }
        try
        {
            panel.render(data, (View[]) views.toArray(View.EMPTY_ARRAY));
        }
        catch (Exception e)
        {
            data.getOutput().println("<pre>");
            e.printStackTrace(data.getOutput());
            data.getOutput().println("</pre>");
        }
        finally
        {
            data.getOutput().flush();
        }
    }

    /**
     * Overwrite or implement method doView()
     * @see com.cyclopsgroup.waterview.spi.taglib.ViewAware#doView(com.cyclopsgroup.waterview.spi.View)
     */
    public void doView(View view)
    {
        views.add(view);
    }

    /**
     * @return Returns the name.
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name The name to set.
     */
    public void setName(String name)
    {
        this.name = name;
    }
}
