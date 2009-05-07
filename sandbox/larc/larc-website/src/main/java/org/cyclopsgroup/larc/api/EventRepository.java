package org.cyclopsgroup.larc.api;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Facade interface for event manipulation
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
@WebService
public interface EventRepository
{
    /**
     * Add new event into repository
     * 
     * @param event Event object
     * @return Unique ID associated to this event
     */
    @WebMethod
    String addEvent(Event event);
}
