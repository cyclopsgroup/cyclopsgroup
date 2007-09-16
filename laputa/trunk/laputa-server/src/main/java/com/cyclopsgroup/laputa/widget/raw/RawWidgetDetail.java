package com.cyclopsgroup.laputa.widget.raw;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.cyclopsgroup.laputa.pojo.UserWidget;

@Entity
@Table( name = "cg_laputa_raw_widget" )
public class RawWidgetDetail
{
    @Column( name = "content", length = 2000 )
    private String content;

    @Column( name = "created_date", nullable = false, updatable = false )
    @Temporal( TemporalType.TIMESTAMP )
    private Date createdDate;

    @Id
    @Column( name = "detail_id" )
    private long detailId;

    @Column( name = "last_modifed_date", nullable = false )
    @Temporal( TemporalType.TIMESTAMP )
    private Date lastModifiedDate;

    @OneToOne
    @JoinColumn( name = "user_widget_id", nullable = false, updatable = false )
    private UserWidget userWidget;

    @Column( name = "version", nullable = false )
    private int version;

    public String getContent()
    {
        return content;
    }

    public Date getCreatedDate()
    {
        return createdDate;
    }

    public long getDetailId()
    {
        return detailId;
    }

    public Date getLastModifiedDate()
    {
        return lastModifiedDate;
    }

    public UserWidget getUserWidget()
    {
        return userWidget;
    }

    public int getVersion()
    {
        return version;
    }

    public void setContent( String content )
    {
        this.content = content;
    }

    public void setCreatedDate( Date createdDate )
    {
        this.createdDate = createdDate;
    }

    public void setDetailId( long detailId )
    {
        this.detailId = detailId;
    }

    public void setLastModifiedDate( Date lastModifiedDate )
    {
        this.lastModifiedDate = lastModifiedDate;
    }

    public void setUserWidget( UserWidget userWidget )
    {
        this.userWidget = userWidget;
    }

    public void setVersion( int version )
    {
        this.version = version;
    }
}
