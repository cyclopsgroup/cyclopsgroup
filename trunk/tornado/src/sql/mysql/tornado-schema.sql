
# -----------------------------------------------------------------------
# c_tnd_users
# -----------------------------------------------------------------------
drop table if exists c_tnd_users;

CREATE TABLE c_tnd_users
(
        id $type $nullString $autoIncrement,
        user_name $type (30) $nullString $autoIncrement,
        encrypted_password $type (255) $nullString $autoIncrement,
        description $type (255) $nullString $autoIncrement,
        is_system $type default 0 $nullString $autoIncrement,
        email $type (255) $nullString $autoIncrement,
        first_name $type (255) $nullString $autoIncrement,
        middle_name $type (30) $nullString $autoIncrement,
        last_name $type (255) $nullString $autoIncrement,
        last_signin $type $nullString $autoIncrement,
        signin_counter $type default 0 $nullString $autoIncrement,
        last_access $type $nullString $autoIncrement,
        created_time $type $nullString $autoIncrement,
        is_disabled $type default 0 $nullString $autoIncrement,
    PRIMARY KEY(id),
    UNIQUE (user_name)
);

# -----------------------------------------------------------------------
# c_tnd_usrobjs
# -----------------------------------------------------------------------
drop table if exists c_tnd_usrobjs;

CREATE TABLE c_tnd_usrobjs
(
        id $type $nullString $autoIncrement,
        user_id $type $nullString $autoIncrement,
        object_key $type (255) $nullString $autoIncrement,
        object_class_name $type (255) $nullString $autoIncrement,
        object_data $type $nullString $autoIncrement,
    PRIMARY KEY(id),
    FOREIGN KEY (user_id) REFERENCES c_tnd_users (id)
    
);

# -----------------------------------------------------------------------
# c_tnd_groups
# -----------------------------------------------------------------------
drop table if exists c_tnd_groups;

CREATE TABLE c_tnd_groups
(
        id $type $nullString $autoIncrement,
        group_name $type (30) $nullString $autoIncrement,
        description $type (255) $nullString $autoIncrement,
        is_system $type default 0 $nullString $autoIncrement,
        is_disabled $type default 0 $nullString $autoIncrement,
    PRIMARY KEY(id),
    UNIQUE (group_name)
);

# -----------------------------------------------------------------------
# c_tnd_grphrch
# -----------------------------------------------------------------------
drop table if exists c_tnd_grphrch;

CREATE TABLE c_tnd_grphrch
(
        id $type $nullString $autoIncrement,
        group_id $type $nullString $autoIncrement,
        parent_group_id $type $nullString $autoIncrement,
    PRIMARY KEY(id),
    FOREIGN KEY (group_id) REFERENCES c_tnd_groups (id)
    ,
    FOREIGN KEY (parent_group_id) REFERENCES c_tnd_groups (id)
    
);

# -----------------------------------------------------------------------
# c_tnd_user_group
# -----------------------------------------------------------------------
drop table if exists c_tnd_user_group;

CREATE TABLE c_tnd_user_group
(
        id $type $nullString $autoIncrement,
        user_id $type $nullString $autoIncrement,
        group_id $type $nullString $autoIncrement,
    PRIMARY KEY(id,user_id,group_id),
    FOREIGN KEY (user_id) REFERENCES c_tnd_users (id)
    ,
    FOREIGN KEY (group_id) REFERENCES c_tnd_groups (id)
    
);

# -----------------------------------------------------------------------
# c_tnd_acls
# -----------------------------------------------------------------------
drop table if exists c_tnd_acls;

CREATE TABLE c_tnd_acls
(
        id $type $nullString $autoIncrement,
        owner_name $type $nullString $autoIncrement,
        owner_type $type (1) $nullString $autoIncrement,
        permission $type (255) $nullString $autoIncrement,
    PRIMARY KEY(id)
);
  
  
  
  
  
  
