
# -----------------------------------------------------------------------
# c_tnd_users
# -----------------------------------------------------------------------
drop table if exists c_tnd_users;

CREATE TABLE c_tnd_users
(
		            id INTEGER NOT NULL,
		            user_name CHAR (30) NOT NULL,
		            encrypted_password VARCHAR (255) NOT NULL,
		            description VARCHAR (255),
		            is_system BIT default 0 NOT NULL,
		            created_time BIGINT,
		            is_disabled BIT default 0 NOT NULL,
    PRIMARY KEY(id),
    UNIQUE (user_name)
);

# -----------------------------------------------------------------------
# c_tnd_groups
# -----------------------------------------------------------------------
drop table if exists c_tnd_groups;

CREATE TABLE c_tnd_groups
(
		            id INTEGER NOT NULL,
		            group_name CHAR (30) NOT NULL,
		            description VARCHAR (255),
		            is_system BIT default 0 NOT NULL,
		            is_disabled BIT default 0 NOT NULL,
    PRIMARY KEY(id),
    UNIQUE (group_name)
);

# -----------------------------------------------------------------------
# c_tnd_grphrch
# -----------------------------------------------------------------------
drop table if exists c_tnd_grphrch;

CREATE TABLE c_tnd_grphrch
(
		            id INTEGER NOT NULL,
		            group_id INTEGER NOT NULL,
		            parent_group_id INTEGER NOT NULL,
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
		            id INTEGER NOT NULL,
		            user_id INTEGER NOT NULL,
		            group_id INTEGER NOT NULL,
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
		            id INTEGER NOT NULL,
		            owner_name INTEGER NOT NULL,
		            owner_type CHAR (1) NOT NULL,
		            permission VARCHAR (255) NOT NULL,
    PRIMARY KEY(id)
);
  
  
  
  
  
