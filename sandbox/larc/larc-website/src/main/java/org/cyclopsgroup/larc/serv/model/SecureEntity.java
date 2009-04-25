package org.cyclopsgroup.larc.serv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Secure entity that manages persmission of another entity
 *
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
@Entity
@Table( name = "larc_secure_entity" )
public class SecureEntity
{
    @Column( name = "creation_date" )
    private String creationDate;

    @Column( name = "group_id", length = 32 )
    private String groupId;

    @Column( name = "group_policy" )
    @Enumerated
    private EntitySecurityPolicy groupPolicy;

    @Column( name = "id", length = 64 )
    @Id
    private String id;

    @Column( name = "last_update" )
    private String lastUpdate;

    @Column( name = "owner_id", length = 32 )
    private String ownerId;

    @Column( name = "owner_policy" )
    @Enumerated
    private EntitySecurityPolicy ownerPolicy;

    @Column( name = "public_policy" )
    @Enumerated
    private EntitySecurityPolicy publicPolicy;

    public final String getCreationDate()
    {
        return creationDate;
    }

    public final String getGroupId()
    {
        return groupId;
    }

    public final EntitySecurityPolicy getGroupPolicy()
    {
        return groupPolicy;
    }

    public final String getId()
    {
        return id;
    }

    public final String getLastUpdate()
    {
        return lastUpdate;
    }

    public final String getOwnerId()
    {
        return ownerId;
    }

    public final EntitySecurityPolicy getOwnerPolicy()
    {
        return ownerPolicy;
    }

    public final EntitySecurityPolicy getPublicPolicy()
    {
        return publicPolicy;
    }

    public final void setCreationDate( String creationDate )
    {
        this.creationDate = creationDate;
    }

    public final void setGroupId( String groupId )
    {
        this.groupId = groupId;
    }

    public final void setGroupPolicy( EntitySecurityPolicy groupPolicy )
    {
        this.groupPolicy = groupPolicy;
    }

    public final void setId( String id )
    {
        this.id = id;
    }

    public final void setLastUpdate( String lastUpdate )
    {
        this.lastUpdate = lastUpdate;
    }

    public final void setOwnerId( String ownerId )
    {
        this.ownerId = ownerId;
    }

    public final void setOwnerPolicy( EntitySecurityPolicy ownerPolicy )
    {
        this.ownerPolicy = ownerPolicy;
    }

    public final void setPublicPolicy( EntitySecurityPolicy publicPolicy )
    {
        this.publicPolicy = publicPolicy;
    }
}
