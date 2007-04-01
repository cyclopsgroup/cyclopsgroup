package com.cyclopsgroup.laputa.identity.db;

import java.io.Serializable;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "cg_laputa_user", uniqueConstraints = { @UniqueConstraint(columnNames = { "user_name" }) })
@NamedQuery(name = "com.cyclopsgroup.laputa.identity.db.User.findByName", query = "select u from com.cyclopsgroup.laputa.identity.db.User u where userName = :userName")
public class User
    implements Serializable
{
    private static final long serialVersionUID = 1L;

    private String country = Locale.getDefault().getCountry();

    private Date createdDate = new Date();

    private boolean disabled;

    private String encodedPassword;

    private String language = Locale.getDefault().getLanguage();

    private String timeZone = TimeZone.getDefault().getID();

    private long userId;

    private String userName;

    @Column(nullable = false, length = 5)
    public String getCountry()
    {
        return country;
    }

    @Column(name = "created_date")
    @Temporal(TemporalType.DATE)
    public Date getCreatedDate()
    {
        return createdDate;
    }

    @Column(name = "encoded_password", length = 30)
    public String getEncodedPassword()
    {
        return encodedPassword;
    }

    @Column(nullable = false, length = 5)
    public String getLanguage()
    {
        return language;
    }

    @Column(name = "time_zone", nullable = false, length = 30)
    public String getTimeZone()
    {
        return timeZone;
    }

    @Column(name = "user_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getUserId()
    {
        return userId;
    }

    @Column(name = "user_name", nullable = false, length = 30)
    public String getUserName()
    {
        return userName;
    }

    public boolean isDisabled()
    {
        return disabled;
    }

    public void setCountry( String country )
    {
        this.country = country;
    }

    public void setCreatedDate( Date createdDate )
    {
        this.createdDate = createdDate;
    }

    public void setDisabled( boolean disabled )
    {
        this.disabled = disabled;
    }

    public void setEncodedPassword( String encodedPassword )
    {
        this.encodedPassword = encodedPassword;
    }

    public void setLanguage( String language )
    {
        this.language = language;
    }

    public void setTimeZone( String timeZone )
    {
        this.timeZone = timeZone;
    }

    public void setUserId( long userId )
    {
        this.userId = userId;
    }

    public void setUserName( String userName )
    {
        this.userName = userName;
    }
}