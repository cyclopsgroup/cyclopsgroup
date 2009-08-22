package org.cyclopsgroup.caff;

import java.util.Date;

import org.cyclopsgroup.caff.conversion.DateField;

/**
 * A bean for testing purpose
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
public class ABean
{
    private int age;

    private Date birthDay;

    private String firstName;

    public String lastName;

    private boolean retired;

    public int getAge()
    {
        return age;
    }

    /**
     * @return Date of birthday
     */
    @DateField( format = "yyyyMMdd" )
    public Date getBirthDay()
    {
        return birthDay;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public boolean isRetired()
    {
        return retired;
    }

    public void setAge( int age )
    {
        this.age = age;
    }

    public void setBirthDay( Date birthDay )
    {
        this.birthDay = birthDay;
    }

    public void setFirstName( String firstName )
    {
        this.firstName = firstName;
    }

    public void setRetired( boolean retired )
    {
        this.retired = retired;
    }
}
