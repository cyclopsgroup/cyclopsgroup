package com.cyclopsgroup.laputa.server.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table( name = "cg_laputa_service" )
public class PortalService
{
    @Column( name = "country", nullable = false, length = 8 )
    private String country;

    @Column( name = "created_date", nullable = false )
    @Temporal( TemporalType.TIMESTAMP )
    private Date createdDate;

    @Column( name = "default_language", nullable = false, length = 8 )
    private String defaultLanguage;

    @Column( name = "description", length = 512 )
    private String description;

    @Column( name = "last_modified_date", nullable = false )
    @Temporal( TemporalType.TIMESTAMP )
    private Date lastModifiedDate;

    @Id
    @Column( name = "service_id" )
    private long serviceId;

    @Column( name = "service_state", nullable = false, length = 16 )
    @Enumerated( EnumType.STRING )
    private ServiceState serviceState;

    @Column( name = "title", nullable = false, length = 64 )
    private String title;

    @Column( name = "user_id", nullable = false, length = 64 )
    private String userId;

    public String getCountry()
    {
        return country;
    }

    public Date getCreatedDate()
    {
        return createdDate;
    }

    public String getDefaultLanguage()
    {
        return defaultLanguage;
    }

    public String getDescription()
    {
        return description;
    }

    public Date getLastModifiedDate()
    {
        return lastModifiedDate;
    }

    public long getServiceId()
    {
        return serviceId;
    }

    public ServiceState getServiceState()
    {
        return serviceState;
    }

    public String getTitle()
    {
        return title;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setCountry( String country )
    {
        this.country = country;
    }

    public void setCreatedDate( Date createdDate )
    {
        this.createdDate = createdDate;
    }

    public void setDefaultLanguage( String defaultLanguage )
    {
        this.defaultLanguage = defaultLanguage;
    }

    public void setDescription( String description )
    {
        this.description = description;
    }

    public void setLastModifiedDate( Date lastModifiedDate )
    {
        this.lastModifiedDate = lastModifiedDate;
    }

    public void setServiceId( long serviceId )
    {
        this.serviceId = serviceId;
    }

    public void setServiceState( ServiceState serviceState )
    {
        this.serviceState = serviceState;
    }

    public void setTitle( String title )
    {
        this.title = title;
    }

    public void setUserId( String userId )
    {
        this.userId = userId;
    }
}
