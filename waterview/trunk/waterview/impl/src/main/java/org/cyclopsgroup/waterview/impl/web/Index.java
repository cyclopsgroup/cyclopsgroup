package org.cyclopsgroup.waterview.impl.web;

import org.cyclopsgroup.waterview.Mapping;
import org.cyclopsgroup.waterview.Page;

/**
 * Index home page
 *
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
@Mapping( path = "/index.vm" )
public class Index
{
    /**
     * @return A string
     */
    @Page( title = "Waterivew", description = "Welcome to waterview" )
    public String render()
    {
        return "fromIndex";
    }
}
