package org.cyclopsgroup.larc.serv.web;

import org.cyclopsgroup.larc.api.EventRepository;
import org.cyclopsgroup.waterview.Module;
import org.cyclopsgroup.waterview.WebContext;

/**
 * Web module to add new event
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
@Module( path = "/adding_event.do" )
public class AddEventAction
{
    private final EventRepository eventRepository;

    /**
     * @param eventRepository Given {@link EventRepository} interface
     */
    public AddEventAction( EventRepository eventRepository )
    {
        this.eventRepository = eventRepository;
    }

    public void render( WebContext context )
    {
        
    }
}
