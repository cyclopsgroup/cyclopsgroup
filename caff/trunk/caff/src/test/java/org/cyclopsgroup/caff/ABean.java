package org.cyclopsgroup.caff;

import java.util.Date;

import org.cyclopsgroup.caff.conversion.BooleanField;
import org.cyclopsgroup.caff.conversion.DateField;
import org.cyclopsgroup.caff.format.AlignPolicy;
import org.cyclopsgroup.caff.format.FixLengthField;
import org.cyclopsgroup.caff.format.FixLengthType;

/**
 * A bean for testing purpose
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
@FixLengthType( length = 22 )
public class ABean
{
    private int age;

    private Date birthDay;

    private String firstName;

    /**
     * A public string field
     */
    @FixLengthField( position = 21, length = 10 )
    public String lastName;

    private boolean retired;

    /**
     * @return A integer age
     */
    @FixLengthField( position = 0, length = 3, align = AlignPolicy.RIGHT, fill = '0' )
    public int getAge()
    {
        return age;
    }

    /**
     * @return A date field
     */
    @DateField( format = "yyyyMMdd" )
    @FixLengthField( position = 3, length = 8 )
    public Date getBirthDay()
    {
        return birthDay;
    }

    /**
     * @return A string field
     */
    @FixLengthField( position = 11, length = 10 )
    public String getFirstName()
    {
        return firstName;
    }

    /**
     * @return A boolean field
     */
    @BooleanField( yes = "1", no = "0" )
    @FixLengthField( position = 21, length = 1 )
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
