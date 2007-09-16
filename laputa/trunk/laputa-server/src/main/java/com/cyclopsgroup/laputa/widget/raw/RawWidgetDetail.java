package com.cyclopsgroup.laputa.widget.raw;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table( name = "cg_laputa_raw_widget" )
public class RawWidgetDetail
{
    @Column( name = "content", length = 2000 )
    private String content;

    @Id
    @Column( name = "detail_id" )
    private long detailId;

    @Column( name = "last_modifed_date", nullable = false )
    @Temporal( TemporalType.TIMESTAMP )
    private Date lastModifiedDate;

    public String getContent()
    {
        return content;
    }

    public long getDetailId()
    {
        return detailId;
    }

    public Date getLastModifiedDate()
    {
        return lastModifiedDate;
    }

    public void setContent( String content )
    {
        this.content = content;
    }

    public void setDetailId( long detailId )
    {
        this.detailId = detailId;
    }

    public void setLastModifiedDate( Date lastModifiedDate )
    {
        this.lastModifiedDate = lastModifiedDate;
    }
}
