package org.cyclopsgroup.doorman.api;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A list of users
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
@XmlRootElement( name = "Users" )
public class Users
{
    private List<User> users;

    /**
     * Default constructor called by JAXB
     */
    public Users()
    {
    }

    /**
     * @param users List of initial users
     */
    public Users( List<User> users )
    {
        setUsers( users );
    }

    /**
     * @return List of users
     */
    @XmlElement( name = "user" )
    public List<User> getUsers()
    {
        return users;
    }

    /**
     * @param users {@link #getUsers()}
     */
    public void setUsers( List<User> users )
    {
        this.users = users;
    }
}
