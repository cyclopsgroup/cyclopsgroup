package com.cyclopsgroup.waterview.ui.page.pub;

import com.cyclopsgroup.waterview.BaseServiceable;
import com.cyclopsgroup.waterview.Context;
import com.cyclopsgroup.waterview.Module;
import com.cyclopsgroup.waterview.RunData;
import com.cyclopsgroup.waterview.spi.Layout;
import com.cyclopsgroup.waterview.spi.ModuleService;
import com.cyclopsgroup.waterview.spi.RunDataSpi;

/**
 * Module for page navigation node
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class DefaultNavigationNode
    extends BaseServiceable
    implements Module
{
    /**
     * @see com.cyclopsgroup.waterview.Module#execute(com.cyclopsgroup.waterview.RunData, com.cyclopsgroup.waterview.Context)
     */
    public void execute( RunData data, Context context )
        throws Exception
    {
        ModuleService moduleService = (ModuleService) lookup( ModuleService.ROLE );
        Layout layout = moduleService.getLayout( "waterview.layout.raw" );
        ( (RunDataSpi) data ).getPageObject().setLayout( layout );
    }
}
