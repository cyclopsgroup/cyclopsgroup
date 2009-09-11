package org.cyclopsgroup.caff.format;

import java.util.Date;

import org.cyclopsgroup.caff.conversion.DateField;

/**
 * A bean for CSV format testing
 *
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
@CSVType( fields = 5 )
public class CSVBean
{
    private String firstName;

    @CSVField( position = 0 )
    public String lastName;

    private int age;

    private Date birthDay;

    public Date getBirthDay()
    {
        return birthDay;
    }

    @DateField( format = "yyyyMMdd" )
    @CSVField( position = 4 )
    public void setBirthDay( Date birthDay )
    {
        this.birthDay = birthDay;
    }

    @CSVField( position = 1 )
    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName( String firstName )
    {
        this.firstName = firstName;
    }

    @CSVField( position = 3 )
    public int getAge()
    {
        return age;
    }

    public void setAge( int age )
    {
        this.age = age;
    }
}
