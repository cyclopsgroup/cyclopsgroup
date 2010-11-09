package org.cyclopsgroup.doorman.api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Response of sign up operation
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
@XmlRootElement( name = "UserSignUpResponse" )
public class UserSignUpResponse
{
    private UserOperationResult result;

    private String token;

    /**
     * Default constructor called by JAXB
     */
    public UserSignUpResponse()
    {
    }

    /**
     * @param result Result enumeration
     * @param token Secret token
     */
    public UserSignUpResponse( UserOperationResult result, String token )
    {
        this.result = result;
        this.token = token;
    }

    /**
     * @return Enumerated result that indicates what happened for sign up operation
     */
    @XmlElement
    public final UserOperationResult getResult()
    {
        return result;
    }

    /**
     * @return A secret token to finish sign up
     */
    public final String getToken()
    {
        return token;
    }

    /**
     * @param result {@link #getResult()}
     */
    public final void setResult( UserOperationResult result )
    {
        this.result = result;
    }

    /**
     * @param token {@link #getToken()}
     */
    public final void setToken( String token )
    {
        this.token = token;
    }
}
