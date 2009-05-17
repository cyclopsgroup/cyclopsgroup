package org.cyclopsgroup.waterview.impl.web;

import org.cyclopsgroup.waterview.Module;
import org.cyclopsgroup.waterview.Page;

/**
 * Index home page
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */

public class Index
{
    /**
     * @return A string
     */
    @Module( path = "/index.vm", returnVariable="indexVariable" )
    @Page( title = "Waterivew", description = "Welcome to waterview" )
    public String render( )
    {
        return "fromIndex";
    }
}
