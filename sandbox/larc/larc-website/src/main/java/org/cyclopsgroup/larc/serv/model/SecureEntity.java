package org.cyclopsgroup.larc.serv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "larc_secure_entity" )
public class SecureEntity
{
    @Column( name = "id", length = 64 )
    @Id
    private String id;

    @Column( name = "owner_id", length = 32 )
    private String ownerId;

    @Column( name = "group_id", length = 32 )
    private String groupId;

    @Column( name = "creation_date" )
    private String creationDate;

    @Column( name = "last_update" )
    private String lastUpdate;

    @Column( name = "owner_policy" )
    @Enumerated
    private EntitySecurityPolicy ownerPolicy;

    @Column( name = "group_policy" )
    @Enumerated
    private EntitySecurityPolicy groupPolicy;

    @Column( name = "public_policy" )
    @Enumerated
    private EntitySecurityPolicy publicPolicy;
}
