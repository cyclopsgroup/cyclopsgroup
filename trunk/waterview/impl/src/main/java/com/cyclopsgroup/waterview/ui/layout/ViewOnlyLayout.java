package com.cyclopsgroup.waterview.ui.layout;

import com.cyclopsgroup.waterview.spi.Layout;
import com.cyclopsgroup.waterview.spi.ModuleService;
import com.cyclopsgroup.waterview.spi.Page;
import com.cyclopsgroup.waterview.spi.RunDataSpi;
import com.cyclopsgroup.waterview.spi.View;

/**
 * Layout that show view only
 * 
 * @author jiaqi.guo@gmail.com
 *
 */
public class ViewOnlyLayout
    implements Layout
{
    private static final String VIEW = "/view";

    /**
     * @see com.cyclopsgroup.waterview.spi.Layout#render(com.cyclopsgroup.waterview.spi.RunDataSpi, com.cyclopsgroup.waterview.spi.Page)
     */
    public void render( RunDataSpi data, Page page )
        throws Exception
    {
        ModuleService moduleService = (ModuleService) data.getServiceManager().lookup( ModuleService.ROLE );
        String path = '/' + data.getPage().getPackageAlias() + VIEW + data.getPage().getPath();
        View view = moduleService.createDynaView( path );
        view.render( data, data.getRequestContext() );
    }
}
