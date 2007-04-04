package com.cyclopsgroup.laputa.space.pojo;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "cglpt_spcsvcctx", uniqueConstraints = { @UniqueConstraint(columnNames = { "user_account_id" }) })
@NamedQuery(name = "findServiceContextByUserAccountId", query = "SELECT c FROM SpaceServiceContext c WHERE userAccountId = :userAccountId")
public class SpaceServiceContext
{
    private String description;

    private boolean disabled;

    private boolean hidden;

    private long id;

    private Collection<SpacePage> pages;

    private String title;

    private long userAccountId;

    private Collection<SpaceViewlet> viewlets;

    @Column(name = "description")
    public String getDescription()
    {
        return description;
    }

    @Id
    @Column(name = "sevice_context_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ServiceContextIdGen")
    @SequenceGenerator(name = "ServiceContextIdGen", sequenceName = "cglpt_spcsvcctx_seq")
    public long getId()
    {
        return id;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "serviceContext")
    public Collection<SpacePage> getPages()
    {
        return pages;
    }

    @Column(name = "title", nullable = false, length = 255)
    public String getTitle()
    {
        return title;
    }

    @Column(name = "user_account_id", nullable = false)
    public long getUserAccountId()
    {
        return userAccountId;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "serviceContext")
    public Collection<SpaceViewlet> getViewlets()
    {
        return viewlets;
    }

    @Column(name = "disabled")
    public boolean isDisabled()
    {
        return disabled;
    }

    @Column(name = "hidden")
    public boolean isHidden()
    {
        return hidden;
    }

    @Column(name = "description", length = 1024)
    public void setDescription( String description )
    {
        this.description = description;
    }

    public void setDisabled( boolean disabled )
    {
        this.disabled = disabled;
    }

    public void setHidden( boolean hidden )
    {
        this.hidden = hidden;
    }

    public void setId( long id )
    {
        this.id = id;
    }

    public void setPages( Collection<SpacePage> pages )
    {
        this.pages = pages;
    }

    public void setTitle( String title )
    {
        this.title = title;
    }

    public void setUserAccountId( long userAccountId )
    {
        this.userAccountId = userAccountId;
    }

    public void setViewlets( Collection<SpaceViewlet> viewlets )
    {
        this.viewlets = viewlets;
    }
}
