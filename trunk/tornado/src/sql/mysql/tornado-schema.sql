
# -----------------------------------------------------------------------
# c_tnd_users
# -----------------------------------------------------------------------
drop table if exists c_tnd_users;

CREATE TABLE c_tnd_users
(
        user_id INTEGER NOT NULL,
        user_name CHAR (30) NOT NULL,
        encrypted_password VARCHAR (255) NOT NULL,
        description VARCHAR (255),
        is_system BIT default 0 NOT NULL,
        email VARCHAR (255) NOT NULL,
        first_name VARCHAR (255) NOT NULL,
        middle_name VARCHAR (30),
        last_name VARCHAR (255) NOT NULL,
        last_signin BIGINT,
        signin_counter INTEGER default 0 NOT NULL,
        last_access BIGINT,
        created_time BIGINT,
        is_disabled BIT default 0 NOT NULL,
    PRIMARY KEY(user_id),
    UNIQUE (user_name)
);

# -----------------------------------------------------------------------
# c_tnd_usrobjs
# -----------------------------------------------------------------------
drop table if exists c_tnd_usrobjs;

CREATE TABLE c_tnd_usrobjs
(
        object_id INTEGER NOT NULL,
        user_id INTEGER NOT NULL,
        object_key VARCHAR (255) NOT NULL,
        object_class_name VARCHAR (255) NOT NULL,
        object_data MEDIUMTEXT,
    PRIMARY KEY(object_id),
    FOREIGN KEY (user_id) REFERENCES c_tnd_users (user_id)
    
);

# -----------------------------------------------------------------------
# c_tnd_groups
# -----------------------------------------------------------------------
drop table if exists c_tnd_groups;

CREATE TABLE c_tnd_groups
(
        group_id INTEGER NOT NULL,
        group_name CHAR (30) NOT NULL,
        description VARCHAR (255),
        is_system BIT default 0 NOT NULL,
        is_disabled BIT default 0 NOT NULL,
    PRIMARY KEY(group_id),
    UNIQUE (group_name)
);

# -----------------------------------------------------------------------
# c_tnd_grphrch
# -----------------------------------------------------------------------
drop table if exists c_tnd_grphrch;

CREATE TABLE c_tnd_grphrch
(
        hierarchy_id INTEGER NOT NULL,
        group_id INTEGER NOT NULL,
        parent_group_id INTEGER NOT NULL,
    PRIMARY KEY(hierarchy_id),
    FOREIGN KEY (group_id) REFERENCES c_tnd_groups (group_id)
    ,
    FOREIGN KEY (parent_group_id) REFERENCES c_tnd_groups (group_id)
    
);

# -----------------------------------------------------------------------
# c_tnd_user_group
# -----------------------------------------------------------------------
drop table if exists c_tnd_user_group;

CREATE TABLE c_tnd_user_group
(
        object_id INTEGER NOT NULL,
        user_id INTEGER NOT NULL,
        group_id INTEGER NOT NULL,
    PRIMARY KEY(object_id,user_id,group_id),
    FOREIGN KEY (user_id) REFERENCES c_tnd_users (user_id)
    ,
    FOREIGN KEY (group_id) REFERENCES c_tnd_groups (group_id)
    
);

# -----------------------------------------------------------------------
# c_tnd_acls
# -----------------------------------------------------------------------
drop table if exists c_tnd_acls;

CREATE TABLE c_tnd_acls
(
        acl_id INTEGER NOT NULL,
        owner_name CHAR (30) NOT NULL,
        owner_type CHAR (1) NOT NULL,
        is_role BIT default 0 NOT NULL,
        permission VARCHAR (255) NOT NULL,
    PRIMARY KEY(acl_id)
);

# -----------------------------------------------------------------------
# c_tnd_roles
# -----------------------------------------------------------------------
drop table if exists c_tnd_roles;

CREATE TABLE c_tnd_roles
(
        role_id INTEGER NOT NULL,
        role_name CHAR (30) NOT NULL,
        description VARCHAR (255),
    PRIMARY KEY(role_id)
);

# -----------------------------------------------------------------------
# c_tnd_confs
# -----------------------------------------------------------------------
drop table if exists c_tnd_confs;

CREATE TABLE c_tnd_confs
(
        conf_id INTEGER NOT NULL,
        conf_key VARCHAR (255) NOT NULL,
        conf_value VARCHAR (255),
    PRIMARY KEY(conf_id)
);
  
  
  
  
  
  
  
  
