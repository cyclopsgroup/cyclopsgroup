package org.cyclopsgroup.doorman.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Request to list users
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
@XmlRootElement( name = "ListUserRequest" )
public class ListUserRequest
{
    private List<String> userNames;

    /**
     * Default constructor called by JAXB
     */
    public ListUserRequest()
    {
    }

    /**
     * Constructor that takes a list of user names
     *
     * @param userNames List of user names or IDs
     */
    public ListUserRequest( Collection<String> userNames )
    {
        setUserNames( new ArrayList<String>( userNames ) );
    }

    /**
     * @return List of user names to list
     */
    @XmlElement( name = "name" )
    @XmlElementWrapper
    public List<String> getUserNames()
    {
        return userNames;
    }

    /**
     * @param userNames {@link #getUserNames()}
     */
    public void setUserNames( List<String> userNames )
    {
        this.userNames = userNames;
    }
}
